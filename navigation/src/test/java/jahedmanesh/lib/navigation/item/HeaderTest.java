package jahedmanesh.lib.navigation.item;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import jahedmanesh.lib.navigation.ProfileManager;
import jahedmanesh.lib.navigation.Profile;

/**
 * Created by Mehdi Jahed Manesh on 3/1/18 at 8:05 PM.
 * Email : 47even@gmail.com
 */
public class HeaderTest {

    @Test
    public void testDuplicateItem() {

        Profile item1 = new Profile(1, "yek");
        Profile item2 = new Profile(2, "do");
        Profile item3 = new Profile(3, "se");
        Profile item4 = new Profile(4, "char");
        Profile item5 = new Profile(5, "pan");

        List<Profile> oldList = new ArrayList<>();
        oldList.add(item1);
        oldList.add(item2);
        oldList.add(item3);
        oldList.add(item4);
        oldList.add(item5);

        ProfileManager header = ProfileManager.getInstance();
        header.setProfileItems(oldList);

        List<Profile> newList = header.getProfileItems();

        Assert.assertTrue(oldList.size() == newList.size());
    }

    @Test
    public void testOrder() {

        Profile item1 = new Profile(1, "yek");
        Profile item2 = new Profile(2, "do");
        Profile item3 = new Profile(3, "se");
        Profile item4 = new Profile(4, "char");
        Profile item5 = new Profile(5, "pan");

        List<Profile> oldList = new ArrayList<>();
        oldList.add(item5);
        oldList.add(item4);
        oldList.add(item3);
        oldList.add(item2);
        oldList.add(item1);

        ProfileManager header = ProfileManager.getInstance();
        header.setProfileItems(oldList);

        List<Profile> newList = header.getProfileItems();

        Profile _item1 = newList.get(0);
        Profile _item2 = newList.get(1);
        Profile _item3 = newList.get(2);
        Profile _item4 = newList.get(3);
        Profile _item5 = newList.get(4);

        Assert.assertTrue(_item1.equals(item5));
        Assert.assertTrue(_item2.equals(item4));
        Assert.assertTrue(_item3.equals(item3));
        Assert.assertTrue(_item4.equals(item2));
        Assert.assertTrue(_item5.equals(item1));
    }

    @Test
    public void testDefault() {

        Profile item1 = new Profile(1, "yek");
        Profile item2 = new Profile(2, "do");
        Profile item3 = new Profile(3, "se");
        Profile item4 = new Profile(4, "char");
        Profile item5 = new Profile(5, "pan");

        List<Profile> oldList = new ArrayList<>();
        oldList.add(item1);
        oldList.add(item2);
        oldList.add(item3);
        oldList.add(item4);
        oldList.add(item5);

        ProfileManager header = ProfileManager.getInstance();
        header.setProfileItems(oldList);

        header.setDefault(item4);
        header.setDefault(item5);
        header.setDefault(item2);
        header.setDefault(item3);
        header.setDefault(item5);

        Assert.assertTrue(item5.isDefault());
        Assert.assertFalse(item1.isDefault());
        Assert.assertFalse(item2.isDefault());
        Assert.assertFalse(item3.isDefault());
        Assert.assertFalse(item4.isDefault());

    }

    @Test
    public void testDefaultIfRemoved() {

        Profile item1 = new Profile(1, "yek");
        Profile item2 = new Profile(2, "do");
        Profile item3 = new Profile(3, "se");
        Profile item4 = new Profile(4, "char");
        Profile item5 = new Profile(5, "pan");

        List<Profile> oldList = new ArrayList<>();
        oldList.add(item1);
        oldList.add(item2);
        oldList.add(item3);
        oldList.add(item4);
        oldList.add(item5);

        ProfileManager header = ProfileManager.getInstance();
        header.setProfileItems(oldList);

        header.setDefault(item4);
        header.setDefault(item5);
        header.setDefault(item2);
        header.setDefault(item3);
        header.setDefault(item5);

        header.removeProfileItem(item5.getId());

        Assert.assertTrue(item1.isDefault());
        Assert.assertFalse(item5.isDefault());
        Assert.assertFalse(item2.isDefault());
        Assert.assertFalse(item3.isDefault());
        Assert.assertFalse(item4.isDefault());

    }


}