package com.gmind.githubuserapp.api

import com.gmind.githubuserapp.model.DetailUserResponse
import com.gmind.githubuserapp.model.Repository
import com.gmind.githubuserapp.model.SearchResponse
import com.gmind.githubuserapp.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<SearchResponse>

    @GET("users")
    @Headers("Authorization: token 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")
    fun getListUser(): Call<ArrayList<User>>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username : String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/repos")
    @Headers("Authorization: token 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")
    fun getRepository(
        @Path("username") username: String
    ): Call<ArrayList<Repository>>
}