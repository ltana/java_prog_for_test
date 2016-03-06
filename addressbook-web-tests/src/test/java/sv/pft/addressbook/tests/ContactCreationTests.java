package sv.pft.addressbook.tests;

import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoAddContact();
        app.getContactsHelper()
                .fillContactData(new ContactData("Name", "Surname", "Town, Street 2", "+111111111111", "name.surname@test.com", "test1"), true);
        app.getContactsHelper().saveContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
