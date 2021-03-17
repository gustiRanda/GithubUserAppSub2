package com.gmind.githubuserapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmind.githubuserapp.api.Retrofit
import com.gmind.githubuserapp.model.DetailUserResponse
import com.gmind.githubuserapp.model.User
import com.gmind.githubuserapp.model.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel(){
    val detailUser = MutableLiveData<DetailUserResponse>()

    fun setDetailUser(username : String){
        Retrofit.apiInstance
            .getDetailUser(username)
            .enqueue(object : Callback<DetailUserResponse>{
                override fun onResponse(
                    call: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful){
                        detailUser.postValue(response.body())
                        Log.d("Get Detail Success", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                    Log.d("Get Detail Failure", t.message)
                }
            })
    }

    fun getDetailUser(): LiveData<DetailUserResponse>{
        return detailUser
    }
}