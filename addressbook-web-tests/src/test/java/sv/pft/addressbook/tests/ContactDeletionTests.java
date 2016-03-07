package sv.pft.addressbook.tests;

import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;


public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion() {
        if (! app.getContactsHelper().isThereAContact()) {
            app.getNavigationHelper().gotoAddContact();
            app.getContactsHelper()
                    .createContact(new ContactData("Name", "SurnameDel", "Town, Street 2", "+111111111111", "name.surname@test.com", "[не вибрано]"));
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactsHelper().selectContact();
        app.getContactsHelper().deleteSelectedContact();
        app.getContactsHelper().acceptAlert();
    }
}
