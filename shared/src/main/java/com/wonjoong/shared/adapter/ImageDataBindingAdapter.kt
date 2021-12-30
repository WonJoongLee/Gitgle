package com.wonjoong.shared.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageDataBindingAdapter {

    @BindingAdapter("src")
    @JvmStatic
    fun ImageView.setImageViewResource(url: String?) {
        if (url == null) return
        Glide.with(this)
            .load(url)
            .into(this)
    }
}