package com.yang.kotlinmvvmsample.ui.ui.home

import androidx.fragment.app.viewModels
import com.yang.baselibs.base.BaseFragment
import com.yang.kotlinmvvmsample.R
import com.yang.kotlinmvvmsample.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private val homeViewModel: HomeViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun getVM(): HomeViewModel = homeViewModel

    override fun bindVM(binding: FragmentHomeBinding, vm: HomeViewModel) {

    }
}