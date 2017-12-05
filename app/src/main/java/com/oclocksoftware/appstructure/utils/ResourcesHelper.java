package com.oclocksoftware.appstructure.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.oclocksoftware.appstructure.R;

/**
 * Created by babin on 12/5/2017.
 */

public class ResourcesHelper {

    public static int getColor(Context context, int colorResource){
        int color;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            color = context.getResources().getColor(colorResource, null);
        } else {
            color = context.getResources().getColor(colorResource);
        }
        return color;
    }

    public static Drawable getDrawable(Context context, int drawableResource){
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            drawable = context.getResources().getDrawable(drawableResource, null);
        } else {
            drawable = context.getResources().getDrawable(drawableResource);
        }
        return drawable;
    }
}
