package com.yang.baselibs.http.interceptor

import com.yang.baselibs.http.constant.HttpConstant
import com.yang.baselibs.utils.SharedPreferencesUtils
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {

    private val preferences by lazy { SharedPreferencesUtils() }

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val builder = request.newBuilder()

        builder.addHeader("Content-type", "application/json; charset=utf-8")
//         .header("token", preferences.token!!)
        // .method(request.method(), request.body())

        val domain = request.url.host
        val url = request.url.toString()
        if (domain.isNotEmpty()) {
            val spDomain: String = preferences.domain!!
            val cookie: String = if (spDomain.isNotEmpty()) spDomain else ""
            if (cookie.isNotEmpty()) {
                // 将 Cookie 添加到请求头
                builder.addHeader(HttpConstant.COOKIE_NAME, cookie)
            }
        }

        return chain.proceed(builder.build())
    }
}