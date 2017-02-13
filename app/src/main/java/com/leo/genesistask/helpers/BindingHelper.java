package com.leo.genesistask.helpers;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * DataBinding helper class.
 * Place all BidingAdapters, BindingConverters etc. inhere.
 *
 * Created by leonid on 2/11/17.
 */

public class BindingHelper {

    @BindingAdapter({"bind:font"})
    public static void setFont(TextView textView, String fontName) {
        textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/" + fontName));
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Glide
                .with(imageView.getContext())
                .load(url)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }
}
