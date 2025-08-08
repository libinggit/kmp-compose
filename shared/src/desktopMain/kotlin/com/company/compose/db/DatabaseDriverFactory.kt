// desktopMain/kotlin/com/company/compose/db/DatabaseDriverFactory.kt
package com.company.compose.db

import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import com.company.compose.data.WebsiteDatabase
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver("jdbc:sqlite:mydatabase.db")
        WebsiteDatabase.Schema.create(driver)
        return driver
    }
}
