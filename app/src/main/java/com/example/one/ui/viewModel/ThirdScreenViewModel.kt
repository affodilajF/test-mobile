package com.example.one.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.one.data.model.ApiResponse
import com.example.one.domain.repository.UserApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ThirdScreenViewModel @Inject constructor(
    private val userApiRepository: UserApiRepository
) : ViewModel() {

    private val _userData = MutableLiveData<ApiResponse>()
    val userData: LiveData<ApiResponse> = _userData

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private val _allDataLoaded = MutableLiveData<Boolean>(false) // Flag untuk menunjukkan apakah semua data telah dimuat

    var isLastPage: Boolean = false

    private var currentPage: Int = 1 // Menyimpan nomor halaman saat ini

    fun fetchData(page: Int, perPage: Int) {
        _isRefreshing.value = true // Menandai bahwa sedang dilakukan pengambilan data baru
        userApiRepository.getUsers(page, perPage).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                _isRefreshing.value = false // Menandai bahwa pengambilan data selesai
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null) {
                        if (currentPage == 1) {
                            // Jika halaman pertama, timpa data yang ada
                            _userData.value = apiResponse
                        } else {
                            // Jika bukan halaman pertama, tambahkan data ke LiveData yang ada
                            val currentData = _userData.value
                            val newData = currentData?.data.orEmpty() + apiResponse.data
                            _userData.value = ApiResponse(apiResponse.page, apiResponse.perPage, apiResponse.total, apiResponse.totalPages, newData)
                        }
                        // Periksa apakah halaman terakhir telah tercapai
                        isLastPage = currentPage >= apiResponse.totalPages
                        // Periksa apakah semua data telah dimuat
                        _allDataLoaded.value = apiResponse.data.size < perPage
                    }
                } else {
                    // Handle error response
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                _isRefreshing.value = false // Menandai bahwa pengambilan data selesai karena gagal
                // Handle failure
            }
        })
    }

    fun refreshData() {
        // Memuat ulang halaman pertama dengan perPage yang baru
        currentPage = 1 // Reset halaman ke 1
        fetchData(page = 1, perPage = 12)
    }

    fun fetchNextPage() {
        if (!isLastPage) {
            // Jika tidak mencapai halaman terakhir, tambahkan nomor halaman
            currentPage++
            fetchData(page = currentPage, perPage = 12)
        }
    }
}





