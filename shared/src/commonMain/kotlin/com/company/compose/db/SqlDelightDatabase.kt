package com.company.compose.db

import com.company.compose.data.Website
import com.company.compose.data.WebsiteDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import java.util.UUID

object SqlDelightDatabase {

    private lateinit var database: WebsiteDatabase

    val allWebsites: Flow<List<Website>> by lazy {
        database.websiteQueries.selectAll().asFlow().mapToList()
    }

    fun init(driverFactory: DatabaseDriverFactory) {
        val driver = driverFactory.createDriver()
        database = WebsiteDatabase(driver)
    }

    suspend fun insertWebsite(name: String, url: String) {
        database.websiteQueries.transaction {
            database.websiteQueries.insertWebsite(UUID.randomUUID().toString(), name, url)
        }
    }

    fun deleteWebsiteById(id: String) {
        database.websiteQueries.transaction {
            database.websiteQueries.deleteById(id)
        }
    }
}
