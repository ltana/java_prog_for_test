package sv.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;
import sv.pft.addressbook.Model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContactsHelper().getContactList();
        ContactData contact = new ContactData("CrName", "Surname", "Town, Street 2", "+111111111111", "name.surname@test.com", "test1");
        app.getContactsHelper().createContact(contact);
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactsHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        int max = 0;
        for (ContactData c : after) {
            if (c.getId() > max) {
                max = c.getId();
            }
        }
        contact.setId(max);
        before.add(contact);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
