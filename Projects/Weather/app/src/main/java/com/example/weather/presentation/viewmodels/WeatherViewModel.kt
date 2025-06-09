package com.example.weather.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.constants.Constant
import com.example.weather.data.remote.NetworkResponse
import com.example.weather.data.remote.RetrofitInstance
import com.example.weather.data.remote.WeatherModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor() : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherAPI
    private val _weatherResponse = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResponse

    fun getData(city:String){
        _weatherResponse.value = NetworkResponse.Loading

        viewModelScope.launch {
           val response =  weatherApi.getWeather(Constant.apiKey,city)

           try {
               if (response.isSuccessful) {
                   response.body()?.let {
                       _weatherResponse.value = NetworkResponse.success(it)
                   }
               } else {
                   _weatherResponse.value = NetworkResponse.Error("Failed to load data!")

               }
           }catch (e: Exception){
               _weatherResponse.value = NetworkResponse.Error("Failed to load data!Check internet")
           }

        }

    }
}