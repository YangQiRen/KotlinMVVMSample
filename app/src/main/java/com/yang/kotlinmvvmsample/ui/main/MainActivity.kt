package com.yang.kotlinmvvmsample.ui.main

import android.os.Bundle
import android.text.TextUtils
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.yang.baselibs.base.BaseVMActivity
import com.yang.baselibs.ext.setOnSingleClickListener
import com.yang.baselibs.utils.SharedPreferencesUtils
import com.yang.kotlinmvvmsample.R
import com.yang.kotlinmvvmsample.databinding.ActivityMainBinding
import com.yang.kotlinmvvmsample.utils.PermissionHelper

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

        mBinding.btnLogin.setOnSingleClickListener {
            val username = mBinding.etUsername.text.toString()
            val password = mBinding.etPassword.text.toString()
            if (TextUtils.isEmpty(username)) {
                showDefaultMsg("账号不能为空")
                return@setOnSingleClickListener
            }
            if (TextUtils.isEmpty(password)) {
                showDefaultMsg("密码不能为空")
                return@setOnSingleClickListener
            }
            mViewModel.login(username, password)
        }
        mBinding.btnLogout.setOnSingleClickListener {
            mViewModel.logout()
        }
        mBinding.btnGetBanner.setOnSingleClickListener {
            mViewModel.getBannerList()
        }
        mBinding.btnCollect.setOnSingleClickListener {
            mViewModel.getCollectList(0)
        }
        mBinding.btnPermission.setOnSingleClickListener {
            PermissionHelper.requestCameraPermission(this) {
                showDefaultMsg("相机权限申请成功")
            }
        }
    }

    private fun initData() {
    }

    override fun startObserver() {
        super.startObserver()
        mViewModel.mLoginData.observe(this) {
            showDefaultMsg("登录成功")
        }
        mViewModel.mLogoutData.observe(this) {
            showDefaultMsg("已退出登录")
        }
        mViewModel.mBannerList.observe(this) { bannerList ->
            if (bannerList.isNotEmpty()) {
                mBinding.tvResult.text = bannerList[0].title
                Glide.with(this@MainActivity).load(bannerList[0].imagePath).into(mBinding.imageView)
            }
        }
        mViewModel.mCollectResponseBody.observe(this) { body ->
            if (body.datas.isNotEmpty()) {
                mBinding.tvResult.text = body.datas[0].title
            }
        }
    }
}