package com.company.compose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.company.compose.data.Website

@Composable
fun VideoPlayPage(){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoListPage(
    website: Website,
    onNavigateBack: (() -> Unit)? = null
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Column {
                        Text(text = website.name, style = MaterialTheme.typography.headlineSmall)
                        Text(text = website.url, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                    }
                },
                actions = {
                    if (onNavigateBack != null) {
                        TextButton(onClick = { onNavigateBack() }) {
                            Text("返回")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        // 页面内容区域，可以根据需要继续填充
        Box(modifier = Modifier.padding(paddingValues)) {
            // 这里先空着或者写提示
            Text("这里是视频列表页面，网站：${website.name}", modifier = Modifier.padding(16.dp))
        }
    }
}
