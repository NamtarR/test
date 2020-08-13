package com.example.myapplication.web

import com.example.myapplication.data.LightHistoryItem
import com.example.myapplication.data.LightState
import com.example.myapplication.data.TokenRequest
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebClient {

    val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    val api = Retrofit.Builder()
        .baseUrl("https://google.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ApiService::class.java)

    var lamp = false

    suspend fun getLightState(): LightState {
        return withContext(Dispatchers.IO) {
            LightState(lamp, 1000, 2000)
        }
    }

    suspend fun setLightState(state: LightState) {
        return withContext(Dispatchers.IO) {
            lamp = state.lamp
        }
    }

    suspend fun setToken(token: TokenRequest) {
        return withContext(Dispatchers.IO) {
            api.setToken(token)
        }
    }

    suspend fun getHistory(): List<LightHistoryItem> {
        return listOf(
            LightHistoryItem("13.08.2020 11:00", 50),
            LightHistoryItem("13.08.2020 12:00", 60),
            LightHistoryItem("13.08.2020 13:00", 0),
            LightHistoryItem("13.08.2020 14:00", 100),
            LightHistoryItem("13.08.2020 11:00", 50),
            LightHistoryItem("13.08.2020 12:00", 60),
            LightHistoryItem("13.08.2020 13:00", 0),
            LightHistoryItem("13.08.2020 14:00", 100),
            LightHistoryItem("13.08.2020 11:00", 50),
            LightHistoryItem("13.08.2020 12:00", 60),
            LightHistoryItem("13.08.2020 13:00", 0),
            LightHistoryItem("13.08.2020 14:00", 100),
            LightHistoryItem("13.08.2020 11:00", 50),
            LightHistoryItem("13.08.2020 12:00", 60),
            LightHistoryItem("13.08.2020 13:00", 0),
            LightHistoryItem("13.08.2020 14:00", 100)
        )
    }
}