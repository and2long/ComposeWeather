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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import android.graphics.Color as gColor

@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
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

// Start building your app here!
@ExperimentalAnimationApi
@Composable
fun MyApp() {
    val colors = mutableStateListOf(
        Brush.verticalGradient(
            listOf(
                Color(0xffab47bc),
                Color(0xffe1bee7),
            )
        ),
        Brush.verticalGradient(
            listOf(
                Color(0xff2196f3),
                Color(0xffbbdefb),
            )
        ),
        Brush.verticalGradient(
            listOf(
                Color(0xff9e9e9e),
                Color(0xfff5f5f5),
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

@ExperimentalAnimationApi
@Composable
fun Info1() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        val images = listOf(
            R.drawable.ic_weather_duststorm,
            R.drawable.ic_weather_rain_showers_day,
            R.drawable.ic_weather_rain_showers_night,
            R.drawable.ic_weather_snow_shower_day,
            R.drawable.ic_weather_thunderstorm,
        )
        Text(text = "北京市", style = MaterialTheme.typography.h4)
        Text(text = "晴朗/有风", style = MaterialTheme.typography.body2)
        Text(text = "13", style = MaterialTheme.typography.h2)
        Text(text = "最高 13 / 最低3", style = MaterialTheme.typography.body2)
        Divider(modifier = Modifier.padding(top = 30.dp, bottom = 16.dp))
        LazyRow {
            items(24) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(50.dp)
                ) {
                    Text(text = "${it}时", style = MaterialTheme.typography.body2)
                    WeatherIcon(images[it % 5])
                    Text(text = "${(1..5).random() + 10}")
                }
            }
        }
        Divider(modifier = Modifier.padding(top = 16.dp))
        listOf("星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日").forEach {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = it, modifier = Modifier.padding(start = 16.dp))
                WeatherIcon(images[(0..images.size).random() % 5])
                Text(text = "${(1..5).random() + 10}", modifier = Modifier.padding(end = 16.dp))
            }
        }
        Divider()
        Text(
            text = "今天大部分晴朗。气温6；预计最高气温12。",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Divider()
        OtherInfo()
        Divider(modifier = Modifier.padding(bottom = 30.dp))
    }
}

@Composable
fun WeatherIcon(@DrawableRes resId: Int) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = null,
        Modifier
            .size(30.dp)
            .padding(vertical = 4.dp),
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
            Text(text = "空气质量", style = MaterialTheme.typography.caption)
            Text(text = "AQI(CN)", style = MaterialTheme.typography.caption)
        }
        Text(text = "280 - 重度污染", style = MaterialTheme.typography.h5)
        Text(text = "北京市的监测站读数。上次更新时间：1小时内。", modifier = Modifier.padding(bottom = 4.dp))
        Divider()
        ItemInfo("日出", "06:46", "日落", "18:56")
        ItemInfo("降雨概率", "10%", "湿度", "56%")
        ItemInfo("风", "西3米/秒", "体感温度", "4")
        ItemInfo("降水量", "0毫米", "气压", "1029百帕")
        ItemInfo("能见度", "8.1公里", "紫外线指数", "0", showDivider = false)
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

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 1100)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 1100)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}
