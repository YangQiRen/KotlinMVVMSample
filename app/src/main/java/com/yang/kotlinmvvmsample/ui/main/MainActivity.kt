package com.yang.kotlinmvvmsample.ui.main

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.yang.baselibs.base.BaseVMActivity
import com.yang.baselibs.utils.SharedPreferencesUtils
import com.yang.baselibs.utils.hideKeyboard
import com.yang.kotlinmvvmsample.R
import com.yang.kotlinmvvmsample.databinding.ActivityMainBinding

class MainActivity : BaseVMActivity<MainViewModel>() {

    private lateinit var mBinding: ActivityMainBinding

    private val preferences by lazy { SharedPreferencesUtils() }

    override fun attachVMClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        initView()
        initData()
    }

    private fun initViewBinding() {
        //        mViewModel = MainViewModel()
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.lifecycleOwner = this
//        mBinding.viewmodel = mViewModel
    }

    private fun initView() {
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

    private fun initData() {
    }
}