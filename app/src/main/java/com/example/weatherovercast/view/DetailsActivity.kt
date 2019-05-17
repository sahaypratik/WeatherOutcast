package com.example.weatherovercast.view

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.weatherovercast.DataGenerator.DataGenerator
import com.example.weatherovercast.factory.ModelFactory
import com.example.weatherovercast.R
import com.example.weatherovercast.adapters.WeatherStatusAdapter
import com.example.weatherovercast.appconstants.StringConstants
import com.example.weatherovercast.db.MyAppDatabase
import com.example.weatherovercast.model.RespObj
import com.example.weatherovercast.repository.Repo
import com.example.weatherovercast.retrofit.ApiUtils
import com.example.weatherovercast.retrofit.UserService
import kotlinx.android.synthetic.main.activity_details.*
import java.util.*

class DetailsActivity : AppCompatActivity() {

    lateinit var userService: UserService
    lateinit var myAppDatabase: MyAppDatabase
    lateinit var cityName: String
    lateinit var list: MutableList<String>
    lateinit var dataGenerator: DataGenerator
    lateinit var model: DataGenerator

    companion object {
        lateinit var detailsActivity: DetailsActivity

        fun getInstance(): DetailsActivity {
            return detailsActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        pbLoading.visibility = View.GONE
        detailsActivity = this
        cityName = intent.getStringExtra(StringConstants.CITYNAME)
        userService = ApiUtils.getUserService()
        myAppDatabase = MyAppDatabase.getInstance(this)!!
        dataGenerator = DataGenerator(this)
        list = ArrayList<String>()
        model = ViewModelProviders.of(this, ModelFactory(this)).get(DataGenerator::class.java)
        setRvAdapter()
    }


    fun setRvAdapter() {

        pbLoading.visibility = View.VISIBLE
        rvWeatherStats.layoutManager = LinearLayoutManager(this)
        model.getList((Repo.makeDecision(this, cityName)), cityName, pbLoading)
            .observe(this, android.arch.lifecycle.Observer<MutableList<String>> { response: MutableList<String>? ->
                pbLoading.visibility = View.GONE
                if ((response?.get(0).toString()).equals(cityName, true))
                    rvWeatherStats.adapter = WeatherStatusAdapter(response, this)

            })
    }


}
