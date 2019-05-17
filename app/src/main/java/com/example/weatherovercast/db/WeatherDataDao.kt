package com.example.weatherovercast.db

import android.arch.persistence.room.*
import com.example.weatherovercast.model.WeatherData

@Dao
interface WeatherDataDao{

    @Insert
    fun insertData(weatherData: WeatherData)

    @Query("SELECT * FROM weatherData WHERE cityname LIKE :city ORDER BY cityname DESC LIMIT 1")
    fun getData(city:String):WeatherData
}