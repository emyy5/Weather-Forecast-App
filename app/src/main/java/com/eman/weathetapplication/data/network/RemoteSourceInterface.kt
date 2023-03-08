package com.eman.weathetapplication.data.network

import com.eman.weathetapplication.data.model.WeatherForecast


interface RemoteSourceInterface {
 suspend fun getCurrentWeatherWithLocation(lat:Double,long:Double,unit:String,lang:String): WeatherForecast

}