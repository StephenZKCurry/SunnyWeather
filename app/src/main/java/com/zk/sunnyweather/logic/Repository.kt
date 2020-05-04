package com.zk.sunnyweather.logic

import androidx.lifecycle.liveData
import com.zk.sunnyweather.logic.dao.PlaceDao
import com.zk.sunnyweather.logic.model.Place
import com.zk.sunnyweather.logic.model.Weather
import com.zk.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.lang.RuntimeException

/**
 *    desc   : 仓库层
 *    author : zhukai
 *    date   : 2020/4/29
 */
object Repository {

    fun searchPlaces(query: String) = fire<List<Place>> {
        val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    fun getWeather(lng: String, lat: String) = fire<Weather> {
        coroutineScope {
            val deferredRealtime = async {
                SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                SunnyWeatherNetwork.getDailyWeather(lng, lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                // 封装天气信息对象
                val weather = Weather(
                    realtimeResponse.result.realtime,
                    dailyResponse.result.daily
                )
                Result.success(weather)
            } else {
                Result.failure(
                    RuntimeException(
                        "realtime response status is ${realtimeResponse.status}" +
                                "daily response status is ${dailyResponse.status}"
                    )
                )
            }
        }
    }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace(): Place = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

    private fun <T> fire(block: suspend () -> Result<T>) = liveData {
        val result = try {
            block()
        } catch (e: Exception) {
            Result.failure<T>(e)
        }
        emit(result as Result<T>)
    }
}