package com.yang.kotlinmvvmsample.ui.main

import android.os.Bundle
import android.text.TextUtils
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.yang.baselibs.base.BaseVMActivity
import com.yang.baselibs.ext.setOnSingleClickListener
import com.yang.baselibs.ext.visible
import com.yang.baselibs.ext.visibleOrGone
import com.yang.baselibs.utils.SharedPreferencesUtils
import com.yang.kotlinmvvmsample.databinding.ActivityMainBinding
import com.yang.kotlinmvvmsample.utils.PermissionHelper
import com.yang.kotlinmvvmsample.widgets.LoadingDialog

class MainActivity : BaseVMActivity<MainViewModel>() {

    private lateinit var mBinding: ActivityMainBinding
    private val mDialog by lazy {
        LoadingDialog(this)
    }

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
        setStatusBar(true, ContextCompat.getColor(this, android.R.color.transparent), true)

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
        mBinding.btnAnim.setOnSingleClickListener {
            mBinding.tvAnim.visibleOrGone(mBinding.tvAnim.isVisible.not(), true)
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
                mBinding.imageView.visible(animate = false)
                Glide.with(this@MainActivity).load(bannerList[0].imagePath).into(mBinding.imageView)
            }
        }
        mViewModel.mCollectResponseBody.observe(this) { body ->
            if (body.datas.isNotEmpty()) {
                mBinding.tvResult.text = body.datas[0].title
            }
        }
    }

    override fun showLoading() {
        mDialog.showLoading()
    }

    override fun hideLoading() {
        mDialog.hideLoading()
    }

    override fun onDestroy() {
        hideLoading()
        super.onDestroy()
    }
}