package com.smilefactory.openweather.screens

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.smilefactory.openweather.R
import com.smilefactory.openweather.databinding.MainActivityBinding
import com.smilefactory.openweather.screens.list.WeatherForecastListFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, WeatherForecastListFragment())
            .commitNow()
    }
}