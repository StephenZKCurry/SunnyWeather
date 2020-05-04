package com.zk.sunnyweather.logic.model

/**
 *    desc   : 天气信息数据模型
 *    author : zhukai
 *    date   : 2020/5/1
 */
data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)