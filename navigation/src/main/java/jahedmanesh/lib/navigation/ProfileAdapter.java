package jahedmanesh.lib.navigation;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mehdi Jahed Manesh on 2/28/18 at 5:57 PM.
 * Email : 47even@gmail.com
 */


public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Profile> profiles = new ArrayList<>();
    private int contentGravity;
    @Nullable
    private NavigationView.ProfileListCallback profileListCallback;
    @Nullable
    private NavigationView.ProfileImageLoaderCallBack profileImageLoaderCallBack;

    public ProfileAdapter(
            int contentGravity) {
        this.contentGravity = contentGravity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layout = 0;

        switch (contentGravity) {
            case NavigationView.CONTENT_GRAVITY_AUTO:
                layout = R.layout.jahedmanesh_item_profile_auto;
                break;
            case NavigationView.CONTENT_GRAVITY_LEFT:
                layout = R.layout.jahedmanesh_item_profile_left;
                break;
            case NavigationView.CONTENT_GRAVITY_RIGHT:
                layout = R.layout.jahedmanesh_item_profile_right;
                break;
        }

        return new ProfileItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Profile profileItem = profiles.get(position);

        ((ProfileItemViewHolder) holder).tvTitle.setText(profileItem.getTitle());

        if (profileImageLoaderCallBack != null) {
            profileImageLoaderCallBack.onProfileAvatarLoading(((ProfileItemViewHolder) holder).imgIcon, profileItem);
        }

        ((ProfileItemViewHolder) holder).itemView.setOnClickListener(row -> {
            if (profileListCallback != null) {
                profileListCallback.onProfileItemClicked(row, profileItem, position);
            }
        });

        ((ProfileItemViewHolder) holder).itemView.setOnLongClickListener(row -> {
            if (profileListCallback != null) {
                profileListCallback.onProfileItemLongClicked(row, profileItem, position);
            }
            return true;
        });

    }

    @Override
    public int getItemCount() {
        return profiles != null ? profiles.size() : 0;
    }

    public void replace(List<Profile> profiles) {
        this.profiles.clear();
        this.profiles.addAll(profiles);
        refresh();
    }

    public boolean remove(Profile profile) {
        boolean isRemoved = profiles.remove(profile);
        refresh();
        return isRemoved;
    }

    public Profile remove(int position) {
        Profile removedItem = profiles.remove(position);
        refresh();
        return removedItem;
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public void setProfileListCallback(@Nullable NavigationView.ProfileListCallback profileListCallback) {
        this.profileListCallback = profileListCallback;
    }

    public void setProfileImageLoaderCallBack(@Nullable NavigationView.ProfileImageLoaderCallBack profileImageLoaderCallBack) {
        this.profileImageLoaderCallBack = profileImageLoaderCallBack;
    }

    public class ProfileItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imgIcon;
        TextView tvTitle;

        public ProfileItemViewHolder(View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }

    }

}
