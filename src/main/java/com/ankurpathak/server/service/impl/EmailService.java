package com.ankurpathak.server.service.impl;

import com.ankurpathak.server.domain.model.Customer;
import com.ankurpathak.server.domain.model.Enquiry;
import com.ankurpathak.server.domain.model.Organization;
import com.ankurpathak.server.domain.model.User;
import com.ankurpathak.server.domain.repository.IUserRepository;
import com.ankurpathak.server.service.IEmailService;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by ankur on 29-05-2017.
 */
@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private Environment environment;



    public static final String MODEL_PARAM_NAME = "name";
    public static final String MODEL_PARAM_LINK = "link";
    public static final String PROPERTY_SUPPORT_EMAIL = "email.from";
    public static final String MESSAGE_CNOTE_SUBJECT = "email.cnote.subject";
    public static final String TEMPLATE_CNOTE = "cnote";
    public static final String TEMPLATE_FORGET_PASSWORD = "forget-password";



    private void sendHtmlMail(String emailTo, String organizationName, String emailText)throws Exception{
        sendHtmlMail(emailTo, organizationName, emailText, null, null);
    }










    private void sendHtmlMail(String emailTo, String organizationName, String emailText, DataSource dataSource, String replyTo) throws Exception{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        messageHelper.setFrom(String.format(environment.getProperty(PROPERTY_SUPPORT_EMAIL), organizationName));
        messageHelper.setTo(emailTo);
        if(replyTo != null)
            messageHelper.setReplyTo(replyTo);
        messageHelper.setSubject(environment.getProperty(MESSAGE_CNOTE_SUBJECT));
        messageHelper.setText(emailText, true);
        if(dataSource != null)
            messageHelper.addAttachment(dataSource.getName(), dataSource);
        javaMailSender.send(message);

    }

    @Autowired
    private IUserRepository userRepository;


    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public void sendCnoteMail(Enquiry enquiry) {

        Customer customer = enquiry.getCustomer();
        User user = enquiry.getCreatedBy();
        String customerEmail = customer.getEmail();
        String userEmail = user.getEmail();
        Organization organization = enquiry.getOrganization();
        Optional<User> admin = userRepository.findByOrganizationIdAndRoles(organization.getId(), User.Role.ROLE_ADMIN).stream().findFirst();

        String logoUrl = String.format("%s/organizations/%s", organization.getImgBaseUrl(),organization.getImgUrl());
        Context context = new Context();
        context.setVariable("now", Calendar.getInstance().getTime());
        context.setVariable("logoUrl", logoUrl);
        context.setVariable("enquiry", enquiry);

        Document document = null;
        ByteArrayInputStream htmlIs = null;
        ByteArrayOutputStream pdfOs = null;
        try{
            String emailText = templateEngine.process(TEMPLATE_CNOTE, context);
            document = new Document();
            htmlIs = new ByteArrayInputStream(emailText.getBytes());
            pdfOs = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document,pdfOs);
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, htmlIs);
            document.close();
            ByteArrayDataSource dataSource = new ByteArrayDataSource(pdfOs.toByteArray(), "application/pdf");
            dataSource.setName("cnote.pdf");
            pdfOs.close();
            htmlIs.close();


            if(customerEmail!= null){
                taskExecutor.execute(()-> {
                    try{
                        sendHtmlMail(customerEmail,organization.getName(), emailText, dataSource, userEmail);
                    }catch (Exception ex){}
                });

            }



        if(userEmail!= null){
            taskExecutor.execute(()-> {
                try {
                    sendHtmlMail(userEmail,organization.getName(), emailText, dataSource, null);
                } catch (Exception e) {}
            });

        }

        if(admin.isPresent()){
            String adminEmail = admin.get().getEmail();
            if(adminEmail != null){
                taskExecutor.execute(()-> {
                    try {
                        sendHtmlMail(adminEmail,organization.getName(), emailText, dataSource, null);
                    } catch (Exception e) {}
                });
            }
        }

        }catch (Exception ex){}
        finally {
            try{
                htmlIs.close();
                pdfOs.close();

            }catch (Exception ex){}
        }



    }

    @Override
    public void sendForgetMail(User user, String password) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("password", password);
        try {
            String emailText = templateEngine.process(TEMPLATE_FORGET_PASSWORD, context);
            String email = user.getEmail();
            if(email!= null){
                taskExecutor.execute(()-> {
                    try {
                        sendHtmlMail(email, user.getOrganization().getName(), emailText);
                    } catch (Exception e) {}
                });

            }


        }catch (Exception ex){

        }
    }
}
