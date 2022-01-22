package com.yang.baselibs.base

data class BaseResponse<out T>(
    val errorCode: Int,
    val errorMsg: String,
    val data: T
)