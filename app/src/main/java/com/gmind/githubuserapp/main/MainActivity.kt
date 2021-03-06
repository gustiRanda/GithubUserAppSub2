package com.gmind.githubuserapp.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmind.githubuserapp.FavoriteActivity
import com.gmind.githubuserapp.detail.DetailActivity
import com.gmind.githubuserapp.R
import com.gmind.githubuserapp.SettingsActivity
import com.gmind.githubuserapp.model.User
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.rv_progressBar

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter : UserAdapter
    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userAdapter = UserAdapter()
        userAdapter.notifyDataSetChanged()

        userAdapter.setOnItemClickcallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(user: User) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USERNAME, user.login)
                intent.putExtra(DetailActivity.EXTRA_ID, user.id)
                intent.putExtra(DetailActivity.EXTRA_AVATAR_URL, user.avatar_url)
                startActivity(intent)
            }

        })
        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            UserViewModel::class.java)


        rv_user.layoutManager = LinearLayoutManager(this)
        rv_user.setHasFixedSize(true)
        rv_user.adapter = userAdapter

        btn_search.setOnClickListener{
            searchUser()
        }

        et_query.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                searchUser()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        //listUser()
        showLoading(true)
        userViewModel.setListUser()

    /*    if (et_query != null){
            userViewModel.getSearchUser().observe(this, Observer {
                if (it!=null){
                    userAdapter.setData(it)
                    showLoading(false)
                }
            })
        } else userViewModel.getListUser().observe(this, Observer {
            if (it!=null){
                userAdapter.setData(it)
                showLoading(false)
            }
        })
        
     */

        userViewModel.getSearchUser().observe(this, Observer {
            if (it!=null){
                userAdapter.setData(it)
                showLoading(false)
            }
        })

        userViewModel.getListUser().observe(this, Observer {
            if (it!=null){
                userAdapter.setData(it)
                showLoading(false)
            }
        })

        btn_favorite.setOnClickListener {
            val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
////        if (item.itemId == R.id.change_language) {
////            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
////            startActivity(intent)
//
//        if (item.itemId == R.id.settings) {
//            val intent = Intent(this@MainActivity, SettingsActivity::class.java)
//            startActivity(intent)
//        }
//        return super.onOptionsItemSelected(item)
//    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_language -> {
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
                return true
            }
            R.id.settings -> {
                val intent = Intent(this@MainActivity, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return true
        }
    }


    private fun searchUser(){
        val query = et_query.text.toString()
        if (query.isEmpty()) return
        showLoading(true)
        userViewModel.setSearchUser(query)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            rv_progressBar.visibility = View.VISIBLE
        } else {
            rv_progressBar.visibility = View.GONE
        }
    }
}
