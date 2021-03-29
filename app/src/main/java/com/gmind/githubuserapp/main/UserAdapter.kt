package com.gmind.githubuserapp.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmind.githubuserapp.R
import com.gmind.githubuserapp.model.User
import kotlinx.android.synthetic.main.list_user.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {
    private val mData = ArrayList<User>()

    private lateinit var onItemClickCallback : OnItemClickCallback

    fun setOnItemClickcallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: ArrayList<User>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_user, parent, false)
        return ListViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {

            with(itemView){
                tv_username.text = user.login
                Glide.with(itemView.context)
                    .load(user.avatar_url)
                    .into(iv_avatar)
                this.setOnClickListener {
                    onItemClickCallback.onItemClicked(user)
                }
            }
        }
    }


    interface OnItemClickCallback{
        fun onItemClicked(user: User)
    }
}

