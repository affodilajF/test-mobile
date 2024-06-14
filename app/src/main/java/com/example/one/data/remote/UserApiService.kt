package com.example.one.data.remote

import com.example.one.data.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {

    @GET("/api/users")
    fun getUsers(@Query("page") page: Int, @Query("per_page") perPage: Int): Call<ApiResponse>
}