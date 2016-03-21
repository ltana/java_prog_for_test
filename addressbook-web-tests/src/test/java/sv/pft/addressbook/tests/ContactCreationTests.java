package sv.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData().withName("CrName")
                .withLastname("Surname").withAddress("Town, Street 2")
                .withMobile("+111111111111").withEmail("name.surname@test.com").withGroup("[не вибрано]");
        app.contact().create(contact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
