package com.yang.kotlinmvvmsample.widgets

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.yang.baselibs.widget.OnSingleClickListener
import com.yang.kotlinmvvmsample.databinding.DialogPermissionBinding

class PermissionDialog : DialogFragment() {

    private lateinit var mBinding: DialogPermissionBinding

    companion object {
        val TITLE = "title"
        val CONTENT = "content"
        val RIGHT_TEXT = "right_text"
        val CANCEL_ABLE = "cancel_able"

        fun newInstance(builder: Builder): PermissionDialog {
            val bundle = Bundle()
            val fragment = PermissionDialog()
            bundle.putString(TITLE, builder.title)
            bundle.putString(CONTENT, builder.content)
            bundle.putString(RIGHT_TEXT, builder.rightText)
            fragment.arguments = bundle
            fragment.isCancelable = builder.cancelAble
            return fragment
        }

        fun newBuilder(): Builder {
            return Builder()
        }
    }

    private var onConfirmClickListener: (() -> Unit)? = null
    private var onCancelClickListener: (() -> Unit)? = null

    fun setOnConfirmClickListener(click: (() -> Unit)): PermissionDialog {
        this.onConfirmClickListener = click
        return this
    }

    fun setOnCancelClickListener(click: (() -> Unit)): PermissionDialog {
        this.onCancelClickListener = click
        return this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.setOnKeyListener(DialogInterface.OnKeyListener { dialog, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                return@OnKeyListener true
            }
            false
        })

        mBinding = DialogPermissionBinding.inflate(inflater, container, false)
        initView()
        return mBinding.root
    }

    private fun initView() {

        val title = this.arguments?.getString(TITLE)
        if (title.isNullOrEmpty().not()) {
            mBinding.tvTitle.visibility = View.VISIBLE
            mBinding.tvTitle.text = title
        }
        val content = this.arguments?.getString(CONTENT)
        if (content.isNullOrEmpty().not()) {
            mBinding.tvContent.visibility = View.VISIBLE
            mBinding.tvContent.text = content
        }
        val rightText = this.arguments?.getString(RIGHT_TEXT)
        if (rightText.isNullOrEmpty().not()) {
            mBinding.tvConfirm.text = rightText
        }

        mBinding.tvConfirm.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View?) {
                onConfirmClickListener?.invoke()
                dismiss()
            }
        })
        mBinding.tvCancel.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View?) {
                onCancelClickListener?.invoke()
                dismiss()
            }
        })
    }

    class Builder {
        private var mConfirmDialog: PermissionDialog? = null
        var title: String = ""
        var content: String = ""
        var rightText: String = ""
        var cancelAble: Boolean = false

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setContent(content: String): Builder {
            this.content = content
            return this
        }

        fun setRightText(rightText: String): Builder {
            this.rightText = rightText
            return this
        }

        fun setCancelAble(cancelAble: Boolean): Builder {
            this.cancelAble = cancelAble
            return this
        }

        fun build(): PermissionDialog? {
            if (this.mConfirmDialog == null) {
                this.mConfirmDialog = newInstance(this)
            }
            return this.mConfirmDialog
        }
    }
}
