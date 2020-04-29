package com.zk.sunnyweather.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zk.sunnyweather.R
import com.zk.sunnyweather.logic.model.Place

/**
 *    desc   : 城市列表Adapter
 *    author : zhukai
 *    date   : 2020/4/29
 */
class PlaceAdapter(private val placeList: List<Place>) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.tvPlaceName.text = place.name
        holder.tvPlaceAddress.text = place.address
    }

    override fun getItemCount(): Int = placeList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPlaceName: TextView = view.findViewById(R.id.tvPlaceName)
        val tvPlaceAddress: TextView = view.findViewById(R.id.tvPlaceAddress)
    }
}