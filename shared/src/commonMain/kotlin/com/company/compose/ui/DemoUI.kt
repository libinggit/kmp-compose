package com.company.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.io.File


@Composable
fun NestedScrollDemo() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            Image(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                bitmap = loadImageBitmap(File("D:/beautiful.jpeg").inputStream()),
                contentDescription = "图片",
                contentScale = ContentScale.FillWidth
            )
            LazyColumn {
                item {
                    Text(text = "数字列表", modifier = Modifier.fillMaxSize())
                }
                repeat(150){
                    item {
                        Text(text = "item->$it", modifier = Modifier.fillMaxSize())
                    }
                }
            }
        }
    }
}