/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.view.View
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import android.graphics.Color as gColor

@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = gColor.TRANSPARENT
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MyApp() {
    val colors = mutableStateListOf(
        Brush.verticalGradient(
            listOf(
                Color(0xff1E417F),
                Color(0xff3779AF),
            )
        ),
        Brush.verticalGradient(
            listOf(
                Color(0xff2196f3),
                Color(0xff64b5f6),
            )
        ),
        Brush.verticalGradient(
            listOf(
                Color(0xff9e9e9e),
                Color(0xffbdbdbd),
            )
        )
    )
    val index = mutableStateOf(0)
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    index.value = if (index.value == colors.size - 1) 0 else index.value + 1
                }
            ) {
                Image(imageVector = Icons.Default.NavigateNext, contentDescription = null)
            }
        },
        content = {
            Box(
                Modifier
                    .background(colors[index.value])
                    .fillMaxSize()
            ) {
                Info1()
            }
        },
    )
}

val images = listOf(
    R.drawable.ic_weather_duststorm,
    R.drawable.ic_weather_rain_showers_day,
    R.drawable.ic_weather_rain_showers_night,
    R.drawable.ic_weather_snow_shower_day,
    R.drawable.ic_weather_thunderstorm,
)

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun Info1() {
    LazyColumn {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.city),
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(top = 54.dp)
                )
                Text(text = stringResource(R.string.status), style = MaterialTheme.typography.body2)
                Text(text = stringResource(R.string.temp), style = MaterialTheme.typography.h2)
                Text(
                    text = stringResource(R.string.temp_range),
                    style = MaterialTheme.typography.body2
                )
                Divider(modifier = Modifier.padding(top = 30.dp, bottom = 16.dp))
            }
        }
        item {
            LazyRow {
                items(24) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.width(50.dp)
                    ) {
                        Text(
                            text = it.toString(),
                            style = MaterialTheme.typography.body2
                        )
                        WeatherIcon(
                            images[it % 5],
                            modifier = Modifier
                                .size(30.dp)
                                .padding(vertical = 4.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Text(text = "${(1..5).random() + 10}°")
                    }
                }
            }
        }
        item { Divider(modifier = Modifier.padding(top = 16.dp)) }
        item {
            stringArrayResource(id = R.array.weeks).forEach {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                    WeatherIcon(
                        images[(0..images.size).random() % 5],
                        modifier = Modifier
                            .size(30.dp)
                            .padding(vertical = 4.dp)
                            .align(Alignment.Center)
                    )
                    Row(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .align(Alignment.CenterEnd)
                    ) {
                        Text(text = "${(0..10).random() + 10}°")
                        Text(
                            text = "${(1..9).random()}°",
                            modifier = Modifier.padding(start = 16.dp),
                            color = Color.LightGray
                        )
                    }
                }
            }
        }
        item {
            Column {
                Divider()
                Text(
                    text = stringResource(R.string.status_detail),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Divider()
                OtherInfo()
                Divider(modifier = Modifier.padding(bottom = 30.dp))
            }
        }
    }
}

@Composable
fun WeatherIcon(@DrawableRes resId: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = null,
        modifier = modifier,
        colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
    )
}

@ExperimentalAnimationApi
@Composable
fun OtherInfo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text(
                text = stringResource(R.string.air_quality),
                style = MaterialTheme.typography.caption
            )
            Text(text = "AQI(CN)", style = MaterialTheme.typography.caption)
        }
        Text(text = stringResource(R.string.quality_status), style = MaterialTheme.typography.h5)
        Text(
            text = stringResource(R.string.quality_desc),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Divider()
        ItemInfo(
            stringResource(R.string.sunrise),
            "06:46",
            stringResource(R.string.sunset),
            "18:56"
        )
        ItemInfo(
            stringResource(R.string.change_of_snow),
            "10%",
            stringResource(R.string.humidity),
            "56%"
        )
        ItemInfo(
            stringResource(R.string.wind), stringResource(R.string.wind_detail),
            stringResource(
                R.string.feels_like
            ),
            "4"
        )
        ItemInfo(
            stringResource(R.string.precipitation),
            "0mm",
            stringResource(R.string.pressure),
            "1029hPa"
        )
        ItemInfo(
            stringResource(R.string.visibility),
            "8.1km",
            stringResource(R.string.uv_index),
            "0",
            showDivider = false
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun ItemInfo(
    key1: String,
    value1: String,
    key2: String,
    value2: String,
    showDivider: Boolean = true
) {
    Column {
        Row {
            Column(Modifier.weight(1f)) {
                Text(
                    text = key1,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
                Text(
                    text = value1,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                )
            }
            Column(Modifier.weight(1f)) {
                Text(
                    text = key2,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
                Text(
                    text = value2,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                )
            }
        }
        AnimatedVisibility(visible = showDivider) {
            Divider()
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 1100)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 1100)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}
