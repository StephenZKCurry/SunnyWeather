package com.zk.sunnyweather.logic.network

import com.zk.sunnyweather.SunnyWeatherApplication
import com.zk.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *    desc   : 城市搜索API
 *    author : zhukai
 *    date   : 2020/4/29
 */
interface PlaceService {

    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    suspend fun searchPlaces(@Query("query") query: String): PlaceResponse
}