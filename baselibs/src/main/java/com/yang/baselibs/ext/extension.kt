package com.yang.baselibs.ext

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Insets
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

/**
 * get 狀態列高度
 */
fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = this.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = this.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

/**
 * 此方法會因 WindowInsets.Type 不一樣會返回不一樣的結果
 * 詳細請看 https://developer.android.com/reference/android/view/WindowInsets.Type
 * 參考資料 https://blog.stylingandroid.com/android11-windowinsets-part1/
 */
fun Activity.getScreenWidth(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = windowManager.currentWindowMetrics
        val insets: Insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        windowMetrics.bounds.width() - insets.left - insets.right
    } else {
        val displayMetrics = DisplayMetrics()
        @Suppress("DEPRECATION")
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        displayMetrics.widthPixels
    }
}

/**
 * 此方法會因 WindowInsets.Type 不一樣會返回不一樣的結果
 * 詳細請看 https://developer.android.com/reference/android/view/WindowInsets.Type
 * 參考資料 https://blog.stylingandroid.com/android11-windowinsets-part1/
 */
fun Activity.getScreenHeight(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = windowManager.currentWindowMetrics
        val insets: Insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        windowMetrics.bounds.height() - insets.top - insets.bottom
    } else {
        val displayMetrics = DisplayMetrics()
        @Suppress("DEPRECATION")
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }
}

/**
 * get color
 */
fun Context.getCompatColor(@ColorRes colorInt: Int) : Int = ContextCompat.getColor(this, colorInt)

/**
 * get drawable
 */
fun Context.getCompatDrawable(@DrawableRes drawableRes: Int) : Drawable? = ContextCompat.getDrawable(this, drawableRes)
