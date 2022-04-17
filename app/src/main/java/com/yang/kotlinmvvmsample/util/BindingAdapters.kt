package com.yang.kotlinmvvmsample.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.yang.kotlinmvvmsample.R
import com.yang.kotlinmvvmsample.data.model.Status
import com.yang.kotlinmvvmsample.ext.setDrawableLeft

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this).load(url).into(this)
}

@BindingAdapter("status")
fun MaterialTextView.status(status: Status) {
    text = status.toString()
    when (status) {
        Status.ALIVE -> setDrawableLeft(R.color.green_a700)
        Status.DEAD -> setDrawableLeft(R.color.red_a700)
        Status.UNKNOWN -> setDrawableLeft(R.color.gray_700)
    }
}