package com.yang.kotlinmvvmsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.yang.baselibs.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusBarColor(ContextCompat.getColor(this, R.color.design_default_color_primary_dark))
        setStatusBarIcon(false)
    }
}