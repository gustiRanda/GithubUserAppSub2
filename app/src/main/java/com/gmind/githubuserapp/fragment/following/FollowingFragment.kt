package com.gmind.githubuserapp.fragment.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmind.githubuserapp.R
import com.gmind.githubuserapp.fragment.FollowAdapter
import kotlinx.android.synthetic.main.activity_main.*


class FollowingFragment : Fragment() {

    private lateinit var followAdapter: FollowAdapter
    private lateinit var followingViewModel: FollowingViewModel

    companion object {
        private const val ARG_USERNAME = "arg_username"

        @JvmStatic
        fun newInstance(username: String) =
            FollowingFragment().apply {
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
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val username = arguments?.getString(ARG_USERNAME)

        followAdapter = FollowAdapter()
        followAdapter.notifyDataSetChanged()

        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowingViewModel::class.java)


        rv_user.layoutManager = LinearLayoutManager(activity)
        rv_user.setHasFixedSize(true)
        rv_user.adapter = followAdapter

        showLoading(true)
        followingViewModel.setFollowingUser(username.toString())


        activity?.let {
            followingViewModel.getFollowingUser().observe(it, Observer {
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