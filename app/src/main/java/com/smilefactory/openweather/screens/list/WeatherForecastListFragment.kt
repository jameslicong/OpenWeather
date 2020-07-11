package com.smilefactory.openweather.screens.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.smilefactory.openweather.R
import com.smilefactory.openweather.databinding.FragmentWeatherForecastListBinding
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class WeatherForecastListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: WeatherForecastListViewModel

    private lateinit var binding: FragmentWeatherForecastListBinding

    private lateinit var adapters: WeatherForecastListAdaper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather_forecast_list,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupSwipeRefresh()
        setupRecyclerView()

        viewModel.loadItems()
        binding.swipeRefreshLayout.isRefreshing = true

        viewModel.weatherForecastListLiveData()
            .observe(this, Observer {
                binding.swipeRefreshLayout.isRefreshing = false
                adapters.add(it)
        })

        viewModel.weatherForecastErrorLiveData()
            .observe(this, Observer { message ->
                binding.swipeRefreshLayout.isRefreshing = false
                Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
            })
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadItems()
        }
    }

    private fun setupRecyclerView() {
        adapters = WeatherForecastListAdaper(this::onSelectedCity)
        binding.recyclerView.adapter = adapters
    }

    private fun onSelectedCity(cityName: String) {
        Timber.d("$cityName I choose")
    }
}