package sv.pft.addressbook.tests;

import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;

/**
 * Created by Ltana on 28.02.2016.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactsHelper().initModificationContact();
        app.getContactsHelper().fillContactData(new ContactData("newName", "newSurname", "newTown, Street 2", "+111111111111", "name.surname@test.com", null), false);
        app.getContactsHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
    }

}
