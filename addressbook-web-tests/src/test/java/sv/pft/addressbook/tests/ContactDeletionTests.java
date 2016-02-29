package sv.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion() {
        app.getContactsHelper().selectContact();
        app.getContactsHelper().deleteSelectedContact();
        app.getContactsHelper().acceptAlert();
    }
}
