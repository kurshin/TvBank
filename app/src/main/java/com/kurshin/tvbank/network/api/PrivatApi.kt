package com.kurshin.tvbank.network.api

import com.kurshin.tvbank.network.model.balance_response.ResponseBalance
import retrofit2.http.Body
import retrofit2.http.POST

interface PrivatApi {

    @POST("p24api/balance")
    suspend fun currentBalance(@Body request: String): ResponseBalance

    @POST("p24api/rest_fiz")
    suspend fun balanceHistory(@Body request: String): ResponseBalance
}