package com.zk.sunnyweather.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zk.sunnyweather.R
import kotlinx.android.synthetic.main.fragment_place.*

/**
 *    desc   : 城市列表Fragment
 *    author : zhukai
 *    date   : 2020/4/29
 */
class PlaceFragment : Fragment() {

    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }

    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = layoutInflater.inflate(R.layout.fragment_place, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = PlaceAdapter(viewModel.placeList)
        recyclerView.adapter = adapter

        etSearchPlace.addTextChangedListener { text ->
            val content = text.toString().trim()
            if (content.isNotEmpty()) {
                viewModel.searchPlaces(content)
            } else {
                ivBg.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.placeLiveData.observe(viewLifecycleOwner, Observer { result ->
            val placeList = result.getOrNull()
            if (placeList != null) {
                ivBg.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(placeList)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}