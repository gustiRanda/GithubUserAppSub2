package com.gmind.githubuserapp.fragment.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmind.githubuserapp.R
import com.gmind.githubuserapp.model.Repository
import kotlinx.android.synthetic.main.list_repo.view.*

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

