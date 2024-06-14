package com.example.one.ui.screen

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.one.data.model.UserResponse
import com.example.one.ui.viewModel.ThirdScreenViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*


import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberImagePainter
import com.example.one.ui.component.Button
import com.example.one.ui.component.TopBar
import com.example.one.ui.navigation.Routes
import com.example.one.ui.theme.Grey03
import com.google.firebase.firestore.auth.User

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch


//fun ThirdScreen(navController: NavHostController){
//@Composable
//fun ThirdScreen(){

// Di ThirdScreen

@Composable
fun ThirdScreen(navController: NavHostController) {
    val viewModel: ThirdScreenViewModel = hiltViewModel()
    val userData by viewModel.userData.observeAsState()
    val isRefreshing by viewModel.isRefreshing.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchData(page = 1, perPage = 5)
    }

    SwipeToRefreshLayout(
        refreshingState = isRefreshing ?: false,
        onRefresh = {
            // Memuat ulang halaman pertama dengan perPage yang baru (12)
            viewModel.fetchData(page = 1, perPage = 12)
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(title = "Third Screen", onClick = { navController.popBackStack() })
            userData?.let { response ->
                if (response.data.isNotEmpty()) {
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        items(response.data) { user ->
                            UserItem(user) { selectedUser ->
                                val name = "${selectedUser.firstName} ${selectedUser.lastName}"
                                navController.previousBackStackEntry?.savedStateHandle?.set("nameValueYa", name)
                                navController.popBackStack()
                            }
                        }
                        // Memuat halaman berikutnya saat mencapai bagian bawah daftar
                        if (!viewModel.isLastPage) {
                            viewModel.fetchNextPage()
                        }
                    }
                } else {
                    // Tampilkan pesan kosong jika data kosong
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Data kosong")
                    }
                }
            }
        }
    }
}





//@Composable
//fun ThirdScreen(navController: NavHostController) {
//    val viewModel: ThirdScreenViewModel = hiltViewModel()
//    val userData by viewModel.userData.observeAsState()
//
//    LaunchedEffect(Unit) {
//        viewModel.fetchData(page = 1, perPage = 10)
//    }
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        TopBar(title = "Third Screen", onClick = { navController.popBackStack() })
//        userData?.let { response ->
//            LazyColumn {
//                items(response.data) { user ->
//                    Log.d("Ya", user.toString())
//                    UserItem(user) { selectedUser ->
//
//                        val name = selectedUser.firstName +" "+ selectedUser.lastName
//                        navController.previousBackStackEntry?.savedStateHandle?.set("nameValueYa", name)
//                        navController.popBackStack()
//
//                    }
//                }
//            }
//        }
//    }
//}



@Composable
fun SwipeToRefreshLayout(
    refreshingState: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {
    val state = rememberSwipeRefreshState(isRefreshing = refreshingState)
    SwipeRefresh(
        state = state,
        onRefresh = onRefresh
    ) {
        content()
    }
}





@Composable
//fun UserItem(user: UserResponse,  onItemClick: () -> Unit) {
fun UserItem(user: UserResponse, onItemClick: (UserResponse) -> Unit) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { onItemClick(user) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(data = user.avatar), // Menggunakan rememberImagePainter dari coil-compose
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            // Menampilkan informasi nama dan email di sebelah kanan
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                // Baris pertama: First Name dan Last Name
                Text(
                    text = "${user.firstName} ${user.lastName}",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
                // Baris kedua: Email
                Text(
                    text = user.email,
                    style = TextStyle(color = Color.Gray)
                )
            }

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
fun PreviewThirdScreen(){
//    SecondScreen()
//    ThirdScreen()
}