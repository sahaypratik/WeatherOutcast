package com.example.weatherovercast.retrofit

import com.example.weatherovercast.model.RespObj
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService
{

    @GET("data/2.5/weather?APPID=5ba0930c707828ea8bb43acf7a93a029&")
    abstract fun fetchData(@Query("q") cityname: String): Call<RespObj>

}