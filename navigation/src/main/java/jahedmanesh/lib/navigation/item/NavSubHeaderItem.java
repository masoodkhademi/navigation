package jahedmanesh.lib.navigation.item;

import android.support.annotation.NonNull;

/**
 * Created by Mehdi Jahed Manesh on 2/28/18 at 8:26 PM.
 * Email : 47even@gmail.com
 */

public class NavSubHeaderItem extends NavItem {

    @NonNull
    private String title;

    public NavSubHeaderItem(long id, @NonNull String title) {
        super(id);
        this.title = title;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public NavSubHeaderItem setTitle(@NonNull String title) {
        this.title = title;
        return this;
    }

}
