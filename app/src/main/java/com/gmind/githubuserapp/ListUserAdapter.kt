package com.gmind.githubuserapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.list_user.view.*

class ListUserAdapter : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    private val mData = ArrayList<User>()

    fun setData(items: ArrayList<User>){
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserAdapter.ListViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_user, parent, false)
        return ListViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ListUserAdapter.ListViewHolder, position: Int) {
        holder.bind(mData[position])
        val variable = mData[position]
        holder.itemView.setOnClickListener {
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

    }

    override fun getItemCount(): Int = mData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            with(itemView){
                username.text = user.username
                user_following.text = itemView.context.getString(R.string.following_ppl, user.following)
                user_followers.text = itemView.context.getString(R.string.followers_ppl, user.followers)
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .apply(RequestOptions().override(100, 100))
                    .into(img_user_photo)
            }
        }
    }


    //interface OnItemClickCallback{
       // fun onItemClicked(user: User)
    //}
}

