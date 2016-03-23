package sv.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;
import sv.pft.addressbook.Model.Contacts;
import sv.pft.addressbook.Model.GroupData;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Ltana on 28.02.2016.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().groupPage();
            if (app.group().all().size() == 0) {
                app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
            }
            app.contact()
                    .create(new ContactData().withName("CrName")
                            .withLastname("Surname").withAddress("Town, Street 2")
                            .withMobile("+111111111111").withEmail("name.surname@test.com").withGroup("[не вибрано]"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("CrName")
                .withLastname("Surname").withAddress("Town, Street 2")
                .withMobile("+111111111111").withEmail("name.surname@test.com");
        app.contact().modify(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
