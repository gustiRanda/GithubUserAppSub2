package com.gmind.githubuserapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmind.githubuserapp.main.FollowAdapter
import kotlinx.android.synthetic.main.activity_main.*


class FollowersFragment : Fragment() {

    private lateinit var followAdapter: FollowAdapter
    private lateinit var followersViewModel: FollowersViewModel

    companion object {
        private const val ARG_USERNAME = "arg_username"

        @JvmStatic
        fun newInstance(username: String) =
            FollowersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USERNAME, username)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString(FollowersFragment.ARG_USERNAME)

        followAdapter = FollowAdapter()
        followAdapter.notifyDataSetChanged()

        followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowersViewModel::class.java)


        rv_user.layoutManager = LinearLayoutManager(activity)
        rv_user.setHasFixedSize(true)
        rv_user.adapter = followAdapter

        showLoading(true)
        followersViewModel.setFollowersUser(username.toString())


        activity?.let {
            followersViewModel.getFollowersUser().observe(it, Observer {
                if (it != null) {
                    followAdapter.setData(it)
                    showLoading(false)
                }
            })
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            rv_progressBar.visibility = View.VISIBLE
        } else {
            rv_progressBar.visibility = View.GONE
        }
    }
}