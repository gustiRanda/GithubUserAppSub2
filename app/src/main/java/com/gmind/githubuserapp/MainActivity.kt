package com.gmind.githubuserapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ListUserAdapter
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ListUserAdapter()
        adapter.notifyDataSetChanged()

        recycler_view_user.layoutManager = LinearLayoutManager(this)
        recycler_view_user.adapter = adapter
        recycler_view_user.setHasFixedSize(true)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)


        mainViewModel.getUser().observe(this, Observer { listDataUser ->
            if (listDataUser !=null){
                adapter.setData(listDataUser)
                showLoading(false)
            }
        })

        getDataUser()
    }

    private fun getDataUser(){
        mainViewModel.setUser(applicationContext)
        showLoading(true)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            rv_progressBar.visibility = View.VISIBLE
        } else {
            rv_progressBar.visibility = View.GONE
        }
    }
}
