package com.example.weatherovercast.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.example.weatherovercast.DataGenerator.DataGenerator

public class ModelFactory(var context: Context):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DataGenerator(context) as T
    }

}