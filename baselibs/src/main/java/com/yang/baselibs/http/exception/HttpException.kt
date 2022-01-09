package com.yang.baselibs.http.exception


class HttpException(
    var errorCode: Int,
    var errorMsg: String,
    var throwable: Throwable? = null
): Exception() {
}