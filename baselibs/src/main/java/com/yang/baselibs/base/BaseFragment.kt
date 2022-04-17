package com.yang.baselibs.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.yang.baselibs.ext.showToast

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    private lateinit var viewModel: VM
    private lateinit var binding: DB

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun getVM(): VM

    abstract fun bindVM(binding: DB, viewModel: VM)

    private val baseDialog by lazy { BaseLoadingDialog(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getVM()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindVM(binding, viewModel)
        with(viewModel) {
            showLoading.observe(viewLifecycleOwner) { isShow ->
                if (isShow) showLoading() else hideLoading()
            }
        }
    }

    /**
     * 可以將common loading dialog寫在此處，也可以覆寫自定義
     */
    open fun showLoading() {
        baseDialog.showLoading()
    }

    /**
     * 可以將common loading dialog寫在此處，也可以覆寫自定義
     */
    open fun hideLoading() {
        baseDialog.hideLoading()
    }

    fun launchOnLifecycleScope(execute: suspend () -> Unit) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            execute()
        }
    }

}