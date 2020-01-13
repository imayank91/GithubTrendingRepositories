package com.app.gitrepositories.helpers

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mikhaellopez.circularimageview.CircularImageView

/**
 * Created by mayank on October 11 2019
 */

@BindingAdapter("avatar")
fun loadImage(view: CircularImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl).apply(RequestOptions().circleCrop())
        .into(view)
}