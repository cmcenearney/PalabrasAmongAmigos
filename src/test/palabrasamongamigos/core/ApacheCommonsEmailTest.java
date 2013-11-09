package palabrasamongamigos.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class ApacheCommonsEmailTest {

    ApacheCommonsEmail email = new ApacheCommonsEmail("this is the message body - it works!", "testing!", "colinmcenearney@gmail.com");

    @Test
    public void mandrillEnvVarsPresent(){
        assertTrue( email.getMandrillAPIKey() != null );
        assertTrue( email.getMandrillUserName() != null );
    }

    @Test
    public void testEmail(){
        email.sendEmailMandrill();
    }

}
