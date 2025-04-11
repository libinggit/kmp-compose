package com.company.compose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.company.compose.util.LogI
import com.company.compose.util.getLog

private val log: LogI = getLog()

/**
 * 计数组件。
 * 目前这个demo是用来理解State的。之所以加mutableStateOf是为了感知值的变化。加remember是为了在发生重组的时候，值不至于被重新覆盖掉
 */
@Composable
fun CounterPage() {
    val TAG = "CounterPage"
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val counter = remember { mutableStateOf(0) }
            log.printLogD(TAG, "目前值:${counter.value}")
            Text("${counter.value}")

            Button(onClick = {
                counter.value++
                log.printLogD(TAG, "增加到:${counter.value}")
            }) {
                Text("增加")
            }

            Button(onClick = {
                counter.value--
                log.printLogD(TAG, "减少到:${counter.value}")
            }) {
                Text("减少")
            }
        }

    }
}

/**
 * 给counter赋值用= 一直要对counter.value进行赋值。稍显繁琐。故可以用by来替代= 并且注意counter的修饰由val-var
 */
@Composable
fun CounterPageBy() {
    val TAG = "CounterPage"
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            var counter by remember { mutableStateOf(0) }
            log.printLogD(TAG, "目前值:${counter}")
            Text("${counter}")

            Button(onClick = {
                counter++
                log.printLogD(TAG, "增加到:${counter}")
            }) {
                Text("增加")
            }

            Button(onClick = {
                counter--
                log.printLogD(TAG, "减少到:${counter}")
            }) {
                Text("减少")
            }
        }

    }
}