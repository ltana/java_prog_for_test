package sv.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import sv.pft.addressbook.Model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(WebDriver wd) {
        super(wd);
    }

    public void saveContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactData(ContactData contactData, boolean creating) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
        if (creating) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initAddContact() {
        click(By.linkText("Додати контакт"));
    }

    public void initModificationContact() {
        click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//*[@id='content']/form[2]/div[2]/input[@type='button']"));
    }

    public void create(ContactData contact) {
        initAddContact();
        fillContactData(contact, true);
        saveContact();
    }

    public void delete(int index) throws InterruptedException {
        select(index);
        deleteSelectedContact();
        acceptAlert();
        Thread.sleep(1000);
    }

    public void modify(ContactData contact) {
        initModificationContact();
        fillContactData(contact, false);
        submitContactModification();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("[name=entry]"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.cssSelector("input")).getAttribute("id"));
            String name = element.findElement(By.cssSelector("[name=entry]>td:nth-of-type(3)")).getText();
            String lastname = element.findElement(By.cssSelector("[name=entry]>td:nth-of-type(2)")).getText();
            contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname));
            //System.out.println("contact1 = " + id + name + lastname);
        }
        return contacts;
    }
}
