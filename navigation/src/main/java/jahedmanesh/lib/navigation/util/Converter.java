package jahedmanesh.lib.navigation.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by Mehdi Jahed Manesh on 3/1/18 at 1:46 PM.
 * Email : 47even@gmail.com
 */

public class Converter {

    public static int convertPixelsToDp(float px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return Math.round(dp);
    }

    public static int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

}
