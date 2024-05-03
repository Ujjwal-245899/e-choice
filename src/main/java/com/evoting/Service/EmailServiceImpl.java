package com.evoting.Service;

import com.evoting.DAO.EnrolledUserRepository;
import com.evoting.Model.EmailDetails;
import com.evoting.Model.EnrolledUser;
import com.evoting.Model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;

@Service
public class EmailServiceImpl  implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;

    @Autowired
    private EnrolledUserRepository enrolledUserRepository;

 // TO Send A simple Email
    @Override
    public String sendSimpleMail(String to, String otp)
    {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(to);
            mailMessage.setText("This is my Mail--> OTP is "+ otp);
            mailMessage.setSubject("Email for enroll ment");

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    @Override
    public void sendEnrollmentNumber( User user) {
        //making of enrollment Number
        String enroll="";
        String state=user.getState();
        String email=user.getEmail();
        String rndm=generateOtp();
        enroll=state.substring(0,3).concat(rndm).toUpperCase();
        EnrolledUser enrolledUser = new EnrolledUser();
        enrolledUser.setErollmentNumber(enroll);
        enrolledUser.setName(user.getName());
        enrolledUserRepository.save(enrolledUser);
        System.out.println("--Enrollment saved to the database");

            // Try block to check for exceptions
            try {

                // Creating a simple mail message
                SimpleMailMessage mailMessage
                        = new SimpleMailMessage();

                // Setting up necessary details
                mailMessage.setFrom(sender);
                mailMessage.setTo(email);
                mailMessage.setText("This is my Mail--> OTP is "+ enroll);
                mailMessage.setSubject("Email for enroll ment");

                // Sending the mail
                javaMailSender.send(mailMessage);
                System.out.println("Enrollment send sucessfully");
            }

            // Catch block to handle the exceptions
            catch (Exception e) {
               e.printStackTrace();
            }
    }
    public String generateOtp() {

        String numbers = "0123456789";
        StringBuilder otp = new StringBuilder();
        Random random = new Random();

        // Generate OTP of given length
        for (int i = 0; i < 6; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }

        return otp.toString();


    }




}
