package com.yang.kotlinmvvmsample.ui.main

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.yang.baselibs.base.BaseActivity
import com.yang.baselibs.utils.Preference
import com.yang.baselibs.utils.hideKeyboard
import com.yang.baselibs.widget.CustomDialog
import com.yang.kotlinmvvmsample.R
import com.yang.kotlinmvvmsample.databinding.ActivityMainBinding
import com.yang.kotlinmvvmsample.utils.PermissionHelper

class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setStatusBarColor(ContextCompat.getColor(this, R.color.design_default_color_primary_dark))
        setStatusBarIcon(false)

        mBinding.btn.setOnClickListener {
            hideKeyboard()

//            CustomDialog.newBuilder()
//                .setTitle("Hello 你今天過得好嗎")
//                .setContent("聽說這裡是Content")
//                .setCancelAble(false)
//                .setLeftText("Cancel")
//                .setRightText("Confirm")
//                .build()
//                ?.show(supportFragmentManager, "CustomDialog")
        }
    }
}