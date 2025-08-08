package com.company.compose.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.company.compose.db.DatabaseDriverFactory
import com.company.compose.db.SqlDelightDatabase
import com.company.compose.router.AppRouter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SqlDelightDatabase.init(DatabaseDriverFactory(this))

        setContent {
            MyApplicationTheme {
                AppRouter()
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        AppRouter()
    }
}
