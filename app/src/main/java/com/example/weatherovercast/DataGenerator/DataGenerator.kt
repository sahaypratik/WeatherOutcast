package com.example.weatherovercast.DataGenerator

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.weatherovercast.db.MyAppDatabase
import com.example.weatherovercast.model.RespObj
import com.example.weatherovercast.model.WeatherData
import com.example.weatherovercast.preference.SharedPref
import com.example.weatherovercast.retrofit.ApiUtils
import com.example.weatherovercast.retrofit.UserService
import com.example.weatherovercast.utils.TimeCalculations
import com.example.weatherovercast.view.DetailsActivity
import kotlinx.android.synthetic.main.activity_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.util.*
import kotlin.collections.ArrayList

public class DataGenerator(val context: Context) : ViewModel() {

    val userService: UserService = ApiUtils.getUserService()
    lateinit var respObj: RespObj
    lateinit var c_name: String
    val myAppDatabase: MyAppDatabase = MyAppDatabase.getInstance(context)!!
    lateinit var mList: MutableList<String>
    val dataResponse = MutableLiveData<MutableList<String>>()


    fun getList(boolean: Boolean, cityname: String, pbLoading: ProgressBar): LiveData<MutableList<String>> {
        c_name = cityname
        mList = ArrayList<String>()
        if (boolean) {
            makeApiCall(c_name, pbLoading)
        } else {
            makeDbCall(c_name, pbLoading)
        }
        return dataResponse
    }


    fun makeApiCall(cityname: String, pbLoading: ProgressBar) {
        val i_date = Date()
        SharedPref.setData(context, cityname, TimeCalculations.toISO8601UTC(i_date))

        if (pbLoading != null) {
            pbLoading.visibility = View.VISIBLE
        }
        val call: Call<RespObj>
        call = userService.fetchData(cityname)
        call.enqueue(object : Callback<RespObj> {
            override fun onResponse(call: Call<RespObj>, response: Response<RespObj>) {
                if (response.code() == HttpURLConnection.HTTP_OK) {

                    respObj = response.body()!!

                    var weatherData: WeatherData = WeatherData(
                        0, respObj.name.toString(), respObj.wind!!.speed.toString(),
                        respObj.main!!.temp.toString(), respObj.main!!.pressure.toString(),
                        respObj.main!!.humidity.toString(), respObj.weather!!.get(0)!!.description.toString()
                    )
                    myAppDatabase.weatherDataDao().insertData(weatherData)
                    mList.clear()
                    mList.add(respObj.name.toString())
                    mList.add(respObj.wind!!.speed.toString())
                    mList.add(respObj.main!!.temp.toString())
                    mList.add(respObj.main!!.pressure.toString())
                    mList.add(respObj.main!!.humidity.toString())
                    mList.add(respObj.weather!!.get(0)!!.description.toString())
                    dataResponse.value = mList
                    DetailsActivity.getInstance().setRvAdapter()
                    pbLoading.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<RespObj>, t: Throwable) {

                pbLoading.visibility = View.GONE
                Toast.makeText(context, "network Error", Toast.LENGTH_SHORT).show()

            }
        })


    }


    fun makeDbCall(cityname: String, pbLoading: ProgressBar) {
        if (pbLoading != null) {
            pbLoading.visibility = View.VISIBLE
        }
        var weatherData: WeatherData = myAppDatabase.weatherDataDao().getData(cityname)
        if (weatherData != null) {
            mList.clear()
            mList.add(weatherData.cityname.toString())
            mList.add(weatherData.windspeed.toString())
            mList.add(weatherData.temperature.toString())
            mList.add(weatherData.pressure.toString())
            mList.add(weatherData.humidity.toString())
            mList.add(weatherData.weatherdescription.toString())
            dataResponse.value = mList

        } else {
            Toast.makeText(context, "No Data found by this city name, try searching for London", Toast.LENGTH_SHORT)
                .show()
        }
        pbLoading.visibility = View.GONE

    }

}