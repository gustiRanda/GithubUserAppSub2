package com.gmind.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    var username: String? = null

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = RepositoryFragment.newInstance(username.toString())
            1 -> fragment = FollowingFragment.newInstance(username.toString())
            2 -> fragment = FollowersFragment.newInstance(username.toString())
        }
        return fragment as Fragment
    }
}