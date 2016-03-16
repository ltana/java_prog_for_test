package sv.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        int before = app.getContactsHelper().getContactCount();
        app.getContactsHelper().createContact(new ContactData("CrName", "Surname", "Town, Street 2", "+111111111111", "name.surname@test.com", "test1"));
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactsHelper().getContactCount();
        Assert.assertEquals(after, before + 1);
    }
}
