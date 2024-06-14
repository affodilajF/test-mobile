package com.example.one.domain.repository

import com.example.one.data.model.ApiResponse
import retrofit2.Call
import javax.inject.Inject


interface UserApiRepository{
    fun getUsers(page: Int, perPage: Int): Call<ApiResponse>

}