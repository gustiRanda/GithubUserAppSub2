package com.gmind.githubuserapp

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmind.githubuserapp.Search.SearchUserAdapter
import com.gmind.githubuserapp.Search.SearchViewModel
import com.gmind.githubuserapp.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var searchUserAdapter : SearchUserAdapter
    private lateinit var searchViewModel : SearchViewModel

    private lateinit var userAdapter : UserAdapter
    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchUserAdapter = SearchUserAdapter()
        searchUserAdapter.notifyDataSetChanged()

        searchViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            SearchViewModel::class.java)

        userAdapter = UserAdapter()
        userAdapter.notifyDataSetChanged()

        userAdapter.setOnItemClickcallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(user: User) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USERNAME, user.login)
                startActivity(intent)
            }

        })
        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)

        rv_search.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_search.setHasFixedSize(true)
        rv_search.adapter = searchUserAdapter

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

        listUser()


        searchViewModel.getSearchUser().observe(this, Observer {
            if (it!=null){
                searchUserAdapter.setData(it)
                showLoading(false)
            }
        })

        userViewModel.getListUser().observe(this, Observer {
            if (it!=null){
                userAdapter.setData(it)
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
        searchViewModel.setSearchUser(query)
    }

    private fun listUser(){
        showLoading(true)
        userViewModel.setListUser()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            rv_progressBar.visibility = View.VISIBLE
        } else {
            rv_progressBar.visibility = View.GONE
        }
    }
}
