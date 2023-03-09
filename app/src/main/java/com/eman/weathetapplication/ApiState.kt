package com.eman.weathetapplication

import com.eman.weathetapplication.data.model.WeatherForecast


sealed class ApiState{
        class onSuccess(val productData: WeatherForecast):ApiState()
        class onFail(val msg: Throwable ):ApiState()
        object Loading : ApiState()
    }

