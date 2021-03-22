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
package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme

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

@ExperimentalAnimationApi
@Composable
@Preview
fun OtherInfoPreview() {
    MyTheme {
        Surface(color = MaterialTheme.colors.background) {
            OtherInfo()
        }
    }
}
