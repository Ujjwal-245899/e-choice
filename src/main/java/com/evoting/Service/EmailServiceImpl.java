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
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

@Service
public class EmailServiceImpl  implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;

    @Autowired
    private EnrolledUserRepository enrolledUserRepository;

    @Autowired
    EnrolledUserImpl enrolledUser;

 // TO Send A simple Email
    @Override
    public String sendSimpleMail(String to, String otp)
    {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

// Generate a random OTP (assuming it's stored in a variable called 'otp')


// Construct the message
        String otpMessage = "Hello,\n\n" +
                "Thank you for using E-choice's OTP service. Your one-time password (OTP) for authentication is: " + otp + ".\n\n" +
                "Please enter this OTP to proceed with your action. This OTP is valid for a limited time and should not be shared with anyone.\n\n" +
                "If you did not request this OTP, please ignore this message.\n\n" +
                "Timestamp: " + formattedDateTime + "\n\n" +
                "Best regards,\nThe E-choice Team";

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(to);
            mailMessage.setText(otpMessage);
            mailMessage.setSubject("Otp Verification E-Choice");

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
        //Genearting the Random Number
        String rndm=generateOtp();
        //Genrating the Enroll ment Number by concatination of random and Stae name
        enroll=state.substring(0,3).concat(rndm).toUpperCase();
        //Genarating New Enrolled User

       enrolledUser.saveEnrollment(user,enroll);

       // Message for Enrollment
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        String onboardingMessage = "Welcome to the Onboarding of E-choice - The Seamless Voting Experience.\n\n" +
                "We're thrilled to have you join us in shaping the future through your vote. Your participation is crucial in making our democracy vibrant and inclusive.\n\n" +
                "Here is the Enrollment Provided: [" + enroll +"] Use this Enrollment number to cast your vote for the candidates in the upcoming election.\n\n" +
                "But that's not all. With E-choice, you can expect:\n\n" +
                "1. Secure and Transparent Voting: We employ state-of-the-art encryption and blockchain technology to ensure that your vote is secure and your privacy is protected.\n" +
                "2. User-Friendly Interface: Our platform is designed to be intuitive and easy to use, making the voting process hassle-free for everyone.\n" +
                "3. Real-Time Updates: Stay informed with real-time updates on election results, ensuring transparency and trust in the electoral process.\n" +
                "4. Accessibility: E-choice is accessible to all eligible voters, regardless of their location or physical abilities, promoting inclusivity and equal participation.\n\n" +
                "We encourage you to explore our platform and familiarize yourself with the voting process. Remember, every vote counts!\n\n" +
                "If you have any questions or need assistance, feel free to reach out to our support team at [support@email.com].\n\n" +
                "Thank you for joining us in this journey towards a more democratic and empowered society.\n\n" +
                "Current Date and Time: " + formattedDateTime + "\n\n" +
                "Best regards,\nThe E-choice Team";





        System.out.println("--Enrollment saved to the database");

            // Try block to check for exceptions
            try {

                // Creating a simple mail message
                SimpleMailMessage mailMessage
                        = new SimpleMailMessage();

                // Setting up necessary details
                mailMessage.setFrom(sender);
                mailMessage.setTo(email);
                mailMessage.setText(onboardingMessage);
                mailMessage.setSubject("Enrollment for E-choice");

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
