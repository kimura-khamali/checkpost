package com.brendakhamali.checkposts.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brendakhamali.checkposts.databinding.PostListItemBinding
import com.brendakhamali.checkposts.model.Post


class AdapterPost(val body: List<Post>, val context:Context):RecyclerView.Adapter<PostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding=PostListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return body.size
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val body=body[position]
        holder.binding.tittle.text=body.title
        holder.binding.id.text=body.id.toString()
        holder.binding.user.text=body.userId.toString()
        holder.binding.body.text=body.body
        holder.binding.clPost.setOnClickListener{
            val intent = Intent(context, CommentsActivity::class.java)
            intent.putExtra("POST_ID", body
                .id)
            context.startActivity(intent)
        }
    }
}

class PostViewHolder(var binding: PostListItemBinding):RecyclerView.ViewHolder(binding.root){

}