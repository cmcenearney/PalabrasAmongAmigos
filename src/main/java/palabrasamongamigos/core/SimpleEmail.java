package palabrasamongamigos.core;

import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.*;
import org.codemonkey.simplejavamail.*;

public class SimpleEmail {

    public SimpleEmail() {}

    public void sendMail(){
    final Email email = new Email();

    email.setFromAddress("Palabras", "colinmcenearney@gmail.com");
    email.setSubject("test");
    email.addRecipient("Colin", "colinmcenearney@gmail.com", Message.RecipientType.TO);
    email.setText("Must... work... ;)");
    email.setTextHTML("PLEASE WORK!");


    new Mailer("smtp.gmail.com", 587, "colinmcenearney@gmail.com", "crk&NY1^%@").sendMail(email);
    }
}
