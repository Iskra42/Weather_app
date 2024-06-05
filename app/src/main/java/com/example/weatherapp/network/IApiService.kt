package com.example.weatherapp.network

import com.example.weatherapp.constant.Const.Companion.openWeatherKey
import com.example.weatherapp.model.forecast.ForecastResult
import com.example.weatherapp.model.weather.WeatherResult
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {
    @GET("weather")
    suspend fun getWeather (
        @Query("lat") lat: Double = 0.0,
        @Query("lon") lng: Double = 0.0,
        @Query("units") units: String = "metric",
        @Query("appid") appId: String = openWeatherKey
    ): WeatherResult

    @GET("forecast")
    suspend fun getForecast (
        @Query("lat") lat: Double = 0.0,
        @Query("lon") lng: Double = 0.0,
        @Query("units") units: String = "metric",
        @Query("appid") appId: String = openWeatherKey
    ): ForecastResult
}