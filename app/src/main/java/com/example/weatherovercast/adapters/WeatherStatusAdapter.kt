package com.example.weatherovercast.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import com.example.weatherovercast.R
import kotlinx.android.synthetic.main.adapter_weather_status.view.*

public class WeatherStatusAdapter(val list: MutableList<String>?, val context: Context) :
    RecyclerView.Adapter<WeatherStatusAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_weather_status, p0, false))
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var title:String=""
        when(position)
        {
            0->title="City Name"
            1->title="Wind Speed"
            2->title="Temperature"
            3->title="Pressure"
            4->title="Humidity"
            5->title="Weather Description"
        }
        holder.tvTitle.text=title
        holder.tvName.text = list?.get(position)
    }

    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.tvTitle
        val tvName: TextView = itemView.tvName
    }
}