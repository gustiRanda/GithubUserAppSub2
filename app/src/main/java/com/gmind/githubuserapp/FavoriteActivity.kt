package com.gmind.githubuserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmind.githubuserapp.detail.DetailActivity
import com.gmind.githubuserapp.main.UserAdapter
import com.gmind.githubuserapp.model.User
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity() {

    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        favoriteAdapter = FavoriteAdapter()
        favoriteAdapter.notifyDataSetChanged()

        favoriteAdapter.setOnItemClickcallback(object : FavoriteAdapter.OnItemClickCallback {
            override fun onItemClicked(user: User) {
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USERNAME, user.login)
                intent.putExtra(DetailActivity.EXTRA_ID, user.id)
                intent.putExtra(DetailActivity.EXTRA_AVATAR_URL, user.avatar_url)
                startActivity(intent)
            }

        })

        rv_favorite.layoutManager = LinearLayoutManager(this)
        rv_favorite.setHasFixedSize(true)
        rv_favorite.adapter = favoriteAdapter

        loadFavAsync()


        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.favorite_user)
        }
    }

    private fun loadFavAsync() {
        GlobalScope.launch(Dispatchers.Main){
//            rv_progressBar.visibility = View.VISIBLE
            val favoriteHelper = FavoriteHelper.getInstance(applicationContext)
            favoriteHelper.open()
            val deferredFav = async(Dispatchers.IO){
                val cursor = favoriteHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }
//            rv_progressBar.visibility = View.INVISIBLE
            val favorite = deferredFav.await()
            if (favorite.size > 0) {
                favoriteAdapter.listFavorite = favorite
            } else {
                favoriteAdapter.listFavorite = ArrayList()
            }
            favoriteHelper.close()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}