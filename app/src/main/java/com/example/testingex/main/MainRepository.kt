package com.example.testingex.main

import com.example.testingex.data.models.CurrencyResponse
import com.example.testingex.util.Resource

interface MainRepository {
    suspend fun getRates(base:String): Resource<CurrencyResponse>
}