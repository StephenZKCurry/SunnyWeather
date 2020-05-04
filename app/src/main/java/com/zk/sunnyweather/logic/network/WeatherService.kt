package com.zk.sunnyweather.logic.network

import com.zk.sunnyweather.SunnyWeatherApplication
import com.zk.sunnyweather.logic.model.DailyResponse
import com.zk.sunnyweather.logic.model.RealtimeResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *    desc   : 获取天气信息API
 *    author : zhukai
 *    date   : 2020/5/1
 */
interface WeatherService {

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    suspend fun getRealtimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): RealtimeResponse

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    suspend fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): DailyResponse
}