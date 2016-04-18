package sv.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;
import sv.pft.addressbook.Model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsCsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line !=null) {
            String[] split = line.split(";");
            list.add(new Object[] {new ContactData().withName(split[0])
                    .withLastname(split[1])
                    .withAddress(split[2])
                    .withMobilePhone(split[3])
                    .withGroup(split[4])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validContactsXml() throws IOException {
       try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
           String xml = "";
           String line = reader.readLine();
           while (line !=null) {
               xml += line;
               line = reader.readLine();
           }
           XStream xstream = new XStream();
           xstream.processAnnotations(ContactData.class);
           List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
           return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
       }
    }

    @DataProvider
    public Iterator<Object[]> validContactsJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json = "";
        String line = reader.readLine();
        while (line !=null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> groups = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test(enabled = false, dataProvider = "validContactsJson")
    public void testContactCreationWithoutPhotoJSON(ContactData contact) {
        Contacts before = app.db().contacts();
        app.goTo().homePage();
        app.contact().create(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

        verifyContactListInUI();
    }

    @Test
    public void testContactCreationWithPhoto() {
        Contacts before = app.db().contacts();
        File photo = new File("src/test/resources/mytests.png");
        ContactData contact = new ContactData()
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
                .withGroup("new test1");
        app.goTo().homePage();
        app.contact().create(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

        verifyContactListInUI();
    }
}
