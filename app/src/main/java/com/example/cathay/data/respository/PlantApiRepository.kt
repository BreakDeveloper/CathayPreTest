package com.example.cathay.data.respository

import com.example.cathay.data.PlantListResponse
import com.example.cathay.network.PlantApi

class PlantApiRepository(private val plantApi: PlantApi) {

    suspend fun getPlantList(): PlantListResponse = plantApi.getPlantList()
}