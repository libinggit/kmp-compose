import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.company.compose.data.Website
import com.company.compose.db.SqlDelightDatabase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebsiteManagerUI() {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var url by remember { mutableStateOf(TextFieldValue("")) }
    var websites by remember { mutableStateOf(listOf<Website>()) }

    val scope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }

    // 监听数据库中的所有网站数据，自动更新 UI
    LaunchedEffect(Unit) {
        SqlDelightDatabase.allWebsites.collectLatest { list ->
            websites = list
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(paddingValues)  // 让内容避免被 Snackbar 或顶部栏挡住
        ) {

            Text(
                "添加新网站",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("网站名称") },
                placeholder = { Text("请输入网站名称") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = url,
                onValueChange = { url = it },
                label = { Text("网站地址") },
                placeholder = { Text("请输入网站地址，例如 https://example.com") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            )

            Button(
                onClick = {
                    if (name.text.isNotBlank() && url.text.isNotBlank()) {
                        scope.launch {
                            SqlDelightDatabase.insertWebsite(
                                name.text,
                                url.text
                            )
                            // 清空输入框
                            name = TextFieldValue("")
                            url = TextFieldValue("")
                        }
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("网站名称和地址不能为空，请输入完整信息。")
                        }
                    }

                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("保存")
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                "已保存网站列表",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(websites, key = { it.id }) { website ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(website.name, style = MaterialTheme.typography.titleMedium)
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    website.url,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            IconButton(onClick = {
                                scope.launch {
                                    // 调用删除方法
                                    SqlDelightDatabase.deleteWebsiteById(website.id)
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "删除"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
