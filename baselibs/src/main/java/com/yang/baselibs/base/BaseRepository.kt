package com.yang.baselibs.base

import com.yang.baselibs.http.constant.HttpErrorCode
import com.yang.baselibs.http.exception.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseRepository {

    /**
     * 在IO thread呼叫api
     */
    suspend fun <T> apiCall(call: suspend () -> BaseResponse<T>): T? {
        return withContext(Dispatchers.IO) {
            val response = call.invoke()
            executeResponse(response)
        }
    }

    /**
     * 判斷errorCode是否成功
     *
     * @return data: T
     * @throws HttpException
     */
    fun <T> executeResponse(response: BaseResponse<T>): T? {
        when (response.errorCode) {
            HttpErrorCode.SUCCESS -> {
                return response.data
            }
            else -> {
                throw HttpException(response.errorCode, response.errorMsg)
            }
        }
    }
}