package com.example.whyphytestapp.network

import com.example.whyphytestapp.mvvm.data.respose
import com.example.whyphytestapp.mvvm.data.seatsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/b/XNB8")
    fun getScreens(): Call<respose>
    @GET("/b/0M5X")
    fun getSeats(): Call<seatsResponse>

}