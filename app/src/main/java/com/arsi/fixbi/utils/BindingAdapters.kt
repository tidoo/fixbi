package com.arsi.fixbi.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("image_url")
fun loadURL(imageView: ImageView, imageURL: String?) {
    if (imageURL != null)
        imageView.loadImage(imageURL)
}