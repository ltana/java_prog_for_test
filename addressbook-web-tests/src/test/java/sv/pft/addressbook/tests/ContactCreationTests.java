package sv.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContactsHelper().getContactList();
        app.getContactsHelper().createContact(new ContactData("CrName", "Surname", "Town, Street 2", "+111111111111", "name.surname@test.com", "test1"));
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactsHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}
