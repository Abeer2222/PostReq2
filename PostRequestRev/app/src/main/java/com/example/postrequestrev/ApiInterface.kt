package com.example.postrequestrev

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @Headers("Content-Type: application/json")
    @GET("/test/")
    fun getUser()
            : Call<List<User.UserDetails>>

    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun addUser(@Body userData: User.UserDetails)
            : Call<List<User.UserDetails>>


    @Headers("Content-Type: application/json")
    @DELETE("/test/{id}")
    fun deleteUser(@Path("id") id: Int)
            : Call<Void>

    @Headers("Content-Type: application/json")
    @PUT("/test/{id}")
    fun updateUser(
        @Path("id") id: Int,
        @Body userData: User.UserDetails
    )
            : Call<List<User.UserDetails>>

}