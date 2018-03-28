package com.newport.app.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.EditText;

import com.newport.app.NewPortApplication;
import com.newport.app.R;

/**
 * Created by tohure on 02/11/17.
 */

public class Helper {

    private Helper() {
        throw new IllegalStateException("Helper Class");
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static boolean validateDniEditText(EditText editText) {
        if (editText.getText().toString().isEmpty()) {
            editText.setError(NewPortApplication.getAppContext().getString(R.string.required_field));
            return false;
        } else if (editText.getText().length() > Constant.DNI_LENGTH) {
            editText.setError(NewPortApplication.getAppContext().getString(R.string.incorrect_field));
            return false;
        }

        return true;
    }
}
