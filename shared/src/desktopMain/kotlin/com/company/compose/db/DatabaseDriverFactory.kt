// desktopMain/kotlin/com/company/compose/db/DatabaseDriverFactory.kt
package com.company.compose.db

import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import com.company.compose.data.WebsiteDatabase
import com.squareup.sqldelight.db.SqlDriver
import java.io.File

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val dbPath = "mydatabase.db"
        val dbFile = File(dbPath)

        val driver = JdbcSqliteDriver("jdbc:sqlite:$dbPath")

        // 如果文件不存在，才创建数据库结构
        if (!dbFile.exists()) {
            WebsiteDatabase.Schema.create(driver)
        }

        return driver
    }
}

