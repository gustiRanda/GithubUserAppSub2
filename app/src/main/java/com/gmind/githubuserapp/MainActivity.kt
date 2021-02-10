package com.gmind.githubuserapp

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        rv_user.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_user.setHasFixedSize(true)
        rv_user.adapter = adapter

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


        mainViewModel.getSearchUser().observe(this, Observer {
            if (it!=null){
                adapter.setData(it)
                showLoading(false)
            }
        })

        //getDataUser()
    }

  /*  override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.nav_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.nav_search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                mainViewModel.setSearchUser(query)
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mainViewModel.setSearchUser(newText)
                return false
            }
        })
        return true
    }
*/
  /*  private fun getDataUser(){
        mainViewModel.setUser(applicationContext)
        showLoading(true)
    }

  */

    private fun searchUser(){
        val query = et_query.text.toString()
        if (query.isEmpty()) return
        showLoading(true)
        mainViewModel.setSearchUser(query)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            rv_progressBar.visibility = View.VISIBLE
        } else {
            rv_progressBar.visibility = View.GONE
        }
    }
}
