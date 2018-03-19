package jahedmanesh.lib.navigation.item;

import android.graphics.Color;
import android.support.annotation.NonNull;

/**
 * Created by Mehdi Jahed Manesh on 2/28/18 at 8:26 PM.
 * Email : 47even@gmail.com
 */

public class NavMenuItem extends NavItem {

    @NonNull
    private String title;
    private int icon;
    private int titleColor = Color.BLACK;

    public NavMenuItem(long id, @NonNull String title) {
        super(id);
        this.title = title;
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

    public int getTitleColor() {
        return titleColor;
    }

    public NavMenuItem setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

}
