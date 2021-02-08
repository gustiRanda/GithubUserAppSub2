package com.gmind.githubuserapp

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject

class MainViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<User>>()
    val listDataUser = ArrayList<User>()

    fun setUser(context: Context){
        //req api
        val client = AsyncHttpClient()
        val url = "https://api.github.com/users"
        client.addHeader("Authorization","token 6ccf961e95fba8a446386aca6eb9e32dd31ef485")
        client.addHeader("User-Agent","request")
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {

                val result = responseBody?.let { String(it) }
                try {
                    val jsonArray = JSONArray(result)

                    for (i in 0 until jsonArray.length()){
                        val item = jsonArray.getJSONObject(i)
                        val avatar = item.getString("avatar_url")
                        val username = item.getString("login")
                        val following = item.getInt("following_url")
                        val followers = item.getString("followers_url")
                        val user = User()
                        user.avatar = avatar
                        user.username = username
                        user.following = following.toString()
                        user.followers = followers
                        listDataUser.add(user)
                    }
                    //set data to adapter
                    listUsers.postValue(listDataUser)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun setUserSearch(query: String, context: Context){

        val client = AsyncHttpClient()
        val url = "https://api.github.com/search/users?q=$query"
        client.addHeader("Authorization","token 6ccf961e95fba8a446386aca6eb9e32dd31ef485")
        client.addHeader("User-Agent","request")
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {

                val result = responseBody?.let { String(it) }
                try {
                    val responseObject = JSONObject(result)
                    val items = responseObject.getJSONArray("items")

                    for (i in 0 until items.length()){
                        val item = items.getJSONObject(i)
                        val avatar = item.getString("avatar_url")
                        val username = item.getString("login")
                        val company = item.getString("company")
                        val name = item.getString("name")
                        val location = item.getString("location")
                        val repository = item.getString("public_repos")
                        val followers = item.getString("followers")
                        val following = item.getString("following")
                        val user = User()
                        user.avatar = avatar
                        user.username = username
                        user.company = company
                        user.name = name
                        user.location = location
                        user.repository = repository
                        user.followers = followers
                        //user.following = following
                        listDataUser.add(user)
                    }
                    //set data to adapter
                    listUsers.postValue(listDataUser)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun setUserDetail(usernameLogin: String, context: Context){

        val client = AsyncHttpClient()
        val url = "https://api.github.com/search/users?q=$usernameLogin"
        client.addHeader("Authorization","token 6ccf961e95fba8a446386aca6eb9e32dd31ef485")
        client.addHeader("User-Agent","request")
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {

                val result = responseBody?.let { String(it) }
                try {
                    val responseObject = JSONObject(result)
                    val items = responseObject.getJSONArray("items")

                    for (i in 0 until items.length()){
                        val item = items.getJSONObject(i)
                        val avatar = item.getString("avatar_url")
                        val username = item.getString("login")
                        val company = item.getString("company")
                        val name = item.getString("name")
                        val location = item.getString("location")
                        val repository = item.getString("public_repos")
                        val followers = item.getString("followers")
                        val following = item.getString("following")
                        val user = User()
                        user.avatar = avatar
                        user.username = username
                        user.company = company
                        user.name = name
                        user.location = location
                        user.repository = repository
                        user.followers = followers
                        //user.following = following
                        listDataUser.add(user)
                    }
                    //set data to adapter
                    listUsers.postValue(listDataUser)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getUser(): LiveData<ArrayList<User>>{
        return listUsers
    }
}