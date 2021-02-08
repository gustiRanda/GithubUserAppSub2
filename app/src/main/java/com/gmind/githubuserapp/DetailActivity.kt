package com.gmind.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        onSupportNavigateUp()
        getDataDetail()

        this.setTitle("Profile")
    }

    private fun getDataDetail() {
        val user = intent.getParcelableExtra(EXTRA_DETAIL) as User
        Glide.with(this)
            .load(user.avatar)
            .apply(RequestOptions().override(350, 350))
            .into(detail_avatar)
        detail_username.text = user.username
        detail_name.text = user.name
        detail_company.text = user.company
        detail_location.text = user.location
        //detail_followers.text = user.followers
       // detail_following.text = user.following
        detail_repository.text = user.repository
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        const val EXTRA_DETAIL = "extra_detail"
    }
}
