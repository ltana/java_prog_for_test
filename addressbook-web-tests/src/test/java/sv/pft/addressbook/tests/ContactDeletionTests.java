package sv.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;
import sv.pft.addressbook.Model.Contacts;
import sv.pft.addressbook.Model.GroupData;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;


public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create
                        (new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
            }
            app.goTo().homePage();
            File photo = new File("src/test/resources/mytests.png");
            app.contact()
                    .create(new ContactData()
                            .withName("CrNamePhoto")
                            .withLastname("Surname")
                            .withAddress("Town, Street 2")
                            .withMobilePhone("+111111111111")
                            .withHomePhone("+2")
                            .withWorkPhone("+3")
                            .withEmail1("name.surname@test.com")
                            .withEmail2("asdasd")
                            .withEmail3("asdasdfdgd")
                            .withPhoto(photo)
                           // .withGroup("new test1")
                    );
            app.goTo().homePage();
        }
    }


    @Test
    public void testContactDeletion() throws InterruptedException {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.goTo().homePage();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        assertEquals(app.contact().count(), before.size() - 1);
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(deletedContact)));

        verifyContactListInUI();
    }
}
