package sv.pft.addressbook.tests;

import org.testng.annotations.Test;
import sv.pft.addressbook.Model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupsHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }

}
