package sv.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import sv.pft.addressbook.Model.ContactData;
import sv.pft.addressbook.Model.Contacts;
import sv.pft.addressbook.Model.GroupData;
import sv.pft.addressbook.Model.Groups;
import sv.pft.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.debug("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod (alwaysRun = true)
    public void logTestStop(Method m) {
        logger.debug("Stop test " + m.getName());
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((c) -> new ContactData().withId(c.getId())
                            .withName(c.getName())
                            .withLastname(c.getLastname())
                            .withAddress(c.getAddress())).collect(Collectors.toSet())));
            System.out.println("uiContacts " + uiContacts);
            System.out.println("dbContacts " + dbContacts);
        }
    }
/*
    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    };
            //.getAllEmails())));
        }

        /*assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }*/


        /*int id = Integer.parseInt(element.findElement(By.cssSelector("input")).getAttribute("id"));
            String name = element.findElement(By.cssSelector("[name=entry]>td:nth-of-type(3)")).getText();
            String lastname = element.findElement(By.cssSelector("[name=entry]>td:nth-of-type(2)")).getText();
            String allPhones = element.findElement(By.cssSelector("[name=entry]>td:nth-of-type(6)"))
                    .getText();
            String address = element.findElement(By.cssSelector("[name=entry]>td:nth-of-type(4)")).getText();
            String allEmails = element.findElement(By.cssSelector("[name=entry]>td:nth-of-type(5)"))
                    .getText();
            contactCache.add(new ContactData().withId(id).withName(name).withLastname(lastname)
            .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));*/
    }

