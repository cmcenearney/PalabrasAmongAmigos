package palabrasamongamigos.core;


import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;
       /*
       mandrill
Host smtp.mandrillapp.com
Port 587
SMTP Username colinmcenearney@yahoo.com
SMTP Password any valid API key
Keys:  yEJeVnKnVKgrBW5sb6istg
*/


public class Email2 {

    String toAddress = "colinmcenearney@gmail.com";
    String messageBody = "IT WORKS!";
    String username = "colinmcenearney@yahoo.com";
    String password = "emuk_vVmpLa4ycii1zwnnw";
    String from = "admin@palabrasamongamigos.net";

    public Email2(){}

    public Email2(String toAddress, String messageBody) {
        this.toAddress = toAddress;
        this.messageBody = messageBody;

    }

    public void sendEmail() {
        Properties mailProperties = new Properties();
        mailProperties.setProperty("mail.transport.protocol", "smtp");
        mailProperties.setProperty("mail.smtp.host", "smtp.mandrillapp.com ");
        mailProperties.setProperty("mail.smtp.port", String.valueOf(587));
        mailProperties.setProperty("mail.smtp.user", username);
        mailProperties.setProperty("mail.smtp.auth", "true");
        //Session session = Session.getDefaultInstance(mailProperties, null);
        final Session session = Session.getInstance(mailProperties, new Authenticator() {

            @Override protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password); }
        });
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("admin@palabrasamongamigos.net"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress("colinmcenearney@gmail.com"));
            msg.setSubject("IT WORKS!!!");
            msg.setText(messageBody);
            Transport.send(msg);

        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        }

    }

    public  void sendEmail2() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mandrillapp.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toAddress));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void useGmail() {

        final String username = "colinmcenearney@gmail.com";
        final String password = "crk&NY1^%@";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("colinmcenearney@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("colinmcenearney@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void useGmailSSL() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("colinmcenearney@gmail.com","crk&NY1^%@");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("colinmcenearney@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("colinmcenearney@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler," +
                    "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}


/*
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailSSL {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("username","password");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@no-spam.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("to@no-spam.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
*/