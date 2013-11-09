package palabrasamongamigos.core;

import org.apache.commons.mail.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ApacheCommonsEmail {

    private String msgBody;
    private String msgSubject;
    private String msgToAddress;
    private String mandrillAPIKey;
    private String mandrillUserName;

    public ApacheCommonsEmail(String msgBody, String subject, String toAddress){
        this.msgBody = msgBody;
        this.msgSubject = subject;
        this.msgToAddress = toAddress;
        if (System.getenv("MANDRILL_APIKEY") != null){
            mandrillAPIKey =  System.getenv("MANDRILL_APIKEY");
            mandrillUserName = System.getenv("MANDRILL_USERNAME");
        }
        else {
            Properties props = new Properties();
            InputStream is = null;
            try {
                File f = new File("src/main/resources/do_not_upload.properties");
                is = new FileInputStream( f );
                props.load( is );
            }
            catch ( Exception e ) { is = null; }

            mandrillAPIKey = props.getProperty("mandrillAPIKey");
            mandrillUserName = props.getProperty("mandrillUserName");
        }

    }

    public void sendEmailMandrill()  {
        Email email = new SimpleEmail();
        email.setHostName("smtp.mandrillapp.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(mandrillUserName, mandrillAPIKey));
        email.setSSLOnConnect(true);
        try {
        email.setFrom("admin@palabrasamongamigos.net");
        email.setSubject(msgSubject);
        email.setMsg(msgBody);
        email.addTo(msgToAddress);
        email.send();
        } catch (EmailException ex){
            ex.printStackTrace();
        }
    }


    public String getMandrillAPIKey() {
        return mandrillAPIKey;
    }

    public String getMandrillUserName() {
        return mandrillUserName;
    }


}
