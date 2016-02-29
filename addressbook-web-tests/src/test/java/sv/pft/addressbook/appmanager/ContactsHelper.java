package sv.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import sv.pft.addressbook.Model.ContactData;

public class ContactsHelper extends HelperBase{

    public ContactsHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void saveContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactData(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
    }

    public void initModificationContact() {
        click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//*[@id='content']/form[2]/div[2]/input"));
    }
}
