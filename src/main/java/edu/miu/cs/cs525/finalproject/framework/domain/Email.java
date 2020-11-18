package edu.miu.cs.cs525.finalproject.framework.domain;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.InputStream;
import java.util.Properties;

public class Email implements Observer {
    private Account subject;

    public Email(Account account) {
        this.subject = account;
        account.registerObserver(this);
    }


    public void update(String txtMessage) {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            props.load(input);
            String userName = props.getProperty("email.account");
            String password = props.getProperty("email.password");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(subject.getCustomer().getEmailAddress()));
            message.setSubject("Your account balance changed.");


            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(txtMessage, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
