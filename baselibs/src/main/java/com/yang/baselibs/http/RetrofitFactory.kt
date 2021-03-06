package com.yang.baselibs.http

import com.yang.baselibs.config.AppConfig
import com.yang.baselibs.http.constant.HttpConstant
import com.yang.baselibs.http.interceptor.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class RetrofitFactory<T> {

    var service: T
    private var mBaseUrl = ""
    private var retrofit: Retrofit? = null

    abstract fun baseUrl(): String

    abstract fun attachService(): Class<T>

    init {
        mBaseUrl = this.baseUrl()
        if (mBaseUrl.isEmpty()) {
            throw RuntimeException("base url can not be empty!")
        }
        service = getRetrofit()!!.create(this.attachService())
    }

    /**
     * 获取 Retrofit 实例对象
     */
    private fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            synchronized(RetrofitFactory::class.java) {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(mBaseUrl)  // baseUrl
                        .client(attachOkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
        }
        return retrofit
    }

    /**
     * 获取 OkHttpClient 实例对象
     * 子类可重写，自定义 OkHttpClient
     */
    open fun attachOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()


        //设置 请求的缓存的大小跟位置
//        val cacheFile = File(AppConfig.getApplication().cacheDir, "cache")
//        val cache = Cache(cacheFile, HttpConstant.MAX_CACHE_SIZE)

        builder.run {
            addInterceptor(initLogInterceptor())
            addInterceptor(HeaderInterceptor())
//            addInterceptor(CookieInterceptor())
//            addInterceptor(CacheInterceptor())
//            cache(cache)  //添加缓存
            connectTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true) // 错误重连
            // cookieJar(CookieManager())
        }
        return builder.build()
    }

    /**
     * 日誌攔截器
     */
    private fun initLogInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (AppConfig.debug) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }
}