package com.example.cathay.network

import com.example.cathay.data.PlantListResponse
import retrofit2.http.GET

interface PlantApi {

    @GET("/api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire")
    suspend fun getPlantList(): PlantListResponse
}