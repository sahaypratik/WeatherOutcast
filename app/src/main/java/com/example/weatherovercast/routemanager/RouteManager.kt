package com.example.weatherovercast.routemanager

import android.content.Context
import android.content.Intent
import com.example.weatherovercast.appconstants.StringConstants
import com.example.weatherovercast.view.DetailsActivity

public class RouteManager
{
    companion object {

        fun redirectToMainActivity(context: Context,cityName:String) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(StringConstants.CITYNAME,cityName)
            context.startActivity(intent)
        }

    }

}