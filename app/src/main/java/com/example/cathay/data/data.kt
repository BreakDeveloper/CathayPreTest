package com.example.cathay.data

data class PlantListResponse(
    var result: ResultData
)

data class ResultData(
    var results: List<Map<String, String>>
)