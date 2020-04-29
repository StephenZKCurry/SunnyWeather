package com.zk.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.zk.sunnyweather.logic.Repository
import com.zk.sunnyweather.logic.model.Place
import java.util.ArrayList

/**
 *    desc   : ViewModel层
 *    author : zhukai
 *    date   : 2020/4/29
 */
class PlaceViewModel : ViewModel() {

    // 搜索参数
    private val searchPlacesLiveData = MutableLiveData<String>()

    // 城市列表数据
    val placeList = ArrayList<Place>()

    val placeLiveData = searchPlacesLiveData.switchMap { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchPlacesLiveData.value = query
    }
}