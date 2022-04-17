package com.yang.kotlinmvvmsample.ui.ui.notifications

import androidx.fragment.app.viewModels
import com.yang.baselibs.base.BaseFragment
import com.yang.kotlinmvvmsample.R
import com.yang.kotlinmvvmsample.databinding.FragmentNotificationsBinding

class NotificationsFragment : BaseFragment<FragmentNotificationsBinding, NotificationsViewModel>() {

    private val notificationsViewModel: NotificationsViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.fragment_notifications

    override fun getVM(): NotificationsViewModel = notificationsViewModel

    override fun bindVM(binding: FragmentNotificationsBinding, vm: NotificationsViewModel) {

    }
}