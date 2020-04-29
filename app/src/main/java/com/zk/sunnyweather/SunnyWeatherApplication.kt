package com.zk.sunnyweather

import android.app.Application
import android.content.Context

/**
 *    desc   : 全局Application
 *    author : zhukai
 *    date   : 2020/4/29
 */
class SunnyWeatherApplication : Application() {

    companion object {
        lateinit var context: Context
        const val TOKEN = "oRugnKDUl4xrbPNz" // 彩云天气令牌
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}