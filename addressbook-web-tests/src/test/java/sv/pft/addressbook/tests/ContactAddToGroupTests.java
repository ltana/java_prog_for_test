package sv.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.ContactData;
import sv.pft.addressbook.Model.Contacts;
import sv.pft.addressbook.Model.GroupData;
import sv.pft.addressbook.Model.Groups;

import java.io.File;

/**
 * Created by Ltana on 28.02.2016.
 */
public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create
                        (new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
            }
            app.goTo().homePage();
            Groups groups = app.db().groups();
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
                            .inGroup(groups.iterator().next())
            );
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactAddToGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();

        for (ContactData contact : contacts) {

        Groups contactGroups = contact.getGroups();
           // int groupID = contactGroups.getId();

            for (GroupData group : groups) {
                System.out.println("enter sec for");
              if (contactGroups.contains(group)) {}
                else {
                  contact.inGroup(group);
                  System.out.println("not contains");
                  //CORRECT METHOD INGROUP()

              }

                       //contact.getGroups().equals((g) -> groups);
            /*if (contact.getGroups().equals(groups)){

            } else {

            }*/


           }
            System.out.println(contact.getGroups());
            System.out.println(groups);
            System.out.println(contactGroups);
        }


        /*inGroup:
  public ContactData inGroup(GroupData group) {
    if (groups == null) {
      groups = new HashSet<GroupData>();
    }
    groups.add(group);
    return this;
  }*/


        /* assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((c) -> new ContactData().withId(c.getId())
                            .withName(c.getName())
                            .withLastname(c.getLastname())
                            .withAddress(c.getAddress())).collect(Collectors.toSet())));*/

        /*List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }*/

        app.goTo().homePage();

        app.goTo().homePage();
       // assertEquals(app.contact().count(), before.size());
        //Contacts after = app.db().contacts();

       // assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

       // verifyContactListInUI();
    }
}
