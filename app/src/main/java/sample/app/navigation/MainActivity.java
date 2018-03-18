package sample.app.navigation;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jahedmanesh.lib.navigation.NavigationView;
import jahedmanesh.lib.navigation.Profile;
import jahedmanesh.lib.navigation.ProfileManager;
import jahedmanesh.lib.navigation.item.DividerItem;
import jahedmanesh.lib.navigation.item.Item;
import jahedmanesh.lib.navigation.item.MenuItem;

/**
 * Created by Mehdi Jahed Manesh on 2/28/18 at 10:58 PM.
 * Email : 47even@gmail.com
 */

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        drawerLayout.setScrimColor(Color.parseColor("#FF0EDF81"));

        MenuItem menuItem1 = new MenuItem(1, "Hey");
        menuItem1.setIcon(R.drawable.ic_audiotrack_black_24dp);
        MenuItem menuItem2 = new MenuItem(2, "Bye");
        menuItem2.setIcon(R.drawable.ic_backup_black_24dp);
        MenuItem menuItem3 = new MenuItem(3, "Why");
        menuItem3.setIcon(R.drawable.ic_build_black_24dp);
        MenuItem menuItem4 = new MenuItem(4, "Who");
        menuItem4.setIcon(R.drawable.ic_face_black_24dp);

        DividerItem dividerItem1 = new DividerItem(5);
        dividerItem1.setDividerColor(Color.GREEN);
        DividerItem dividerItem2 = new DividerItem(6);
        dividerItem2.setDividerColor(Color.RED);

        List<Item> items = new ArrayList<>();
        items.add(menuItem1);
        items.add(menuItem2);
        items.add(dividerItem1);
        items.add(menuItem3);
        items.add(dividerItem2);
        items.add(menuItem4);

        NavigationView navigation = findViewById(R.id.navigation);
        navigation.setMenuItemCallback(new NavigationView.MenuListCallback() {
            @Override
            public void onMenuItemClicked(View row, MenuItem item, int position) {
                Log.i("====>", "onMenuItemClicked: ");
            }

            @Override
            public void onMenuItemLongClicked(View row, MenuItem item, int position) {
                Log.i("====>", "onMenuItemLongClicked: ");
            }
        });
        navigation.replaceItems(items);

        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(Color.RED);

        Profile profile1 = new Profile(1,"yek");
        profile1.setAvatarIcon(R.drawable.ic_face_black_24dp);
        profile1.setCoverDrawable(colorDrawable);

        colorDrawable = new ColorDrawable();
        colorDrawable.setColor(Color.YELLOW);

        Profile profile2 = new Profile(2,"do");
        profile2.setAvatarIcon(R.drawable.ic_backup_black_24dp);
        profile2.setCoverDrawable(colorDrawable);

        colorDrawable = new ColorDrawable();
        colorDrawable.setColor(Color.GRAY);

        Profile profile3 = new Profile(3,"se");
        profile3.setAvatarIcon(R.drawable.ic_build_black_24dp);
        profile3.setCoverDrawable(colorDrawable);

        colorDrawable = new ColorDrawable();
        colorDrawable.setColor(Color.CYAN);

        Profile profile4 = new Profile(4,"char");
        profile4.setAvatarIcon(R.drawable.ic_audiotrack_black_24dp);
        profile4.setCoverDrawable(colorDrawable);

        colorDrawable = new ColorDrawable();
        colorDrawable.setColor(Color.MAGENTA);

        Profile profile5 = new Profile(5,"pan");
        profile5.setAvatarIcon(R.mipmap.ic_launcher_round);
        profile5.setCoverDrawable(colorDrawable);

        ProfileManager profileManager = ProfileManager.getInstance();

        profileManager.addProfileItem(profile1);
        profileManager.addProfileItem(profile2);
        profileManager.addProfileItem(profile3);
        profileManager.addProfileItem(profile4);
        profileManager.addProfileItem(profile5);

        profileManager.setDefault(profile1);

        navigation.setProfileImageLoaderCallBack(new NavigationView.ProfileImageLoaderCallBack() {
            @Override
            public void onProfileCoverLoading(ImageView coverView, Profile profileItem) {
                if (profileItem.getCoverDrawable() != null) {
                    coverView.setImageDrawable(profileItem.getCoverDrawable());
                }
            }

            @Override
            public void onProfileAvatarLoading(ImageView avatarView, Profile profileItem) {
                avatarView.setImageResource(profileItem.getAvatarIcon());
            }
        });
        navigation.setProfileHeaderCallback(new NavigationView.ProfileHeaderCallback() {
            @Override
            public void onProfileClicked(Profile item) {
                profileManager.setDefault(item);
            }

            @Override
            public void onProfileLongClicked(Profile item) {

            }

            @Override
            public void onArrowClicked(View v) {
                if (navigation.isMenuListShown()) {
                    navigation.switchTo(NavigationView.LIST_TYPE_PROFILE);
                } else {
                    navigation.switchTo(NavigationView.LIST_TYPE_ITEM);
                }
            }
        });
        navigation.setProfileListCallback(new NavigationView.ProfileListCallback() {
            @Override
            public void onProfileItemClicked(View row, Profile item, int position) {
                profileManager.setDefault(item);
            }

            @Override
            public void onProfileItemLongClicked(View row, Profile item, int position) {

            }
        });
        navigation.enableHeader();

        TextView footerView = (TextView) getLayoutInflater().inflate(R.layout.layout_footer, null);
        footerView.setText("Footer");
        navigation.setFooter(footerView);

    }

}
