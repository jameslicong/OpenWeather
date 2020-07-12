package com.smilefactory.openweather.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.smilefactory.openweather.R
import com.smilefactory.openweather.databinding.FragmentWeatherForecastDetailsBinding
import com.smilefactory.openweather.repository.model.WeatherForecast
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class WeatherForecastDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: WeatherForecastDetailsViewModel

    private lateinit var binding: FragmentWeatherForecastDetailsBinding

    private var currentWeatherForecast: WeatherForecast? = null

    companion object {

        const val CITY_NAME_TAG = "city_name"

        fun getFragment(cityName: String): WeatherForecastDetailsFragment {
            val fragment =  WeatherForecastDetailsFragment()
            var bundle = Bundle()
            bundle.putString(CITY_NAME_TAG, cityName)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather_forecast_details,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var cityName = arguments?.getString(CITY_NAME_TAG)
        cityName?.let {
            Timber.d("City Name here $cityName")
            viewModel.loadDetails(it)
        }

        binding.favorite.setOnClickListener {
            currentWeatherForecast?.let {
                viewModel.setAsFavorite(!it.isFavorite, it.name)
            }
        }

        viewModel.weatherForecastLiveData().observe(
            this, Observer { weatherForecast ->

                currentWeatherForecast = weatherForecast
                with(weatherForecast) {
                    binding.cityName.text = name
                    binding.temperature.text = "${main.temp}\u2103"
                    binding.weather.text = weather[0].main
                    binding.highAndLowTemperature.text =
                        "High ${main.tempMax}\u2103 / Low ${main.tempMin}\u2103"

                    if (isFavorite) {
                        binding.favorite.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.root.context, R.drawable.ic_heart_favorite_24))
                    } else {
                        binding.favorite.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.root.context, R.drawable.ic_heart_unfavorite_24))
                    }
                }
        })
    }
}