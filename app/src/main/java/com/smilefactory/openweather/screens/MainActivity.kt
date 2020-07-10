package com.smilefactory.openweather.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smilefactory.openweather.R
import com.smilefactory.openweather.screens.list.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}