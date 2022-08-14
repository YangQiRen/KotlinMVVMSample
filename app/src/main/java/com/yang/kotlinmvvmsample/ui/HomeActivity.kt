package com.yang.kotlinmvvmsample.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yang.baselibs.base.BaseActivity
import com.yang.baselibs.base.BaseViewModel
import com.yang.baselibs.utils.ConnectivityObserver
import com.yang.baselibs.utils.NetworkConnetivityObserver
import com.yang.kotlinmvvmsample.R
import com.yang.kotlinmvvmsample.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding, BaseViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_characters, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    companion object {
        fun getIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override val layoutId: Int
        get() = R.layout.activity_home
    // 此頁面不用viewModel，先塞BaseViewModel
    override fun getVM(): BaseViewModel = BaseViewModel()

    override fun bindVM(binding: ActivityHomeBinding, viewModel: BaseViewModel) = Unit
}