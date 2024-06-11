package com.bitcode.a10_04_2024_webservicesversion2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitcode.a10_04_2024_webservicesversion2.databinding.UserViewBinding
import com.bumptech.glide.Glide

class UsersAdapter(private val users : ArrayList<User>) :
RecyclerView.Adapter<UsersAdapter.UserViewHolder>(){
    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val userViewBinding : UserViewBinding
        init {
            userViewBinding = UserViewBinding.bind(view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            UserViewHolder {

            return UserViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.user_view,null))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.userViewBinding.user = users[position]
        Glide.with(holder.itemView)
            .load(users[position].avatar)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.mipmap.ic_launcher)
            .into(holder.userViewBinding.imgUser)
    }

    override fun getItemCount(): Int {
        return users.size
    }
}