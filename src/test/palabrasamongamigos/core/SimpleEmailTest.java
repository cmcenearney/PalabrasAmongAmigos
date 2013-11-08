package palabrasamongamigos.core;

import junit.framework.Test;

public class SimpleEmailTest {
    SimpleEmail emailer = new SimpleEmail();

    @org.junit.Test
    public void testEmail(){
        emailer.sendMail();
    }
}
