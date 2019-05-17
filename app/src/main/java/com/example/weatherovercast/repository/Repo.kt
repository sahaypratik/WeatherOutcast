package com.example.weatherovercast.repository

import android.content.Context
import com.example.weatherovercast.appconstants.StringConstants
import com.example.weatherovercast.preference.SharedPref
import com.example.weatherovercast.utils.TimeCalculations
import java.util.*

public class Repo{
    companion object {
        fun makeDecision(context: Context,cityname:String):Boolean
        {
            if (SharedPref.getData(context,cityname)!=null)
            {
                val f_date = Date()
                val i_date=TimeCalculations.fromISO8601UTC(SharedPref.getData(context,cityname).toString())
                return (TimeCalculations.tymDiff(f_date,i_date))>=(24*60*60*1000)
            }
            else
                return true
        }
    }
}