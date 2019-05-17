package com.example.weatherovercast.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.weatherovercast.R
import com.example.weatherovercast.appconstants.StringConstants
import com.example.weatherovercast.preference.SharedPref
import com.example.weatherovercast.routemanager.RouteManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btSearch.setOnClickListener {
            if (etCityName.text.toString()!="") {
                RouteManager.redirectToMainActivity(this,etCityName.text.toString())
            } else
                Toast.makeText(this,"Please enter a city name",Toast.LENGTH_SHORT).show()
        }

    }
}
