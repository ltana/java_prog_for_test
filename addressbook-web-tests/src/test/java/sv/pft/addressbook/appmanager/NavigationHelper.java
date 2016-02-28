package sv.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Ltana on 28.02.2016.
 */
public class NavigationHelper {
    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void gotoGroupPage() {
        wd.findElement(By.linkText("Групи")).click();
    }

    public void gotoAddContact() {
        wd.findElement(By.linkText("Додати контакт")).click();
    }

    public void gotoHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }
}
