package com.yang.baselibs.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.yang.baselibs.R
import com.yang.baselibs.databinding.DialogBaseLoadingBinding

class BaseLoadingDialog: Dialog {
    private lateinit var binding: DialogBaseLoadingBinding

    constructor(context: Context) : super(context, R.style.FullScreenDialog)

    constructor(context: Context, themeResId: Int) : super(context, themeResId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogBaseLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDialogStyle()
    }

    private fun setDialogStyle() {
        setCancelable(true)
        setCanceledOnTouchOutside(false)
    }

    fun showLoading() {
        super.show()
    }

    fun hideLoading() {
        if (isShowing) {
            super.dismiss()
        }
    }

}