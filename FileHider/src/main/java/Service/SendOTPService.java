package Service;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendOTPService {

    // Constructor
    public SendOTPService() {
    }

    // Method to send OTP
    public static void sendOTP(String email, String genOTP) {
        String to = email; // Recipient's email
        String from = "gaganshenvi7@gmail.com"; // Sender's email
         // Sender's email password (use app-specific password for Gmail)
        String host = "smtp.gmail.com"; // SMTP server

        // Set properties for the mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Create a new session with an authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "gxca oosc gnup zfva");
            }
        });

        session.setDebug(true); // Enable debug mode

        try {
            // Create a new email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from)); // Set the sender
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // Add recipient
            message.setSubject("File Enc OTP"); // Set subject
            message.setText("Your One-Time Password for the File Enc app is: " + genOTP); // Set message body

            // Send the email
            System.out.println("Sending...");
            Transport.send(message);
            System.out.println("Sent message successfully...");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
