package palabrasamongamigos.core;


import org.junit.Test;

public class EmailTest {

    Email2 emailer = new Email2();

    @Test
    public void testEmail(){
        emailer.sendEmail();
    }

    @Test
    public void testEmail2(){
        emailer.useGmailSSL();
    }
}
