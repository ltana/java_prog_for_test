package sv.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Ltana on 28.02.2016.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        if (!app.getContactsHelper().isThereAContact()) {
            app.getContactsHelper()
                    .createContact(new ContactData("Name", "SurnameDel", "Town, Street 2", "+111111111111", "name.surname@test.com", "[не вибрано]"));
            app.getNavigationHelper().gotoHomePage();
        }
        List<ContactData> before = app.getContactsHelper().getContactList();
        app.getContactsHelper().initModificationContact();
        ContactData contact = new ContactData(before.get(0).getId(), "newName", "newSurname", "newTown, Street 2", "+111111111111", "name.surname@test.com", null);
        app.getContactsHelper().fillContactData(contact, false);
        app.getContactsHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactsHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.get(0));
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);

    }
}
