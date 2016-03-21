package sv.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;
import sv.pft.addressbook.Model.GroupData;

import java.util.Set;


public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().groupPage();
            if (app.group().all().size() == 0) {
                app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
            }
            app.contact()
                    .create(new ContactData().withName("CrName")
                            .withLastname("Surnamedel").withAddress("Town, Street 2")
                            .withMobile("+111111111111").withEmail("name.surname@test.com").withGroup("[не вибрано]"));
            app.goTo().homePage();
        }
    }


    @Test
    public void testContactDeletion() throws InterruptedException {
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }


}
