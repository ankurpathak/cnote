package com.ankurpathak.server.service;

import com.ankurpathak.server.domain.model.Enquiry;
import com.ankurpathak.server.domain.model.User;

/**
 * Created by ankur on 29-05-2017.
 */
public interface IEmailService {
    void sendCnoteMail(Enquiry enquiry);

    void sendForgetMail(User user, String password);

}
