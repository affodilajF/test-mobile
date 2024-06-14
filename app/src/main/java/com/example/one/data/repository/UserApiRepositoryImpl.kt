package com.example.one.data.repository

import com.example.one.data.model.ApiResponse
import com.example.one.data.remote.UserApiService
import com.example.one.domain.repository.UserApiRepository
import retrofit2.Call
import javax.inject.Inject


class UserApiRepositoryImpl @Inject constructor(
    private val apiService: UserApiService
) : UserApiRepository {

    override fun getUsers(page: Int, perPage: Int): Call<ApiResponse> {
        return apiService.getUsers(page, perPage)
    }


}