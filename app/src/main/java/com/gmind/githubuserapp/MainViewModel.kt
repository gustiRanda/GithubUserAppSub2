package com.gmind.githubuserapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmind.githubuserapp.api.Retrofit
import com.gmind.githubuserapp.model.User
import com.gmind.githubuserapp.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(){
    val listUser = MutableLiveData<ArrayList<User>>()

    fun setSearchUser(query: String){
        Retrofit.apiInstance
            .getSearchUser(query)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful){
                        listUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Failure", t.message)
                }
            })
    }

    fun getSearchUser() : LiveData<ArrayList<User>>{
        return listUser
    }
}