package com.gmind.githubuserapp.api

import com.gmind.githubuserapp.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 3931a280d7814e51f90c8b40e7ac79ce95922e5d")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<UserResponse>
}