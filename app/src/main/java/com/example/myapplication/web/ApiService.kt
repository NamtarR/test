package com.example.myapplication.web

import com.example.myapplication.data.LightHistory
import com.example.myapplication.data.LightState
import com.example.myapplication.data.TokenRequest
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("/get/light/state")
    suspend fun getLightState(): LightState

    @POST("/set/light/state")
    suspend fun setLightState(state: LightState)

    @GET("/get/light/history")
    suspend fun getLightHistory(): LightHistory

    @POST("/set/token")
    suspend fun setToken(token: TokenRequest)
}