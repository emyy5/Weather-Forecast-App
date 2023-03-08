package com.eman.weathetapplication.data.repository

import androidx.lifecycle.LiveData
import com.eman.weathetapplication.data.model.AlertData
import com.eman.weathetapplication.data.model.Settings
import com.eman.weathetapplication.data.model.WeatherAddress
import com.eman.weathetapplication.data.model.WeatherForecast


interface RepositoryInterface {
    suspend fun getCurrentWeatherWithLocationInRepo(
        lat: Double,
        long: Double,
        unit: String
    ): WeatherForecast

    val storedAddresses: LiveData<List<WeatherAddress>>

    fun getAllWeathersInRepo(): LiveData<List<WeatherForecast>>

    fun getOneWeather(lat: Double, long: Double): LiveData<WeatherForecast>

    fun insertFavoriteAddress(address: WeatherAddress)

    fun deleteFavoriteAddress(address: WeatherAddress)

    fun insertWeather(weather: WeatherForecast)

    fun deleteWeather(weather: WeatherForecast)

    fun addSettingsToSharedPreferences(settings: Settings)
    fun getSettingsSharedPreferences(): Settings?

    fun addWeatherToSharedPreferences(weather: WeatherForecast)
    fun getWeatherSharedPreferences(): WeatherForecast?

   fun getAllAlertsInRepo(): LiveData<List<AlertData>>
      fun insertAlertInRepo(alert: AlertData)
   fun deleteAlertInRepo(alert: AlertData)
}
