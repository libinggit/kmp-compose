package com.company.compose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.company.compose.data.Website
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.regex.Pattern
import androidx.compose.foundation.lazy.items
import java.awt.Desktop
import java.net.URI

@Composable
fun VideoPlayPage(){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoListPage(
    website: Website,
    onNavigateBack: (() -> Unit)? = null
) {
    val client = remember { HttpClient(CIO) }
    var videoUrls by remember { mutableStateOf<List<String>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(website.url) {
        isLoading = true
        errorMsg = null
        try {
            val html = withContext(Dispatchers.IO) {
                client.get(website.url).body<String>()
            }
            videoUrls = extractVideoUrlsFromHtml(html,website.url)
        } catch (e: Exception) {
            errorMsg = "加载网页失败：${e.localizedMessage}"
        }
        isLoading = false
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Column {
                        Text(text = website.name, style = MaterialTheme.typography.headlineSmall)
                        Text(text = website.url, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary, modifier = Modifier.clickable {
                            println("点击网页链接: ${website.url}")
                            // 示例：用默认浏览器打开链接 (Windows Desktop)
                            if (Desktop.isDesktopSupported()) {
                                try {
                                    Desktop.getDesktop().browse(URI(website.url))
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        })
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
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                errorMsg != null -> {
                    Text(
                        text = errorMsg!!,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.Center)
                    )
                }
                else -> {
                    if (videoUrls.isEmpty()) {
                        Text(
                            text = "未发现视频链接",
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxSize().padding(16.dp)
                        ) {
                            items(videoUrls) { videoUrl ->
                                Text(
                                    text = videoUrl,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .clickable {
                                            println("点击视频链接: $videoUrl")
                                            // 示例：用默认浏览器打开链接 (Windows Desktop)
                                            if (Desktop.isDesktopSupported()) {
                                                try {
                                                    Desktop.getDesktop().browse(URI(videoUrl))
                                                } catch (e: Exception) {
                                                    e.printStackTrace()
                                                }
                                            }
                                        }
                                )
                            }


                        }
                    }
                }
            }
        }
    }
}

// 简单用正则提取网页中 video 标签的 src 和 source 标签的 src
fun extractVideoUrlsFromHtml(html: String, baseUrl: String): List<String> {
    val urls = mutableSetOf<String>()

    val videoTagPattern = Pattern.compile("""<video[^>]*src=["']([^"']+)["']""", Pattern.CASE_INSENSITIVE)
    val sourceTagPattern = Pattern.compile("""<source[^>]*src=["']([^"']+)["']""", Pattern.CASE_INSENSITIVE)

    fun resolveUrl(url: String): String {
        return if (url.startsWith("http://") || url.startsWith("https://")) {
            url
        } else {
            URI(baseUrl).resolve(url).toString()
        }
    }

    val matcher1 = videoTagPattern.matcher(html)
    while (matcher1.find()) {
        val rawUrl = matcher1.group(1)
        urls.add(resolveUrl(rawUrl))
    }

    val matcher2 = sourceTagPattern.matcher(html)
    while (matcher2.find()) {
        val rawUrl = matcher2.group(1)
        urls.add(resolveUrl(rawUrl))
    }

    return urls.toList()
}


