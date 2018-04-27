package com.brightcove.tvmazeclient.view;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.brightcove.tvmazeclient.R;
import com.brightcove.tvmazeclient.utils.StringUtil;
import com.squareup.picasso.Picasso;

/**
 * Created by Ali on 26-Apr-18.
 */

public class ImageBindingAdapter {
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url){
        if(!StringUtil.isNullOrEmpty(url))
            Picasso.get()
                    .load(url)
            .placeholder(R.drawable.ic_photo_black_24dp)
                    .into(imageView);
    }
}
