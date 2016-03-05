package sv.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("Групи"));
    }

    public void gotoAddContact() {
        click(By.linkText("Додати контакт"));
    }

    public void gotoHomePage() {
        click(By.linkText("home page"));
    }
}
