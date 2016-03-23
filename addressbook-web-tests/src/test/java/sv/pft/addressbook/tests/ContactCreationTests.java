package sv.pft.addressbook.tests;

import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;
import sv.pft.addressbook.Model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withName("CrName")
                .withLastname("Surname").withAddress("Town, Street 2")
                .withMobile("+111111111111").withEmail("name.surname@test.com").withGroup("[не вибрано]");
        app.contact().create(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
