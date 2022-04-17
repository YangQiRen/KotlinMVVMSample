package com.yang.kotlinmvvmsample.ui.main

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.yang.baselibs.base.BaseActivity
import com.yang.baselibs.ext.setOnSingleClickListener
import com.yang.baselibs.ext.showToast
import com.yang.baselibs.ext.visible
import com.yang.baselibs.ext.visibleOrGone
import com.yang.baselibs.utils.SharedPreferencesUtils
import com.yang.kotlinmvvmsample.R
import com.yang.kotlinmvvmsample.databinding.ActivityMainBinding
import com.yang.kotlinmvvmsample.util.hasPermissions
import com.yang.kotlinmvvmsample.widget.LoadingDialog

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
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

    private val mDialog by lazy { LoadingDialog(this) }
    private val preferences by lazy { SharedPreferencesUtils() }

    private val mainViewModel: MainViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getVM(): MainViewModel = mainViewModel

    override fun bindVM(binding: ActivityMainBinding, viewModel: MainViewModel) = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initListener()
        subscribeAndObserve()
        setupPermission()
    }

    /**
     * 設置畫面
     */
    private fun initView() {
        setStatusBar(true, ContextCompat.getColor(this, android.R.color.transparent), true)
    }

    /**
     * 初始化監聽器
     */
    private fun initListener() {
        binding.btnLogin.setOnSingleClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            if (TextUtils.isEmpty(username)) {
                showToast("账号不能为空")
                return@setOnSingleClickListener
            }
            if (TextUtils.isEmpty(password)) {
                showToast("密码不能为空")
                return@setOnSingleClickListener
            }
            viewModel.login(username, password)
        }
        binding.btnLogout.setOnSingleClickListener {
            viewModel.logout()
        }
        binding.btnGetBanner.setOnSingleClickListener {
            viewModel.getBannerList()
        }
        binding.btnCollect.setOnSingleClickListener {
            viewModel.getCollectList(0)
        }
        binding.btnPermission.setOnSingleClickListener {
            setupPermission()
        }
        binding.btnAnim.setOnSingleClickListener {
            binding.tvAnim.visibleOrGone(binding.tvAnim.isVisible.not(), true)
        }
    }

    /**
     * 訂閱及觀察者
     */
    private fun subscribeAndObserve() {
        viewModel.mLoginData.observe(this) {
            showToast("登录成功")
        }
        viewModel.mLogoutData.observe(this) {
            showToast("已退出登录")
        }
        viewModel.mBannerList.observe(this) { bannerList ->
            if (bannerList.isNotEmpty()) {
                binding.tvResult.text = bannerList[0].title
                binding.imageView.visible(animate = false)
                Glide.with(this).load(bannerList[0].imagePath).into(binding.imageView)
            }
        }
        viewModel.mCollectResponseBody.observe(this) { body ->
            if (body.datas.isNotEmpty()) {
                binding.tvResult.text = body.datas[0].title
            }
        }
    }

    /**
     * 設置權限
     */
    private fun setupPermission() {
        if (!hasPermissions(this, permissionList)) {
            permReqLauncher.launch(permissionList)
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
        AlertDialog.Builder(this).apply {
            setTitle("Need permission(s)")
            setMessage("Some permissions are required to do the task.")
            setPositiveButton("OK") { dialog, which ->
                launchToAppDetail()
                dialog.dismiss()
            }
            setNeutralButton("Cancel", null)
        }.create().show()
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