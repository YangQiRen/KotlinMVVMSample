package com.yang.baselibs.utils

import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

/**
 * 是否落在 EditText 區域
 */
fun isHideKeyboard(view: View?, event: MotionEvent): Boolean {
    if (view != null && view is EditText) {
        val location = intArrayOf(0, 0)
        view.getLocationInWindow(location)
        //获取现在拥有焦点的控件view的位置，即EditText
        val left = location[0]
        val top = location[1]
        val bottom = top + view.height
        val right = left + view.width
        //判断我们手指点击的区域是否落在EditText上面，如果不是，则返回true，否则返回false
        val isInEt = (event.x > left && event.x < right && event.y > top && event.y < bottom)
        return !isInEt
    }
    return false
}

fun Context.hideKeyboard(view: View?) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view!!.windowToken, 0)
}

/**
 * 打開軟鍵盤
 */
fun openKeyBoard(mEditText: EditText, mContext: Context) {
    val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

/**
 * 關閉軟鍵盤
 */
fun closeKeyBoard(mEditText: EditText, mContext: Context) {
    val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
}