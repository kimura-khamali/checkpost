package com.brendakhamali.checkposts.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brendakhamali.checkposts.model.Comments
import com.brendakhamali.checkposts.databinding.CommentListItemBinding


class AdapterComments(var comments: List<Comments>) : RecyclerView.Adapter<CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding =CommentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        holder.binding.tvCommentBody.text = comment.body
        holder.binding.tvCommentName.text = comment.name
        holder.binding.tvCommentEmail.text = comment.email
    }
}

class CommentViewHolder(var binding: CommentListItemBinding) : RecyclerView.ViewHolder(binding.root) {

}
