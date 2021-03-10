package com.gmind.githubuserapp.api

import com.gmind.githubuserapp.model.DetailUserResponse
import com.gmind.githubuserapp.model.SearchResponse
import com.gmind.githubuserapp.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token de0a84dd32f83ae1d7db8eebf86367c0dae5ac56")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<SearchResponse>

    @GET("users")
    @Headers("Authorization: token de0a84dd32f83ae1d7db8eebf86367c0dae5ac56")
    fun getListUser(): Call<ArrayList<User>>

    @GET("users/{username}")
    @Headers("Authorization: token de0a84dd32f83ae1d7db8eebf86367c0dae5ac56")
    fun getDetailUser(
        @Path("username") username : String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token de0a84dd32f83ae1d7db8eebf86367c0dae5ac56")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token de0a84dd32f83ae1d7db8eebf86367c0dae5ac56")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}