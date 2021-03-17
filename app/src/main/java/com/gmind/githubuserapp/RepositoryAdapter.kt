package com.gmind.githubuserapp.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmind.githubuserapp.R
import com.gmind.githubuserapp.model.Repository
import com.gmind.githubuserapp.model.User
import kotlinx.android.synthetic.main.list_repo.view.*
import kotlinx.android.synthetic.main.list_user.view.*
import kotlinx.android.synthetic.main.list_user.view.iv_avatar

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.ListViewHolder>() {
    private val mData = ArrayList<Repository>()


    fun setData(items: ArrayList<Repository>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_repo, parent, false)
        return ListViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mData[position])
        // val variable = mData[position]
        /***holder.itemView.setOnClickListener {
        val intentData = User(
        variable.username,
        variable.name,
        variable.company,
        variable.avatar,
        variable.location,
        variable.repository,
        variable.followers,
        variable.following
        )
        val intent = Intent(it.context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DETAIL, intentData)
        it.context.startActivity(intent)
        }
         ***/
    }

    override fun getItemCount(): Int = mData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repo: Repository) {

            with(itemView){
                tv_name.text = repo.name
                tv_desc.text = repo.description
                tv_star.text = repo.stargazers_count.toString()
                tv_language.text = repo.language
            }
        }
    }
}

