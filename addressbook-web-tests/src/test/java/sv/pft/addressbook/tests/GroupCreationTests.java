package sv.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupsHelper().getGroupCount();
        app.getGroupsHelper().createGroup(new GroupData("test1", "test2", "test3"));
        int after = app.getGroupsHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
    }

}
