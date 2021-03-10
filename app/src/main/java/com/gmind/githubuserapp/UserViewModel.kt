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

class UserViewModel : ViewModel(){
    val listUser = MutableLiveData<ArrayList<User>>()

    fun setListUser(){
        Retrofit.apiInstance
            .getListUser()
            .enqueue(object : Callback<ArrayList<User>>{
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful){
                        listUser.postValue(response.body())
                        Log.d("Success", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.d("Failure", t.message)
                }
            })
    }

    fun getListUser() : LiveData<ArrayList<User>>{
        return listUser
    }
}