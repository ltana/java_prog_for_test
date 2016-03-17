package sv.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;

import java.util.List;

/**
 * Created by Ltana on 28.02.2016.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        if (! app.getContactsHelper().isThereAContact()) {
            app.getContactsHelper()
                    .createContact(new ContactData("Name", "SurnameDel", "Town, Street 2", "+111111111111", "name.surname@test.com", "[не вибрано]"));
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactsHelper().getContactList();
        app.getContactsHelper().initModificationContact();
        app.getContactsHelper().fillContactData(new ContactData("newName", "newSurname", "newTown, Street 2", "+111111111111", "name.surname@test.com", null), false);
        app.getContactsHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactsHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }
}
