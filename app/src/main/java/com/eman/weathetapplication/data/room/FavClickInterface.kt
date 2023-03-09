package com.eman.weathetapplication.data.room

import com.eman.weathetapplication.data.model.WeatherAddress
import com.eman.weathetapplication.data.model.WeatherForecast

interface FavClickInterface {
    fun onRemoveBtnClick(address: WeatherForecast)
    fun onFavItemClick(address: WeatherForecast)
}