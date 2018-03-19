package jahedmanesh.lib.navigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Hashtable;
import java.util.List;

import jahedmanesh.lib.navigation.item.NavItem;
import jahedmanesh.lib.navigation.item.NavMenuItem;

public class NavigationView extends RelativeLayout implements ProfileManager.ProfileEventChanged {

    public static final int CONTENT_GRAVITY_LEFT = 100;
    public static final int CONTENT_GRAVITY_RIGHT = 200;
    public static final int CONTENT_GRAVITY_AUTO = 300;

    public static final int LIST_TYPE_ITEM = 1;
    public static final int LIST_TYPE_PROFILE = 2;

    private int contentGravity;

    private HeaderView headerView;
    private RecyclerView containerItems;
    private RecyclerView containerProfiles;
    private FrameLayout navigationFooter;

    private ItemAdapter itemAdapter;
    private ProfileAdapter profileAdapter;

    private Hashtable<Long, NavItem> itemHashTable;

    public NavigationView(Context context) {
        this(context, null, 0);
    }

    public NavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // Load attributes
        final TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.NavigationView, defStyle, 0);

        contentGravity = typedArray.getInt(R.styleable.NavigationView_contentGravity, CONTENT_GRAVITY_AUTO);

        typedArray.recycle();

        setup();
    }

    private void setup() {
        View navigationView = LayoutInflater.from(getContext()).inflate(R.layout.jahedmanesh_view_navigation, this, true);

        headerView = navigationView.findViewById(R.id.navigationHeader);
        headerView.setup(contentGravity);

        containerItems = navigationView.findViewById(R.id.containerItems);
        containerProfiles = navigationView.findViewById(R.id.containerProfiles);

        itemHashTable = new Hashtable<>();

        // Set NavItem to adapter
        itemAdapter = new ItemAdapter(contentGravity );
        containerItems.setAdapter(itemAdapter);

        // Set Profile to adapter
        profileAdapter = new ProfileAdapter(contentGravity);
        containerProfiles.setAdapter(profileAdapter);

        navigationFooter = navigationView.findViewById(R.id.navigationFooter);
    }

    public int getContentGravity() {
        return contentGravity;
    }

    // ITEM

    public void setMenuItemCallback(MenuListCallback menuItemCallback) {
        itemAdapter.setMenuItemCallback(menuItemCallback);
    }

    public void replaceItems(List<NavItem> items) {
        itemHashTable.clear();
        for (NavItem item : items) {
            itemHashTable.put(item.getId(), item);
        }
        itemAdapter.replace(items);
    }

    public void refreshItems() {
        itemAdapter.refresh();
    }

    public void removeItem(@NonNull NavItem item) {
        if (itemAdapter.remove(item)) {
            itemHashTable.remove(item.getId());
        }
    }

    public void removeItem(int position) {
        if (position >= 0) {
            NavItem removedItem = itemAdapter.remove(position);
            itemHashTable.remove(removedItem.getId());
        }
    }

    @Nullable
    public NavItem getItem(long id) {
        return itemHashTable.get(id);
    }

    // HEADER
    public void enableHeader() {
        ProfileManager.getInstance().removeAllProfileEventChanged();
        ProfileManager.getInstance().addProfileEventChanged(this);
        ProfileManager.getInstance().addProfileEventChanged(headerView);
        ProfileManager.getInstance().refresh();
    }

    public void setProfileListCallback(ProfileListCallback profileListCallback) {
        profileAdapter.setProfileListCallback(profileListCallback);
    }

    public void setProfileImageLoaderCallBack(ProfileImageLoaderCallBack profileImageLoaderCallBack) {
        profileAdapter.setProfileImageLoaderCallBack(profileImageLoaderCallBack);
        headerView.setProfileImageLoaderCallBack(profileImageLoaderCallBack);
    }

    public void setProfileHeaderCallback(ProfileHeaderCallback profileHeaderCallback) {
        headerView.setProfileHeaderCallback(profileHeaderCallback);
    }

    @Override
    public void defaultProfileChanged(ProfileManager profileManager, Profile profile) {
        profileAdapter.replace(profileManager.getProfileItemsExceptDefault());
    }

    @Override
    public void defaultProfileRemoved(ProfileManager profileManager, Profile profile) {
        profileAdapter.replace(profileManager.getProfileItemsExceptDefault());
    }

    @Override
    public void profileRemoved(ProfileManager profileManager, Profile profile) {
        profileAdapter.replace(profileManager.getProfileItemsExceptDefault());
    }

    @Override
    public void refresh(ProfileManager profileManager) {
        profileAdapter.replace(profileManager.getProfileItemsExceptDefault());
    }

    public  boolean isMenuListShown(){
        return containerItems.getVisibility() == VISIBLE;
    }

    public  boolean isProfileListShown(){
        return containerProfiles.getVisibility() == VISIBLE;
    }

    @IntDef(value = {LIST_TYPE_ITEM,LIST_TYPE_PROFILE})
    @Retention(RetentionPolicy.SOURCE)
    @interface ListType{
    }

    public void switchTo(@ListType int type){
        if (type == LIST_TYPE_ITEM) {
            containerItems.setVisibility(VISIBLE);
            containerProfiles.setVisibility(GONE);
        }else {
            containerItems.setVisibility(GONE);
            containerProfiles.setVisibility(VISIBLE);
        }
    }

    // Footer

    public void setFooter(View footer) {
        navigationFooter.removeAllViews();
        navigationFooter.addView(footer);
    }

    public void showFooter() {
        navigationFooter.setVisibility(VISIBLE);
    }

    public void hideFooter(){
        navigationFooter.setVisibility(GONE);
    }

    public interface MenuListCallback {
        void onMenuItemClicked(View row, NavMenuItem item, int position);
        void onMenuItemLongClicked(View row, NavMenuItem item, int position);
    }

    public interface ProfileListCallback {
        void onProfileItemClicked(View row, Profile item, int position);
        void onProfileItemLongClicked(View row, Profile item, int position);
    }

    public interface ProfileImageLoaderCallBack{
        void onProfileCoverLoading(ImageView coverView, Profile profileItem);
        void onProfileAvatarLoading(ImageView avatarView, Profile profileItem);
    }

    public interface ProfileHeaderCallback {
        void onProfileClicked(Profile item);
        void onProfileLongClicked(Profile item);
        void onArrowClicked(View v);
    }

}
