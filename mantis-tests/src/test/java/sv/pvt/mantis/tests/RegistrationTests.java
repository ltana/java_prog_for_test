package sv.pvt.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by Ltana on 21.04.2016.
 */
public class RegistrationTests extends TestBase{

    @Test
    public void testRegistration() {
        app.registration().start("user1", "user1@localhost.localdomain");
    }
}
