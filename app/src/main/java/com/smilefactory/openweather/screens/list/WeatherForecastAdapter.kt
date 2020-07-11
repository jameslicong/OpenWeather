package com.smilefactory.openweather.screens.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.smilefactory.openweather.R
import com.smilefactory.openweather.databinding.ItemWeatherForecastBinding
import com.smilefactory.openweather.repository.model.WeatherForecast

class WeatherForecastListAdaper(private var listener: (String) -> Unit) :
    RecyclerView.Adapter<WeatherForecastViewHolder>() {

    private var items: MutableList<WeatherForecast> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        val binding =
            DataBindingUtil.inflate<ItemWeatherForecastBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_weather_forecast,
                parent,
                false)

        return WeatherForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun add(list: List<WeatherForecast>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}

class WeatherForecastViewHolder(private val binding: ItemWeatherForecastBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(weatherForecast: WeatherForecast,
             clickListener: (String) -> Unit) {
        with(weatherForecast) {
            binding.cityName.text = name
            binding.temperature.text = "${main.temp}\u2103"
            binding.weather.text = weather[0].main
            binding.cardViewContainer.setBackgroundResource(
                getBackgroundColorBasedOnTemperature(main.temp))

            if (isFavorite) {
                binding.favorite.setImageDrawable(
                    ContextCompat.getDrawable(binding.root.context, R.drawable.ic_heart_favorite_24))
            } else {
                binding.favorite.setImageDrawable(
                    ContextCompat.getDrawable(binding.root.context, R.drawable.ic_heart_unfavorite_24))
            }

            itemView.setOnClickListener {
                clickListener(name)
            }
        }
    }

    private fun getBackgroundColorBasedOnTemperature(temperature: Double): Int {
        return when(temperature) {
            in Double.MIN_VALUE..0.0 -> {
                R.color.freezing
            }
            in 0.0..15.0 -> {
                R.color.cold
            }
            in 15.1..30.0 -> {
                R.color.warm
            }
            else -> {
                R.color.warm
            }
        }
    }
}