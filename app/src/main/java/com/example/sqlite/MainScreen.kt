package com.example.sqlite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sqlite.MainViewModel.Companion.factory
import com.example.sqlite.ui.theme.SQLiteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)) {
    val itemsList = mainViewModel.itemsList.collectAsState(initial = emptyList())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.fonreg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .padding(top = 100.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                TextField(
                    value = mainViewModel.newText1.value,
                    onValueChange = { mainViewModel.newText1.value = it },
                    label = { Text(text = "Name") },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Magenta)
                )
            }
            item {
                TextField(
                    value = mainViewModel.newText2.value,
                    onValueChange = { mainViewModel.newText2.value = it },
                    label = { Text(text = "Password") },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Magenta)
                )
            }
            item {
                IconButton(onClick = {
                    mainViewModel.insertItem()
                }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                }
            }
            item {
                Spacer(modifier = Modifier.height(5.dp))
            }
            items(itemsList.value) { item ->
                ListItem(
                    item, {
                        mainViewModel.nameEntity = it
                        mainViewModel.newText1.value = it.name
                        mainViewModel.newText2.value = it.password
                    },
                    {
                        mainViewModel.deleteItem(it)
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SQLiteTheme {
        MainScreen()
    }
}
