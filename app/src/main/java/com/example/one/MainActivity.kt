package com.example.one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.one.ui.navigation.AppNavigationGraph
import com.example.one.ui.theme.OneTheme
import dagger.hilt.android.AndroidEntryPoint

import com.example.one.ui.screen.FirstScreen
import com.example.one.ui.theme.Black01

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OneTheme {
                Surface(modifier = Modifier
                    .safeDrawingPadding()
                    .fillMaxSize()
                    .background(color = Black01))
                {

//                    val navController = rememberNavController()
//                    AppNavigationGraph(navController)
//                    FirstScreen(navController = navController)

                    val navController = rememberNavController()
                    AppNavigationGraph(navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OneTheme {
        Greeting("Android")
    }
}