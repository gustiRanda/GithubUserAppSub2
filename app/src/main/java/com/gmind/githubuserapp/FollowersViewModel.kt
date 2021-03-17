package com.gmind.githubuserapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmind.githubuserapp.api.Retrofit
import com.gmind.githubuserapp.model.User
import com.gmind.githubuserapp.model.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel(){
    val listFollowers = MutableLiveData<ArrayList<User>>()

    fun setFollowersUser(username : String){
        Retrofit.apiInstance
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<User>>{
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful){
                        listFollowers.postValue(response.body())
                        Log.d("Get Followers Success", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.d("Get Followers Failure", t.message)
                }
            })
    }

    fun getFollowersUser() : LiveData<ArrayList<User>>{
        return listFollowers
    }
}