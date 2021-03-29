package com.gmind.githubuserapp.detail

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.gmind.githubuserapp.FavoriteHelper
import com.gmind.githubuserapp.R
import com.gmind.githubuserapp.SectionsPagerAdapter
import com.gmind.githubuserapp.model.User
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"


        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
        )
    }

    private lateinit var detailViewModel: DetailViewModel

    private lateinit var favoriteHelper: FavoriteHelper

    private var favorite : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        val id = intent.getIntExtra(EXTRA_ID, 0)

        val values = ContentValues()

        var statusFavorite = false

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailViewModel::class.java)

        detailViewModel.setDetailUser(username)
        detailViewModel.getDetailUser().observe(this, Observer {
            if (it!=null){
                tv_detail_name.text = it.name
                tv_detail_username.text = it.login
                tv_repositori.text = it.public_repos.toString()
                tv_following.text = it.following.toString()
                tv_followers.text = it.followers.toString()
                tv_comp.text = it.company
                tv_loc.text = it.location
                Glide.with(this)
                    .load(it.avatar_url)
                    .into(iv_detail_avatar)
            }
        })

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = username
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            title = "Detail"
        }

        favoriteHelper = FavoriteHelper.getInstance(applicationContext)
        favoriteHelper.open()

        favorite = intent.getParcelableExtra(EXTRA_ID)
//        favorite = intent.getParcelableExtra(EXTRA_USERNAME)

        values.put(EXTRA_ID, id)
//        values.put(EXTRA_USERNAME, username)


        setStatusFavorite(statusFavorite)
        btn_favorite.setOnClickListener {
            statusFavorite = !statusFavorite
            favoriteHelper.insert(values)
            setStatusFavorite(statusFavorite)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            btn_favorite.setBackgroundResource(R.drawable.ic_favorite_true)
            Toast.makeText(this, "Ditambahkan ke favorit", Toast.LENGTH_SHORT).show()
        } else {
            btn_favorite.setBackgroundResource(R.drawable.ic_favorite_false)
            Toast.makeText(this, "Dihapus dari favorit", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}