package io.github.dwivedyaakash.mystore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import io.github.dwivedyaakash.mystore.ui.screens.HomeScreen
import io.github.dwivedyaakash.mystore.ui.theme.MyStoreTheme
import io.github.dwivedyaakash.mystore.viewModel.StoreViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = remember {  StoreViewModel()}

            MyStoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}
