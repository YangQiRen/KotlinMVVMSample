package com.yang.baselibs.base

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.yang.baselibs.ext.showToast
import com.yang.baselibs.utils.ActivityUtil
import com.yang.baselibs.utils.hideKeyboard
import com.yang.baselibs.utils.isHideKeyboard

abstract class BaseActivity : AppCompatActivity(), IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityUtil.getInstance().addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityUtil.getInstance().removeActivity(this)
    }

    /**
     * 設定狀態欄形式<BR>
     * @param padding 狀態欄是否會壓在畫面上，false:狀態欄跟layout重疊；true:狀態欄跟layout不重疊
     * @param color 狀態欄底色顏色，可為透明 ContextCompat.getColor(R.color.xxx)
     * @param backgroundIsLight 狀態欄類型(亮或暗)，true:狀態欄亮字黑色；false:狀態欄暗字白色
     */
    open fun setStatusBar(padding: Boolean, color: Int, backgroundIsLight: Boolean) {
        (findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(
            0
        ).fitsSystemWindows = padding

        val flag: Int = if (padding) {
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        } else {
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE//狀態列在layout上方
        }

        val w = window
        window.clearFlags(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        w.decorView.systemUiVisibility = flag

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && backgroundIsLight) {
            val v = w.decorView
            v.systemUiVisibility = v.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        w.statusBarColor = color
    }

    /**
     * 可以將common loading dialog寫在此處，也可以覆寫自定義
     */
    override fun showLoading() {
    }

    /**
     * 可以將common loading dialog寫在此處，也可以覆寫自定義
     */
    override fun hideLoading() {
    }

    override fun showDefaultMsg(msg: String) {
        showToast(msg)
    }

    override fun showMsg(msg: String) {
        showToast(msg)
    }

    override fun showError(errorMsg: String) {
        showToast(errorMsg)
    }

    /**
     * 點擊輸入框外隱藏鍵盤
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_UP) {
            val v = currentFocus
            if (isHideKeyboard(v, ev)) {
                hideKeyboard(v)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 點擊home鍵->返回
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}