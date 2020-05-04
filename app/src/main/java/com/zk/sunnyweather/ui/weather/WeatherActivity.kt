package com.zk.sunnyweather.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zk.sunnyweather.R
import com.zk.sunnyweather.logic.model.Weather
import com.zk.sunnyweather.logic.model.getSky
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.layout_life_index.*
import kotlinx.android.synthetic.main.layout_weather_forecast.*
import kotlinx.android.synthetic.main.layout_weather_now.*
import java.text.SimpleDateFormat
import java.util.*

/**
 *    desc   : 天气信息Activity
 *    author : zhukai
 *    date   : 2020/5/1
 */
class WeatherActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        viewModel.locationLng = intent.getStringExtra("location_lng") ?: ""
        viewModel.locationLat = intent.getStringExtra("location_lat") ?: ""
        viewModel.placeName = intent.getStringExtra("place_name") ?: ""
        viewModel.weatherLiveData.observe(this, Observer { result ->
            val weather = result.getOrNull()
            if (weather != null) {
                showWeatherInfo(weather)
            } else {
                Toast.makeText(this, "无法成功获取天气信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        // 获取天气信息
        viewModel.getWeather(viewModel.locationLng, viewModel.locationLat)
    }

    private fun showWeatherInfo(weather: Weather) {
        scrollView.visibility = View.VISIBLE
        tvPlaceName.text = viewModel.placeName
        val realtime = weather.realtime
        val daily = weather.daily

        tvCurrentTemp.text = "${realtime.temperature.toInt()}°C"
        tvCurrentSky.text = getSky(realtime.skycon).info
        tvCurrentAQI.text = "空气指数 ${realtime.airQuality.aqi.chn.toInt()}"
        rlNowWeather.setBackgroundResource(getSky(realtime.skycon).bg)

        llForecast.removeAllViews()
        for (i in daily.skycon.indices) {
            val skycon = daily.skycon[i]
            val temperature = daily.temperature[i]
            val view = LayoutInflater.from(this).inflate(R.layout.item_forecast, llForecast, false)
            val tvDateInfo = view.findViewById(R.id.tvDateInfo) as TextView
            val ivSkyIcon = view.findViewById(R.id.ivSkyIcon) as ImageView
            val tvSkyInfo = view.findViewById(R.id.tvSkyInfo) as TextView
            val tvTemperatureInfo = view.findViewById(R.id.tvTemperatureInfo) as TextView
            tvDateInfo.text =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(skycon.date)
            ivSkyIcon.setImageResource(getSky(skycon.value).icon)
            tvSkyInfo.text = getSky(skycon.value).info
            tvTemperatureInfo.text = "${temperature.min.toInt()}~${temperature.max.toInt()}°C"
            llForecast.addView(view)
        }

        val lifeIndex = daily.lifeIndex
        tvColdRiskText.text = lifeIndex.coldRisk[0].desc
        tvDressingText.text = lifeIndex.dressing[0].desc
        tvUltravioletText.text = lifeIndex.ultraviolet[0].desc
        tvCarWashingText.text = lifeIndex.carWashing[0].desc
    }
}