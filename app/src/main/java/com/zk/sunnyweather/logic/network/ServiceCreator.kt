package com.zk.sunnyweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *    desc   : Retrofit构建器
 *    author : zhukai
 *    date   : 2020/4/29
 */
object ServiceCreator {

    private const val BASE_URL = "https://api.caiyunapp.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}