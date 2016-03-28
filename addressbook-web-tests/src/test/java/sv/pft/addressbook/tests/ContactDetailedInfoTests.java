package sv.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Ltana on 27.03.2016.
 */
public class ContactDetailedInfoTests extends TestBase {

    @Test
    public void testContactDetailedInfo() {
        ContactData createdContact = new ContactData().withName("DetailedName")
                .withLastname("DetailedSurname").withAddress("DetailedH Town, Street 2, 4/3")
                .withMobilePhone("+111111111111").withHomePhone("+8 (456)").withEmail1("")
                .withGroup("[none]");
        app.contact().create(createdContact);
        app.goTo().homePage();

        int createdContactId = app.contact().getContactId("DetailedName");
        app.contact().initViewContact(createdContactId);
        String detailedData = app.contact().infoFromDetailedForm();
        app.goTo().homePage();

        ContactData contactInfoFromEditForm = app.contact().infoFromEditFormForContactName("DetailedName");

        assertThat(cleaned(detailedData), equalTo(mergeContactData(contactInfoFromEditForm)));
        System.out.println("info " + createdContactId + "         and " +  contactInfoFromEditForm.getId());
    }

    private String mergeContactData(ContactData contact) {
        return Arrays.asList
                (contact.getName(), contact.getLastname(), contact.getAddress(), contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactDetailedInfoTests::cleaned)
                .collect(Collectors.joining(""));
    }

    public static String cleaned(String data) {
        return data.replaceAll("H:", "").replaceAll("\\s", "").replaceAll("M:", "");
    }
}
