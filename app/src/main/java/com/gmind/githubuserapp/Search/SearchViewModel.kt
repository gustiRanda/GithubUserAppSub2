package com.gmind.githubuserapp.Search

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

class SearchViewModel : ViewModel(){
    val listUser = MutableLiveData<ArrayList<User>>()

    fun setSearchUser(query: String){
        Retrofit.apiInstance
            .getSearchUser(query)
            .enqueue(object : Callback<SearchResponse>{
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if (response.isSuccessful){
                        listUser.postValue(response.body()?.items)
                        Log.d("Success", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Log.d("Failure", t.message)
                }
            })
    }

    fun getSearchUser() : LiveData<ArrayList<User>>{
        return listUser
    }
}