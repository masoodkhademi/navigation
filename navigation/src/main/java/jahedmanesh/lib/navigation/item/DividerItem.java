package jahedmanesh.lib.navigation.item;

import android.graphics.Color;

/**
 * Created by Mehdi Jahed Manesh on 2/28/18 at 8:26 PM.
 * Email : 47even@gmail.com
 */

public class DividerItem extends Item {

    private int dividerColor = Color.BLACK;

    public DividerItem(long id) {
        super(id);
    }

    public int getDividerColor() {
        return dividerColor;
    }

    public DividerItem setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        return this;
    }

}
