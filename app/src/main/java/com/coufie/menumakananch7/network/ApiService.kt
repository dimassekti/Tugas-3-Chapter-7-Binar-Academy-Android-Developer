package com.coufie.menumakananch7.network

import com.coufie.menumakananch7.model.GetAllMenuItem
import com.coufie.menumakananch7.model.GetAllUserItem
import com.coufie.menumakananch7.model.RequestUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("user")
    fun getAllUser() : Call<List<GetAllUserItem>>

    //Login
    @GET("user")
    fun getUser(@Query("email") email : String) : Call<List<GetAllUserItem>>

    //Register
    @POST("user")
    fun postUser(@Body request: RequestUser) : Call<GetAllUserItem>

    //List menu restoran
    @GET("menu-restoran")
    fun getAllMenu() : Call<List<GetAllMenuItem>>

}