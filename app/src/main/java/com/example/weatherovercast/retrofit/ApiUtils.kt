package com.example.weatherovercast.retrofit

public class ApiUtils
{


    companion object {

        val BASE_URL = "https://samples.openweathermap.org/"

        fun getUserService(): UserService {
            return RetrofitClient.getClient(BASE_URL)!!.create(UserService::class.java)

        }
    }

}