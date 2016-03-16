package sv.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;
import sv.pft.addressbook.Model.GroupData;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;


public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion() throws InterruptedException {
        if (! app.getContactsHelper().isThereAContact()) {
            app.getNavigationHelper().gotoGroupPage();
            if (! app.getGroupsHelper().isThereAGroup()) {
                app.getGroupsHelper().createGroup(new GroupData("test1", "test2", "test3"));
            }
            app.getContactsHelper()
                    .createContact(new ContactData("Name", "SurnameDel", "Town, Street 2", "+111111111111", "name.surname@test.com", "[не вибрано]"));
            app.getNavigationHelper().gotoHomePage();
        }
        int before = app.getContactsHelper().getContactCount();
        app.getContactsHelper().selectContact(before - 1);
        app.getContactsHelper().deleteSelectedContact();
        app.getContactsHelper().acceptAlert();
        Thread.sleep(1000);
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactsHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }
}
