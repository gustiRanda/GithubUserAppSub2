package com.gmind.githubuserapp.fragment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmind.githubuserapp.api.Retrofit
import com.gmind.githubuserapp.model.Repository
import com.gmind.githubuserapp.model.User
import com.gmind.githubuserapp.model.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryViewModel : ViewModel(){
    val listRepository = MutableLiveData<ArrayList<Repository>>()

    fun setRepositoryUser(username : String){
        Retrofit.apiInstance
            .getRepository(username)
            .enqueue(object : Callback<ArrayList<Repository>>{
                override fun onResponse(
                    call: Call<ArrayList<Repository>>,
                    response: Response<ArrayList<Repository>>
                ) {
                    if (response.isSuccessful){
                        listRepository.postValue(response.body())
                        Log.d("Get Repo Success", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<ArrayList<Repository>>, t: Throwable) {
                    Log.d("Get Repo Failure", t.message)
                }
            })
    }

    fun getRepositoryUser() : LiveData<ArrayList<Repository>>{
        return listRepository
    }
}