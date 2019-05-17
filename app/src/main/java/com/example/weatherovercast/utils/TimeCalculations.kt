package com.example.weatherovercast.utils

import java.text.SimpleDateFormat
import java.util.*

public class TimeCalculations{
    companion object {
        fun toISO8601UTC(date: Date): String {
            val tz = TimeZone.getTimeZone("UTC")
            val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
            df.timeZone = tz
            return df.format(date)
        }

        fun fromISO8601UTC(str: String): Date{
          return  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'").parse(str)
        }

        fun tymDiff(date1: Date, date2: Date): Long {
            return date1.time - date2.time
        }



    }
}