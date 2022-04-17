package com.yang.kotlinmvvmsample.ui.ui.dashboard

import androidx.fragment.app.viewModels
import com.yang.baselibs.base.BaseFragment
import com.yang.kotlinmvvmsample.R
import com.yang.kotlinmvvmsample.databinding.FragmentDashboardBinding

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {

    private val dashboardViewModel: DashboardViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.fragment_dashboard

    override fun getVM(): DashboardViewModel = dashboardViewModel

    override fun bindVM(binding: FragmentDashboardBinding, vm: DashboardViewModel) {

    }
}