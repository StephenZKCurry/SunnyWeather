package com.zk.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 *    desc   : 城市数据模型
 *    author : zhukai
 *    date   : 2020/4/29
 */
data class PlaceResponse(
    val status: String,
    val query: String,
    val places: List<Place>
)

data class Place(
    val id: String,
    val location: Location,
    val place_id: String,
    val name: String,
    @SerializedName("formatted_address") val address: String
)

data class Location(
    val lng: String,
    val lat: String
)