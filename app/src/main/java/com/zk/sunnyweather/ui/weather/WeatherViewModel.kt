package com.zk.sunnyweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.zk.sunnyweather.logic.Repository
import com.zk.sunnyweather.logic.model.Location

/**
 *    desc   : ViewModel层
 *    author : zhukai
 *    date   : 2020/5/1
 */
class WeatherViewModel : ViewModel() {

    private val locationLiveData = MutableLiveData<Location>()

    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    val weatherLiveData = locationLiveData.switchMap { location ->
        Repository.getWeather(location.lng, location.lat)
    }

    fun getWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }
}