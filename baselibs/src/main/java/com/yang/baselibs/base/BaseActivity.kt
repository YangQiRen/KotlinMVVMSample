package com.yang.baselibs.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.MotionEvent
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.yang.baselibs.common.AppManager
import com.yang.baselibs.ext.showToast
import com.yang.baselibs.utils.StatusBarUtil
import com.yang.baselibs.utils.hideKeyboard
import com.yang.baselibs.utils.isHideKeyboard

abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity(), IView {

    lateinit var mBinding: VB

    /**
     * 佈局綁定
     */
    abstract fun getViewBinding(): VB

    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 设置状态栏的背景颜色
     */
    fun setStatusBarColor(@ColorInt color: Int) {
        StatusBarUtil.setColor(this, color, 0)
    }

    /**
     * 设置状态栏图标的颜色
     *
     * @param dark true: 黑色  false: 白色
     */
    fun setStatusBarIcon(dark: Boolean) {
        if (dark) {
            StatusBarUtil.setLightMode(this)
        } else {
            StatusBarUtil.setDarkMode(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        mBinding = getViewBinding()
        setContentView(mBinding.root)
        AppManager.instance.addActivity(this)
        initView()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

    override fun showLoading() {
    }

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

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_UP) {
            val v = currentFocus
            if (isHideKeyboard(v, ev)) {
                hideKeyboard(v)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}