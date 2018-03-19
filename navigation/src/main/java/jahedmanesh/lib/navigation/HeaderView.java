package jahedmanesh.lib.navigation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class HeaderView extends FrameLayout implements ProfileManager.ProfileEventChanged {

    private ImageView imgAvatarMain;
    private ImageView imgAvatarSecond;
    private ImageView imgAvatarThird;

    private ImageView imgCover;
    private ImageView imgArrow;
    private TextView tvTitle;
    private TextView tvSubTitle;

    private Profile mainProfile;
    private Profile secondProfile;
    private Profile thirdProfile;

    private NavigationView.ProfileImageLoaderCallBack profileImageLoaderCallBack;
    private NavigationView.ProfileHeaderCallback profileHeaderCallback;

    public HeaderView(Context context) {
        this(context, null, 0);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setup(int contentGravity) {

        View headerView = null;

        switch (contentGravity) {

            case NavigationView.CONTENT_GRAVITY_LEFT:
                headerView = LayoutInflater.from(getContext()).inflate(R.layout.jahedmanesh_profile_header_left, this, true);
                break;

            case NavigationView.CONTENT_GRAVITY_RIGHT:
                headerView = LayoutInflater.from(getContext()).inflate(R.layout.jahedmanesh_profile_header_right, this, true);
                break;

            case NavigationView.CONTENT_GRAVITY_AUTO:
                headerView = LayoutInflater.from(getContext()).inflate(R.layout.jahedmanesh_profile_header_auto, this, true);
                break;

        }

        if (headerView != null) {

            imgAvatarMain = headerView.findViewById(R.id.imgAvatarMain);
            imgAvatarMain.setOnClickListener(view -> {
                if (profileHeaderCallback != null) {
                    profileHeaderCallback.onProfileClicked(mainProfile);
                }
            });
            imgAvatarMain.setOnLongClickListener(view -> {
                if (profileHeaderCallback != null) {
                    profileHeaderCallback.onProfileLongClicked(mainProfile);
                }
                return true;
            });

            imgAvatarSecond = headerView.findViewById(R.id.imgAvatarSecond);
            imgAvatarSecond.setOnClickListener(view -> {
                if (profileHeaderCallback != null) {
                    profileHeaderCallback.onProfileClicked(secondProfile);
                }
            });
            imgAvatarSecond.setOnLongClickListener(view -> {
                if (profileHeaderCallback != null) {
                    profileHeaderCallback.onProfileLongClicked(secondProfile);
                }
                return true;
            });

            imgAvatarThird = headerView.findViewById(R.id.imgAvatarThird);
            imgAvatarThird.setOnClickListener(view -> {
                if (profileHeaderCallback != null) {
                    profileHeaderCallback.onProfileClicked(thirdProfile);
                }
            });
            imgAvatarThird.setOnLongClickListener(view -> {
                if (profileHeaderCallback != null) {
                    profileHeaderCallback.onProfileLongClicked(thirdProfile);
                }
                return true;
            });

            imgCover = headerView.findViewById(R.id.imgCover);
            tvTitle = headerView.findViewById(R.id.tvTitle);
            tvSubTitle = headerView.findViewById(R.id.tvSubTitle);

            imgArrow = headerView.findViewById(R.id.imgArrow);
            imgArrow.setOnClickListener(v->{
                if (profileHeaderCallback != null) {
                    profileHeaderCallback.onArrowClicked(v);
                }
            });

        }

    }

    public void setProfileImageLoaderCallBack(NavigationView.ProfileImageLoaderCallBack profileImageLoaderCallBack) {
        this.profileImageLoaderCallBack = profileImageLoaderCallBack;
    }

    public void setProfileHeaderCallback(NavigationView.ProfileHeaderCallback profileHeaderCallback) {
        this.profileHeaderCallback = profileHeaderCallback;
    }

    @Override
    public void defaultProfileChanged(ProfileManager profileManager, Profile profile) {
        loadProfiles(profile);
    }

    @Override
    public void defaultProfileRemoved(ProfileManager profileManager, Profile profile) {
        // TODO: 3/18/18
    }

    @Override
    public void profileRemoved(ProfileManager profileManager, Profile profile) {
        // TODO: 3/18/18
    }

    @Override
    public void refresh(ProfileManager profileManager) {
        loadProfiles(profileManager.getDefault());
    }

    public void loadProfiles(@Nullable Profile defaultProfile) {

        if (defaultProfile == null) {
            mainProfile = ProfileManager.getInstance().getDefault();
        } else {
            mainProfile = defaultProfile;
        }

        List<Profile> profileItems = ProfileManager.getInstance().getProfileItemsExceptDefault();
        for (int i = 0; i < profileItems.size(); i++) {
            Profile profile = profileItems.get(i);
            if (i == 0) {
                secondProfile = profile;
            } else if (i == 1) {
                thirdProfile = profile;
            } else {
                break;
            }
        }

        if (mainProfile != null) {
            if (profileImageLoaderCallBack != null) {
                profileImageLoaderCallBack.onProfileAvatarLoading(imgAvatarMain, mainProfile);
                profileImageLoaderCallBack.onProfileCoverLoading(imgCover, mainProfile);
            }

            tvTitle.setText(mainProfile.getTitle());
            tvSubTitle.setText(mainProfile.getSubTitle());
        }

        if (secondProfile != null) {
            if (profileImageLoaderCallBack != null) {
                profileImageLoaderCallBack.onProfileAvatarLoading(imgAvatarSecond, secondProfile);
            }
        }

        if (thirdProfile != null) {
            if (profileImageLoaderCallBack != null) {
                profileImageLoaderCallBack.onProfileAvatarLoading(imgAvatarThird, thirdProfile);
            }
        }

    }

}
