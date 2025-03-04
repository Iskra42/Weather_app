package com.example.weatherapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.constant.Const.Companion.LOADING
import com.example.weatherapp.constant.Const.Companion.NA
import com.example.weatherapp.model.weather.WeatherResult
import com.example.weatherapp.utils.Utils.Companion.buildicon
import com.example.weatherapp.utils.Utils.Companion.timestampToHumanDate
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons

@Composable
fun WeatherSection(weatherResponse: WeatherResult) {

    var title = ""
    if (!weatherResponse.name.isNullOrEmpty()) {
        weatherResponse?.name?.let {
            title = it
        }
    }
    else {
        weatherResponse.coord?.let {
            title = "${it.lat}/${it.lon}"
        }
    }

    var subTitle = ""
    val dateVal = (weatherResponse.dt ?: 0)
    subTitle = if (dateVal == 0) LOADING
    else timestampToHumanDate(dateVal.toLong(), "dd-MM-yyyy")

    var icon = ""
    var description = ""
    weatherResponse.weather.let {
        if(it!!.size > 0) {
            description = if (it[0].description == null) LOADING else
                it[0].description!!
            icon = if(it[0].icon == null) LOADING else it[0].icon!!
        }
    }

    var temp = ""
    weatherResponse.main?.let{
        temp = "${it.temp?.toInt()} ℃"
    }

    var wind = ""
    weatherResponse.wind.let {
        wind = if (it == null) LOADING else "${it.speed}"
    }
    var clouds = ""
            weatherResponse.clouds.let {
                clouds = if (it == null) LOADING else "${it.all}%"
            }



    var snow = ""
    weatherResponse.snow.let {
        snow = if (it!!.d1h == null) NA else "${it.d1h}"
    }

    WeatherTitleSection(text = title, subtext = subTitle, fontSize = 30.sp)
    WeatherImage(icon = icon)
    WeatherTitleSection(text = temp, subtext = description, fontSize = 60.sp)
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ){
        WeatherInfo(icon = FaIcons.Wind, text = wind)
        WeatherInfo(icon = FaIcons.Cloud, text = clouds)
        WeatherInfo(icon = FaIcons.Snowflake, text = snow)
    }
}

@Composable
fun WeatherInfo(icon: FaIconType.SolidIcon, text: String) {
    Column {
        FaIcon(faIcon = icon, size = 48.dp, tint = Color.White)
        Text(text, fontSize = 24.sp, color = Color.White)
    }
}

@Composable
fun WeatherImage(icon: String) {
    AsyncImage(model = buildicon(icon), contentDescription = icon,
        modifier = Modifier
            .width(200.dp)
            .height(200.dp),
        contentScale = ContentScale.FillBounds)
}

@Composable
fun WeatherTitleSection(text: String, subtext: String, fontSize: TextUnit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text, fontSize = fontSize, color = Color.White, fontWeight = FontWeight.Bold)
        Text(subtext, fontSize = 14.sp, color = Color.White)
    }
}
