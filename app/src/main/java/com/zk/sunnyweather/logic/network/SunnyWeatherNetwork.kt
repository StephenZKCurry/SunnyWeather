package com.zk.sunnyweather.logic.network

/**
 *    desc   : 网络请求API封装
 *    author : zhukai
 *    date   : 2020/4/29
 */
object SunnyWeatherNetwork {

    private val placeService = ServiceCreator.create(PlaceService::class.java)

    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query)
}
