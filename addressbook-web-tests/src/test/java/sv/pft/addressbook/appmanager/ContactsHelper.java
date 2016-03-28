package sv.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import sv.pft.addressbook.Model.ContactData;
import sv.pft.addressbook.Model.Contacts;

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
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail1());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        if (creating) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initAddContact() {
        click(By.linkText("add new"));
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




    public int getContactId(String name) {
        int id =  Integer.parseInt(wd.findElement(By.xpath(".//*[@id='maintable']/tbody/tr[td = '" + name + "']/td[1]/input")).getAttribute("id"));
        return id;
    }

    public void initViewContact(int id) {
        click(By.cssSelector("a[href='view.php?id=" + id + "']"));
    }

    public String infoFromDetailedForm() {
        String element = wd.findElement(By.id("content")).getText();
        return element;
    }

    public ContactData infoFromEditFormForContactName(String name) {
        initModificationContact(getContactId(name));
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lasttname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(getContactId(name)).withName(firstname).withLastname(lasttname)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
                .withAddress(address);
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
            String allPhones = element.findElement(By.cssSelector("[name=entry]>td:nth-of-type(6)"))
                    .getText();
            String address = element.findElement(By.cssSelector("[name=entry]>td:nth-of-type(4)")).getText();
            String allEmails = element.findElement(By.cssSelector("[name=entry]>td:nth-of-type(5)"))
                    .getText();
            contactCache.add(new ContactData().withId(id).withName(name).withLastname(lastname)
            .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
           // System.out.println("contact1 = " + allEmails);
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initModificationContact(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lasttname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(firstname).withLastname(lasttname)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone).withAddress(address)
                .withEmail1(email1).withEmail2(email2).withEmail3(email3);
    }
}
