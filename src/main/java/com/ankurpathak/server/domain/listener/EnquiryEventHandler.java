package com.ankurpathak.server.domain.listener;

import com.ankurpathak.server.domain.model.Enquiry;
import com.ankurpathak.server.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;


/**
 * Created by ankur on 06-04-2017.
 */
@RepositoryEventHandler
@Component
public class EnquiryEventHandler {



    @Autowired
    private IEmailService emailService;







    @HandleAfterCreate
    public void handlePersonCreate(Enquiry enquiry) {
        emailService.sendCnoteMail(enquiry);
    }

}
