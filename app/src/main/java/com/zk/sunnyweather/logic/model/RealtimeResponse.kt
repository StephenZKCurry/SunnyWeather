package com.zk.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 *    desc   : 当天天气信息数据模型
 *    author : zhukai
 *    date   : 2020/4/29
 */
data class RealtimeResponse(val status: String, val result: Result) {

    data class Result(val realtime: Realtime)

    data class Realtime(
        val skycon: String, val temperature: Double,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: Aqi)

    data class Aqi(val chn: Double)
}

