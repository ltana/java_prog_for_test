package sv.pvt.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by Ltana on 21.04.2016.
 */
public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }

    public void initChangePassword(int id) {
        click(By.linkText("Manage Users"));
        click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + id + "']"));
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void login(String userName, String password) {
        type(By.name("username"), userName);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }
}
