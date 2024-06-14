package com.example.one.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.one.R
import com.example.one.ui.theme.Grey03

@Composable
fun TopBar(title : String, onClick: () -> Unit ){

    Box(
        modifier = Modifier
            .height(56.dp) // Tetapkan tinggi di sini
            .background(color = Color.White)
            .fillMaxWidth()
        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center // Teks berada di tengah
            )
        )

        Icon(
            painter = painterResource(R.drawable.baseline_keyboard_arrow_left_24),
            contentDescription = "Icon",
            tint = Color.Blue,
            modifier = Modifier.padding(horizontal = 10.dp).size(40.dp).align(Alignment.CenterStart)
                .clickable { onClick() }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp) // Tetapkan tinggi di sini
            .background(color = Grey03.copy(alpha = 0.1f))
        ,
        contentAlignment = Alignment.Center
    ) {
    }

}


@Preview
@Composable
fun TopBarPreview(){
    TopBar(title = "Check"){}
}