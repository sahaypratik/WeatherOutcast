package com.example.weatherovercast.preference

import android.content.Context

public class SharedPref
{
    companion object {

        fun getData(context: Context?, key: String): String? {
            if (context != null) {
                val sharedPref = context.getSharedPreferences(
                    "token",
                    Context.MODE_PRIVATE
                )
                return sharedPref.getString(key, null)
            } else {
                return null
            }

        }

        fun setData(context: Context, key: String, value: String) {
            val sharedPref = context.getSharedPreferences(
                "token",
                Context.MODE_PRIVATE
            )
            val editor = sharedPref.edit()
            editor.putString(key, value)
            editor.apply()
        }
    }

}