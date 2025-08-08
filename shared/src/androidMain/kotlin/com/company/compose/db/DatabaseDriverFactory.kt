// androidMain/kotlin/com/company/compose/db/DatabaseDriverFactory.kt
package com.company.compose.db

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.company.compose.data.WebsiteDatabase
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(WebsiteDatabase.Schema, context, "mydatabase.db")
    }
}
