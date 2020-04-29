package com.zk.sunnyweather.logic

import androidx.lifecycle.liveData
import com.zk.sunnyweather.logic.model.Place
import com.zk.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException

/**
 *    desc   : 仓库层
 *    author : zhukai
 *    date   : 2020/4/29
 */
object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure<List<Place>>(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}