package com.my.homecloud.ui.watercounter

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue


@Composable
fun WaterCounter(modifier: Modifier = Modifier) {

    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable {mutableStateOf(0)}
        if (count > 3) {
            Text(
                text = "You've had ${count} glasses.",
                modifier = modifier.padding(16.dp)
            )
        }
        Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatelessCounter(title: String, count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses of $title")
        }
        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatefullCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableIntStateOf(0) }
    var juiceCount by remember { mutableStateOf(0) }
    StatelessCounter(title = "water", count = count, onIncrement = { count++ }, modifier.padding(0.dp,0.dp,0.dp,0.dp))
    StatelessCounter(title = "juice", count = juiceCount, onIncrement = { juiceCount++ }, modifier.padding(0.dp,0.dp,0.dp,0.dp))
}

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
//        WaterCounter(modifier)
        StatefullCounter(modifier)
    }
}