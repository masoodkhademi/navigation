package jahedmanesh.lib.navigation.item;

import android.support.annotation.NonNull;

/**
 * Created by Mehdi Jahed Manesh on 2/28/18 at 8:26 PM.
 * Email : 47even@gmail.com
 */

public class NavMenuItem extends NavItem {

    @NonNull
    private String title;
    private int icon;

    public NavMenuItem(long id, @NonNull String title) {
        super(id);
        this.title = title;
    }

    public NavMenuItem(long id, @NonNull String title, int icon) {
        super(id);
        this.title = title;
        this.icon = icon;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public NavMenuItem setTitle(@NonNull String title) {
        this.title = title;
        return this;
    }

    public int getIcon() {
        return icon;
    }

    public NavMenuItem setIcon(int icon) {
        this.icon = icon;
        return this;
    }

}
