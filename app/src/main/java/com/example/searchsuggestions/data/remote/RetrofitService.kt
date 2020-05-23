package com.example.searchsuggestions.data.remote

import com.example.searchsuggestions.data.response.AddressResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("autocomplete")
    suspend fun getAddressData(
        @Query("queryString") address: String?,
        @Query("city") city: String?
    ): Response<AddressResponse?>?
}