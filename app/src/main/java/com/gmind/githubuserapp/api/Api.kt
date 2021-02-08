package com.gmind.githubuserapp.api

import com.gmind.githubuserapp.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization : Token f6e5da69f1894badde142ffa18e3efdac3f9d463")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<UserResponse>
}