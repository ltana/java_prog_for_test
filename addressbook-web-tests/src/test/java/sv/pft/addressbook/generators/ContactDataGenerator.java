package sv.pft.addressbook.generators;

import sv.pft.addressbook.Model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ltana on 29.03.2016.
 */
public class ContactDataGenerator {
    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getName(), contact.getLastname(),
                    contact.getAddress(), contact.getMobilePhone(), contact.getGroup()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i=0; i<count; i++) {
            contacts.add(new ContactData().withName(String.format("name %s", i))
            .withLastname(String.format("lastname %s", i))
            .withAddress(String.format("address %s", i))
            .withMobilePhone(String.format("mobile phone %s", i))
            .withGroup("[none]"));
        }
       return contacts;
    }
}
