package jahedmanesh.lib.navigation;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Mehdi Jahed Manesh on 2/28/18 at 10:41 AM.
 * Email : 47even@gmail.com
 */

@SuppressWarnings(value = {"unused", "WeakerAccess", "SameParameterValue"})
public class Profile {

    private long id;
    @NonNull
    private String title;
    @Nullable
    private String subTitle;
    @Nullable
    private String avatarUrl;
    @Nullable
    private String coverUrl;
    @Nullable
    private Drawable avatarDrawable;
    @Nullable
    private Drawable coverDrawable;
    @DrawableRes
    private int avatarIcon;
    @DrawableRes
    private int coverIcon;

    private boolean isDefault;

    public Profile(long id, @NonNull String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;

        Profile profile = (Profile) o;

        return getId() == profile.getId();
    }

    public long getId() {
        return id;
    }

    public Profile setId(long id) {
        this.id = id;
        return this;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public Profile setTitle(@NonNull String title) {
        this.title = title;
        return this;
    }

    @Nullable
    public String getSubTitle() {
        return subTitle;
    }

    public Profile setSubTitle(@Nullable String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    @Nullable
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Profile setAvatarUrl(@Nullable String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    @Nullable
    public String getCoverUrl() {
        return coverUrl;
    }

    public Profile setCoverUrl(@Nullable String coverUrl) {
        this.coverUrl = coverUrl;
        return this;
    }

    @Nullable
    public Drawable getAvatarDrawable() {
        return avatarDrawable;
    }

    public Profile setAvatarDrawable(@Nullable Drawable avatarDrawable) {
        this.avatarDrawable = avatarDrawable;
        return this;
    }

    @Nullable
    public Drawable getCoverDrawable() {
        return coverDrawable;
    }

    public Profile setCoverDrawable(@Nullable Drawable coverDrawable) {
        this.coverDrawable = coverDrawable;
        return this;
    }

    public int getAvatarIcon() {
        return avatarIcon;
    }

    public Profile setAvatarIcon(int avatarIcon) {
        this.avatarIcon = avatarIcon;
        return this;
    }

    public int getCoverIcon() {
        return coverIcon;
    }

    public Profile setCoverIcon(int coverIcon) {
        this.coverIcon = coverIcon;
        return this;
    }

    public boolean isDefault() {
        return isDefault;
    }

    void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

}
