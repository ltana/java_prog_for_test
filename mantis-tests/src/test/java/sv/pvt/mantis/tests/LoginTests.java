package sv.pvt.mantis.tests;

import org.testng.annotations.Test;
import sv.pvt.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by Ltana on 20.04.2016.
 */
public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.IsLoggedInAs("administrator"));
    }
}
