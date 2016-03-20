package sv.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;
import sv.pft.addressbook.Model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Ltana on 28.02.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().list().size() == 0) {
            app.goTo().groupPage();
            if (app.group().list().size() == 0) {
                app.group().create(new GroupData("test1", "test2", "test3"));
            }
            app.contact()
                    .create(new ContactData("Name", "SurnameDel", "Town, Street 2", "+111111111111", "name.surname@test.com", "[не вибрано]"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData(before.get(0).getId(), "newName", "newSurname", "newTown, Street 2", "+111111111111", "name.surname@test.com", null);
        app.contact().modify(contact);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.get(0));
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);

    }


}
