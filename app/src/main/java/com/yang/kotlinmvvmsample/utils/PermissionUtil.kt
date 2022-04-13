package com.yang.kotlinmvvmsample.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat


/**
 * 檢查所有權限
 */
fun hasPermissions(context: Context, permissions: Array<String>): Boolean = permissions.all {
    ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
}