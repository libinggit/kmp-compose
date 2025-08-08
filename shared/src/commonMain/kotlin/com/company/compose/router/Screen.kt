package com.company.compose.router

import com.company.compose.data.Website

/**
 * 路由类。
 */
sealed class Screen {
    /**
     * 收集网页的页面。
     */
    object WebsiteManager : Screen()


    /**
     * 视频搜索页面。
     */
    object VideoSearchPage : Screen()

    /**
     * 视频列表页面
     */
    data class VideoListPage(val website: Website) : Screen()

}