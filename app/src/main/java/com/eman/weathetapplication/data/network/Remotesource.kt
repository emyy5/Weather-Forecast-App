package com.eman.weathetapplication.data.network

import android.util.Log
import com.eman.weathetapplication.data.model.WeatherForecast
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow

private const val TAG = "RemoteSource"
class RemoteSource {

    lateinit var MyCurrentWeather: WeatherForecast
    suspend fun getCurrentWeatherWithLocation(
        lat: Double,
        long: Double,
        unit: String,
        lang: String
    ): WeatherForecast {

        Log.d(TAG, "getCurrentWeatherWithLocation lat: $lat")
        Log.d(TAG, "getCurrentWeatherWithLocation long: $long")
        Log.d(TAG, "getCurrentWeatherWithLocation unit: $unit")
        Log.d(TAG, "getCurrentWeatherWithLocation lang: $lang")
        //var result:WeatherForecast
        val apiResponse = RetrofitHelper.API.retrofitService.getTheWholeWeather(lat,
            long,
            unit,
            "minutely",
            lang,

            "375d11598481406538e244d548560243"
        )

        Log.i("TAG", "getCurrentWeatherWithLocation: in Remooooote source apiService")


        return apiResponse
    }

   fun getCurrentWeatherWithLocationInRepoFlow(
        lat: Double,
        long: Double,
        unit: String,
        lang: String
    ) = flow {
        coroutineScope {
            val serviceObj = RetrofitHelper.API.retrofitService
            val response = serviceObj.getTheWholeWeather(
                lat,
                long,
                unit,
                "minutely",
                lang,
                "375d11598481406538e244d548560243"

            )
            MyCurrentWeather = response
            emit(MyCurrentWeather)
        }

    }

    companion object {
        private var instance: RemoteSource? = null
        fun getInstance(): RemoteSource {
            return instance ?: RemoteSource()
        }
    }
}