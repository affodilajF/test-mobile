package com.example.one.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.one.ui.component.Button
import com.example.one.ui.component.TopBar
import com.example.one.ui.navigation.Routes
import com.example.one.ui.viewModel.SecondScreenViewModel
import com.example.one.ui.viewModel.ThirdScreenViewModel

@Composable

fun SecondScreen(navController: NavHostController, nameValue: String) {
    val selectedNameValue = navController.currentBackStackEntry?.savedStateHandle?.get<String>("nameValueYa") ?: "Selected User Name"

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(title = "Second Screen", onClick = { navController.popBackStack() })
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 16.dp), verticalArrangement = Arrangement.SpaceBetween) {
            WelcomeTitle(nameValue)
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                SelectedUserName(nameSelected = selectedNameValue)
            }
            Button(defaultText = "Choose A User") {
                navController.navigate(Routes.THIRD_SCREEN)
            }
        }
    }
}

@Composable
fun SelectedUserName(nameSelected : String){
    Text(
        text = nameSelected,
        style = TextStyle(
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        ),
    )
}



@Composable
fun WelcomeTitle(name : String){
    Column {
        Text("Welcome ")
        Text(
            text = name,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            ),
        )
    }
}



@Preview
@Composable
fun PreviewSecondScreen(){
//    SecondScreen()
}