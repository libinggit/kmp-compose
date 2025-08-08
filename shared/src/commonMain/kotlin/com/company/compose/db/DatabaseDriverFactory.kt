// shared/src/commonMain/kotlin/com/company/compose/db/DatabaseDriverFactory.kt
package com.company.compose.db

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}
