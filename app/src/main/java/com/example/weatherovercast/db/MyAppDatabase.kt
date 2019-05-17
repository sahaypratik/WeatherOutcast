package com.example.weatherovercast.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.weatherovercast.model.WeatherData

@Database(entities = [WeatherData::class], version = 1, exportSchema = false)
abstract class MyAppDatabase : RoomDatabase() {
    abstract fun weatherDataDao(): WeatherDataDao

    companion object {
        private var INSTANCE: MyAppDatabase? = null

        fun getInstance(context: Context): MyAppDatabase? {
            if (INSTANCE == null) {
                synchronized(MyAppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MyAppDatabase::class.java, "weather.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
