package sv.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import sv.pft.addressbook.Model.ContactData;
import sv.pft.addressbook.Model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void initModificationContact(int id) {
        click(By.cssSelector("a[href='edit.php?id=" + id + "']"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    private void selectById(int id) {
        wd.findElement(By.cssSelector("input[id = '" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//*[@id='content']/form[2]/div[2]/input[@type='button']"));
    }

    public void create(ContactData contact) {
        initAddContact();
        fillContactData(contact, true);
        saveContact();
        contactCache = null;
    }

    public void delete(ContactData contact) throws InterruptedException {
        selectById(contact.getId());
        deleteSelectedContact();
        acceptAlert();
        Thread.sleep(1000);
        contactCache = null;
    }

    public void modify(ContactData contact) {
        initModificationContact(contact.getId());
        fillContactData(contact, false);
        submitContactModification();
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("[name=entry]"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.cssSelector("input")).getAttribute("id"));
            String name = element.findElement(By.cssSelector("[name=entry]>td:nth-of-type(3)")).getText();
            String lastname = element.findElement(By.cssSelector("[name=entry]>td:nth-of-type(2)")).getText();
            contactCache.add(new ContactData().withId(id).withName(name).withLastname(lastname));
            //System.out.println("contact1 = " + id + name + lastname);
        }
        return new Contacts(contactCache);
    }
}
