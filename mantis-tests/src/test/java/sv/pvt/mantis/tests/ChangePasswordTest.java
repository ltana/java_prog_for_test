package sv.pvt.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import sv.pvt.mantis.model.MailMessage;
import sv.pvt.mantis.model.Users;
import sv.pvt.mantis.model.UsersData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Ltana on 24.04.2016.
 */
public class ChangePasswordTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        String password = "p8";
        app.registration().login("administrator", "root");
        Users users = app.db().users();
        for (UsersData user : users) {
            CharSequence str = "user";
            if (user.getName().contains(str)) {
                int id = user.getId();
                String username = user.getName();
                String email = user.getEmail();
                System.out.println("Change password for user with id = " + id + ", name = " + username);
                app.registration().initChangePassword(id);
                List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
                String confirmationLink = findConfirmationLink(mailMessages, email);
                app.registration().finish(confirmationLink, password);
                assertTrue(app.newSession().login(username, password));
                return;
            } else {
                System.out.println("admin");
            }
        }
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email))
                .findFirst().get();
        VerbalExpression regex = VerbalExpression.regex()
                .find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
