package com.gmind.githubuserapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmind.githubuserapp.main.UserAdapter
import com.gmind.githubuserapp.model.User
import kotlinx.android.synthetic.main.list_user.view.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    var listFavorite = ArrayList<User>()
        set(listFavorite){
            if (listFavorite.size > 0){
                this.listFavorite.clear()
            }

            this.listFavorite.addAll(listFavorite)

            notifyDataSetChanged()
        }

//    fun addItem(user: User){
//        this.listFavorite.add(user)
//        notifyItemInserted(this.listFavorite.size - 1)
//    }
//
//
//    fun removeItem(position: Int){
//        this.listFavorite.removeAt(position)
//        notifyItemRemoved(position)
//        notifyItemRangeChanged(position, this.listFavorite.size)
//    }


    private lateinit var onItemClickCallback : FavoriteAdapter.OnItemClickCallback

    fun setOnItemClickcallback(onItemClickCallback: FavoriteAdapter.OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapter.FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_user, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteViewHolder, position: Int) {
        holder.bind(listFavorite[position])
    }

    override fun getItemCount(): Int = this.listFavorite.size

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
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