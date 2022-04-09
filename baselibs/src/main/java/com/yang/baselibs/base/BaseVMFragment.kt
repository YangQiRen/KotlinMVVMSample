package com.yang.baselibs.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider

abstract class BaseVMFragment<VM : BaseViewModel> : BaseFragment() {

    lateinit var mViewModel: VM

    abstract fun attachVMClass(): Class<VM>?

    open fun startObserver() {
        subscribeLoadingDialog(mViewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVM()
        startObserver()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initVM() {
        if (attachVMClass() == null) {
            throw RuntimeException("ViewModel must not be null.")
        }
        attachVMClass()?.let {
            mViewModel = ViewModelProvider(this)[it]
            lifecycle.addObserver(mViewModel)
        }
    }

    /**
     * 观察 Loading 的监听
     */
    open fun subscribeLoadingDialog(vararg baseAndroidViewModel: BaseViewModel) {
        baseAndroidViewModel.forEach {
            it.showLoading.observe(this) { isShow ->
                if (isShow) {
                    showLoading()
                } else {
                    hideLoading()
                }
            }
        }
    }

    override fun onDestroy() {
        lifecycle.removeObserver(mViewModel)
        super.onDestroy()
    }
}