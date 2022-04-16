package com.yang.kotlinmvvmsample.ui.main

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.yang.baselibs.base.BaseVMActivity
import com.yang.baselibs.ext.setOnSingleClickListener
import com.yang.baselibs.ext.showToast
import com.yang.baselibs.ext.visible
import com.yang.baselibs.ext.visibleOrGone
import com.yang.baselibs.utils.SharedPreferencesUtils
import com.yang.kotlinmvvmsample.databinding.ActivityMainBinding
import com.yang.kotlinmvvmsample.util.PermissionHelper
import com.yang.kotlinmvvmsample.util.hasPermissions
import com.yang.kotlinmvvmsample.widget.LoadingDialog

class MainActivity : BaseVMActivity<MainViewModel>() {
    // permissions
    private val permissionList = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private val appDetailLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (!hasPermissions(this, permissionList)) {
                permReqLauncher.launch(permissionList)
            }
        }

    private val permReqLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all {
                it.value == true
            }
            if (granted) {
                showToast("所有權限已同意")
            } else {
                showAskPermissionAlertDialog()
            }
        }


    private lateinit var mBinding: ActivityMainBinding
    private val mDialog by lazy {
        LoadingDialog(this)
    }

    private val preferences by lazy { SharedPreferencesUtils() }

    override fun attachVMClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupListener()
        setupPermission()
    }

    /**
     * 設置畫面
     */
    private fun setupView() {
        //        mViewModel = MainViewModel()
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.lifecycleOwner = this
        setStatusBar(true, ContextCompat.getColor(this, android.R.color.transparent), true)
//        mBinding.viewmodel = mViewModel
    }

    /**
     * 設置監聽器
     */
    private fun setupListener() {
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

    /**
     * 設置權限
     */
    private fun setupPermission() {
//        permissionUtil = PermissionUtil(this, permissionList, PermissionsRequestCode)
//        permissionUtil.checkPermissions()
        if (!hasPermissions(this, permissionList)) {
            permReqLauncher.launch(permissionList)
        }
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

    /**
     * 要求前去app setting打開權限
     */
    private fun showAskPermissionAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Need permission(s)")
        builder.setMessage("Some permissions are required to do the task.")
        builder.setPositiveButton("OK") { dialog, which ->
            launchToAppDetail()
            dialog.dismiss()
        }
        builder.setNeutralButton("Cancel", null)
        val dialog = builder.create()
        dialog.show()
    }

    /**
     * 前往此應用系統設定頁面
     */
    private fun launchToAppDetail() {
        appDetailLauncher.launch(
            Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.parse("package:$packageName")
            }
        )
    }
}