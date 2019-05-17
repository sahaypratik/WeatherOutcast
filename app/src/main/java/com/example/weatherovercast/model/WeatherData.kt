package com.example.weatherovercast.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "weatherData")
data class WeatherData(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var cityname: String? = null,
    var windspeed: String? = null,
    var temperature: String? = null,
    var pressure: String? = null,
    var humidity: String? = null,
    var weatherdescription: String? = null
){

}
