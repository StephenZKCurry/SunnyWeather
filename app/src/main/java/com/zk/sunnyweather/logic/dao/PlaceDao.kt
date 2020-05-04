package com.zk.sunnyweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.zk.sunnyweather.SunnyWeatherApplication
import com.zk.sunnyweather.logic.model.Place

/**
 *    desc   : 保存选中城市
 *    author : zhukai
 *    date   : 2020/5/4
 */

object PlaceDao {

    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() = SunnyWeatherApplication.context.getSharedPreferences(
        "sunny_weather", Context.MODE_PRIVATE
    )
}