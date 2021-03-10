package com.gmind.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)

        detailViewModel.setDetailUser(username)
        detailViewModel.getDetailUser().observe(this, Observer {
            if (it!=null){
                tv_detail_name.text = it.name
                tv_detail_username.text = it.login
                tv_repositori.text = "Repository\n${it.public_repos}"
                tv_following.text = "Following\n${it.following}"
                tv_followers.text = "Followers\n${it.followers}"
                tv_comp.text = it.company
                tv_loc.text = it.location
                Glide.with(this)
                    .load(it.avatar_url)
                    .into(iv_detail_avatar)
            }
        })

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            title = "Detail"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}