package com.yang.kotlinmvvmsample.ext

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.textview.MaterialTextView

fun MaterialTextView.setDrawableLeft(@ColorRes res: Int) {
    if (compoundDrawables[0] == null) return
    compoundDrawables[0].setTint(ContextCompat.getColor(context, res))
}