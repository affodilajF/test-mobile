package com.example.one.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.one.R
import com.example.one.ui.component.Button
import com.example.one.ui.component.TextInputField
import com.example.one.ui.navigation.AppNavigationGraph
import com.example.one.ui.navigation.Routes
import com.example.one.ui.theme.Green01
import com.example.one.ui.theme.Orange01
import com.example.one.ui.theme.Purple40
import com.example.one.ui.theme.Tosca01
import com.example.one.ui.theme.Tosca02
import com.example.one.ui.viewModel.FirstScreenViewModel
import kotlin.random.Random

@Composable
fun BlurredSurface(
    content: @Composable () -> Unit
) {
    val color1 = remember {
        Tosca02.copy(alpha = 0.9f)
    }
    val color2 = remember {
        Orange01.copy(alpha = 0.9f)
    }

    val Circle1 = Offset(700f, 800f) // Pusat lingkaran atas
    val Circle2 = Offset(0f, 0f) // Pusat lingkaran bawah
    val Circle3 = Offset(100f, 2000f) // Pusat lingkaran bawah
    val Circle4 = Offset(550f, 2000f) // Pusat lingkaran bawah


    val brush1 = remember {
        Brush.radialGradient(
            colors = listOf(color2, Color.Transparent),
            center = Circle1,
            radius = 500f,
        )
    }

    val brush2 = remember {
        Brush.radialGradient(
            colors = listOf(Color.White, Color.Transparent),
            center = Circle2,
            radius = 1800f,
        )
    }

    val brush3 = remember {
        Brush.radialGradient(
            colors = listOf(color1, Color.Transparent),
            center = Circle3,
            radius = 800f,
        )
    }

    val brush4 = remember {
        Brush.radialGradient(
            colors = listOf(Color.White, Color.Transparent),
            center = Circle4,
            radius = 800f,
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Tosca02,
        contentColor = Color.Transparent,
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                // Lingkaran atas
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = brush1)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = brush4)
                )

                // Lingkaran bawah 1
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = brush2)
                )

                // Lingkaran bawah 2
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = brush3)
                )

                content()
            }
        }
    )
}

//fun FirstScreen(navController: NavController){

@Composable
fun FirstScreen(navController: NavHostController) {

    val viewModel: FirstScreenViewModel = hiltViewModel()
    var nameValue by remember { mutableStateOf("") }
    var palindromeValue by remember { mutableStateOf("") }
    var isPalindrome by remember { mutableStateOf(false) }
    var dialogShown by remember { mutableStateOf(false) }


    BlurredSurface {

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp, vertical = 22.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.userimgicon),
                    contentDescription = "user img",
                    modifier = Modifier
                        .size(116.dp)
                        .padding(4.dp)
                )

                Spacer(modifier = Modifier.height(60.dp))

                TextInputField("Name") { newValue ->
                    nameValue = newValue
                }
                Spacer(modifier = Modifier.height(16.dp))

                TextInputField("Palindrome") { newValue ->
                    palindromeValue = newValue
                }
                Spacer(modifier = Modifier.height(60.dp))

                Button(defaultText = "CEK") {
                    isPalindrome = viewModel.isPalindrome(palindromeValue)
                    Log.d("PalindromeCheck", "Is Palindrome: $isPalindrome")
                    dialogShown = true
                }
                Spacer(modifier = Modifier.height(16.dp))

                if (dialogShown) {
                    showResultDialog(isPalindrome) {
                        dialogShown = false
                    }
                }
                Button(defaultText = "NEXT") {
                    val finalNameValue = if (nameValue.isEmpty()) "John Doe" else nameValue
                    Log.d("FirstScreen", "Name Value: $finalNameValue")

                    val route = Routes.SECOND_SCREEN + "/$finalNameValue"
                    navController.navigate(route)
                }
            }
        }
    }
}

@Composable
fun showResultDialog(isPalindrome: Boolean, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Text(
                if (isPalindrome) {
                    "Palindrome"
                } else {
                    "Bukan Palindrome"
                },
                style = TextStyle(
                    color = Color.Black, // Ubah warna teks menjadi hitam
                    fontSize = 16.sp, // Atur ukuran teks
                    fontWeight = FontWeight.Bold, // Atur tebal teks
                    textAlign = TextAlign.Center // Atur penyejajaran teks
                )
            )
        },
        confirmButton = {
        }
    )
}



@Preview
@Composable
fun FirstScreenPreview(){
//    FirstScreen()
}