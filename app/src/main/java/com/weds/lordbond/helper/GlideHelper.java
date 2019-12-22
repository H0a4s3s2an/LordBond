package com.weds.lordbond.helper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.weds.lordbond.R;

public class GlideHelper {
    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).
                placeholder(R.drawable.default_woman_image).
                error(R.drawable.default_woman_image).
                into(imageView);
    }

    public static void loadImagePlanPlaceholder(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).
                placeholder(R.drawable.ic_launcher_background).
                error(R.drawable.ic_launcher_background).
                into(imageView);
    }

    public static void loadImageDarkPlanPlaceholder(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).
                placeholder(R.drawable.ic_launcher_background).
                error(R.drawable.ic_launcher_background).
                into(imageView);
    }
}
