package com.gmind.githubuserapp.fragment.repository

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmind.githubuserapp.R
import kotlinx.android.synthetic.main.activity_main.rv_progressBar
import kotlinx.android.synthetic.main.fragment_repository.*


class RepositoryFragment : Fragment() {

    private lateinit var repositoryAdapter: RepositoryAdapter
    private lateinit var repositoryViewModel: RepositoryViewModel

    companion object {
        private const val ARG_USERNAME = "arg_username"

        @JvmStatic
        fun newInstance(username: String) =
            RepositoryFragment().apply {
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
        return inflater.inflate(R.layout.fragment_repository, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val username = arguments?.getString(ARG_USERNAME)

        repositoryAdapter = RepositoryAdapter()
        repositoryAdapter.notifyDataSetChanged()

        repositoryViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            RepositoryViewModel::class.java)


        rv_repo.layoutManager = LinearLayoutManager(activity)
        rv_repo.setHasFixedSize(true)
        rv_repo.adapter = repositoryAdapter

        showLoading(true)
        repositoryViewModel.setRepositoryUser(username.toString())


        activity?.let {
            repositoryViewModel.getRepositoryUser().observe(it, Observer {
                if (it != null) {
                    repositoryAdapter.setData(it)
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