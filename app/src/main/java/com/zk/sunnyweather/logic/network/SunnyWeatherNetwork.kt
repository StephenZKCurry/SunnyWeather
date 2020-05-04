package com.zk.sunnyweather.logic.network

/**
 *    desc   : 网络请求API封装
 *    author : zhukai
 *    date   : 2020/4/29
 */
object SunnyWeatherNetwork {

    private val placeService = ServiceCreator.create(PlaceService::class.java)
    private val weatherService = ServiceCreator.create(WeatherService::class.java)

    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query)

    suspend fun getRealtimeWeather(lng: String, lat: String) =
        weatherService.getRealtimeWeather(lng, lat)

    suspend fun getDailyWeather(lng: String, lat: String) = weatherService.getDailyWeather(lng, lat)
}
