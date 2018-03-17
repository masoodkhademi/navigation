package jahedmanesh.lib.navigation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Mehdi Jahed Manesh on 3/1/18 at 7:35 PM.
 * Email : 47even@gmail.com
 */
public class ProfileManager {

    private static ProfileManager instance;

    private LinkedHashMap<Long, Profile> profileItems = new LinkedHashMap<>();
    private List<ProfileEventChanged> listeners;

    private ProfileManager() {
        listeners = new ArrayList<>();
    }

    public static ProfileManager getInstance() {
        synchronized (ProfileManager.class) {
            if (instance == null) {
                instance = new ProfileManager();
            }
            return instance;
        }
    }

    /**
     * Get default profile if exists .
     *
     * @return null if there is no any default account
     */
    @Nullable
    public Profile getDefault() {
        for (Long itemId : profileItems.keySet()) {
            if (profileItems.get(itemId).isDefault()) {
                return profileItems.get(itemId);
            }
        }
        return null;
    }

    /**
     * Set default profile , if the profile is not already default .
     *
     * @param profileItem future default account
     */
    public void setDefault(@NonNull Profile profileItem) {
        if (!profileItem.isDefault()) {
            resetValues();
            profileItem.setDefault(true);
            notifyDefaultProfileChanged(profileItem);
        }
    }

    /**
     * Add new profile .
     *
     * @param profileItem new profile
     */
    public void addProfileItem(@NonNull Profile profileItem) {
        profileItems.put(profileItem.getId(), profileItem);
    }

    /**
     * Remove profile .
     * <p>
     * <b>Note : </b> If the removed profile is default profile
     * then default profile will be changed to first profile in list .
     * </p>
     *
     * @param profileId id of existed profile
     */
    public void removeProfileItem(long profileId) {
        Profile item = profileItems.get(profileId);
        if (item.isDefault()) {

            // set update default status , just only for any consumer has its reference
            item.setDefault(false);

            Profile removedDefaultProfile = profileItems.remove(profileId);
            notifyDefaultProfileRemoved(removedDefaultProfile);


            // set first profile to default
            // noinspection LoopStatementThatDoesntLoop
            for (Long itemId : profileItems.keySet()) {
                Profile profile = profileItems.get(itemId);
                profile.setDefault(true);
                notifyDefaultProfileChanged(profile);
                break;
            }

        } else {
            Profile removedProfile = profileItems.remove(profileId);
            notifyProfileRemoved(removedProfile);
        }
    }

    /**
     * @return a copy of profiles
     */
    public List<Profile> getProfileItems() {
        List<Profile> profileAsList = new ArrayList<>();
        profileAsList.addAll(profileItems.values());
        return profileAsList;
    }

    /**
     * Set new list of profile .
     * <p>
     * <b>Note : </b> If there are already profiles in list , they will be removed permanently .
     * </p>
     *
     * @param profileItems new set of profiles list
     */
    public void setProfileItems(List<Profile> profileItems) {
        this.profileItems.clear();
        for (Profile profileItem : profileItems) {
            this.profileItems.put(profileItem.getId(), profileItem);
        }
    }

    public List<Profile> getProfileItemsExceptDefault() {
        List<Profile> profileAsList = new ArrayList<>();
        for (Long itemId : profileItems.keySet()) {
            Profile profile = profileItems.get(itemId);
            if (!profile.isDefault()) {
                profileAsList.add(profile);
            }
        }
        return profileAsList;
    }

    void addProfileEventChanged(ProfileEventChanged profileEventChanged) {
        listeners.add(profileEventChanged);
    }

    void removeProfileEventChanged(ProfileEventChanged profileEventChanged) {
        listeners.remove(profileEventChanged);
    }

    void removeAllProfileEventChanged() {
        listeners.clear();
    }

    /**
     * Reset all flags or values.
     */
    private void resetValues() {
        for (Long itemId : profileItems.keySet()) {
            profileItems.get(itemId).setDefault(false);
        }
    }

    private void notifyDefaultProfileChanged(Profile profile) {
        for (ProfileEventChanged listener : listeners) {
            listener.defaultProfileChanged(instance, profile);
        }
    }

    private void notifyDefaultProfileRemoved(Profile profile) {
        for (ProfileEventChanged listener : listeners) {
            listener.defaultProfileRemoved(instance, profile);
        }
    }

    private void notifyProfileRemoved(Profile profile) {
        for (ProfileEventChanged listener : listeners) {
            listener.profileRemoved(instance, profile);
        }
    }

    public void refresh() {
        for (ProfileEventChanged listener : listeners) {
            listener.refresh(instance);
        }
    }

    interface ProfileEventChanged {
        void defaultProfileChanged(ProfileManager profileManager, Profile profile);
        void defaultProfileRemoved(ProfileManager profileManager, Profile profile);
        void profileRemoved(ProfileManager profileManager, Profile profile);
        void refresh(ProfileManager profileManager);
    }

}
