package com.deepaliverma.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deepaliverma.mvvm.R
import com.deepaliverma.mvvm.data.models.Users
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*

class UsersAdapter(val data:List<Users>): RecyclerView.Adapter<UserVeiwHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=UserVeiwHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
    )

    override fun onBindViewHolder(holder: UserVeiwHolder, position: Int) {
holder.bind(data[position])


    }

    override fun getItemCount()=data.size

}

class UserVeiwHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
fun bind(item:Users)=with(itemView){
   usernameTv.text=item.login
    Picasso.get().load(item.avatarUrl).into(userImgView)

}
}
