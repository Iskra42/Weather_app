package com.example.weatherapp.model.weather

import com.google.gson.annotations.SerializedName

data class Main (
    @SerializedName("temp") var temp: Double? = null,
)