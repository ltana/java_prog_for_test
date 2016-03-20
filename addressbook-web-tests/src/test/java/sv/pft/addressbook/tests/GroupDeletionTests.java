package sv.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import sv.pft.addressbook.Model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupsHelper().getGroupList();
        if (! app.getGroupsHelper().isThereAGroup()) {
           app.getGroupsHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        app.getGroupsHelper().selectGroup(before.size() - 1);
        app.getGroupsHelper().deleteSelectedGroups();
        app.getGroupsHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupsHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
