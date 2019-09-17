import java.util.Properties;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Transport;

public class MailApp {
    public static void main(String[] args) {
        final String host = "smtp.gmail.com";
        final String username = System.getenv("MAIL_USERNAME"); // the username
        final String password = System.getenv("MAIL_PASSWORD"); // the password
        String recipient = System.getenv("MAIL_USERNAME"); // sending an email to oneself

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", username);
        props.put("mail.smtp.password", password);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuth() {
                return new PasswordAuthentication(username, password);
            }
                 }
        );

        try {
            Message message = new MimeMessage(session);

            InternetAddress recipientAddress = new InternetAddress(recipient);

            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, recipientAddress);

            message.setSubject("JavaMail API test");
            message.setText("Testing 123");

            Transport.send(message);

            System.out.println("Message sent!");

        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
