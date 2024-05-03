package com.evoting.Service;

import com.evoting.Model.EmailDetails;
import com.evoting.Model.User;

public interface EmailService {

    //to send a simple email
    String sendSimpleMail(String to,String otp);
    void sendEnrollmentNumber( User user);

    //to send an email eith attachement
  //  String sendMailWithAttachment(EmailDetails emailDetails);
}
