package com.yang.kotlinmvvmsample.widgets

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.airbnb.lottie.LottieDrawable
import com.yang.kotlinmvvmsample.R
import com.yang.kotlinmvvmsample.databinding.DialogLoadingBinding

class LoadingDialog : Dialog {
    private lateinit var mBinding: DialogLoadingBinding

    private var jsonFileName: String = "loading.json"
    private var isLoop: Boolean = true

    constructor(context: Context) : super(context, R.style.LightProgressDialog)

    constructor(context: Context, themeResId: Int) : super(context, themeResId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DialogLoadingBinding.inflate(layoutInflater)
        mBinding.lavProgress.setAnimation(jsonFileName)
        mBinding.lavProgress.repeatCount = if (isLoop) LottieDrawable.INFINITE else 0
        mBinding.lavProgress.playAnimation()
        setContentView(mBinding.root)
        setDialogStyle()
    }

    private fun setDialogStyle() {
        setCancelable(true)
        setCanceledOnTouchOutside(false)
    }

    fun showLoading() {
        super.show()
        mBinding.lavProgress.playAnimation()
    }

    fun hideLoading() {
        if (isShowing) {
            mBinding.lavProgress.cancelAnimation()
            super.dismiss()
        }
    }
}