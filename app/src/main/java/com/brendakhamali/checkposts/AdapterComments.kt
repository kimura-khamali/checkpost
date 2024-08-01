package com.brendakhamali.checkposts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brendakhamali.checkposts.databinding.CommentListItemBinding

//import com.brendakhamali.checkposts.databinding.CommentListItemBinding

//class AdapterComments {
//}

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


//package com.brendakhamali.checkposts
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.brendakhamali.checkposts.databinding.ActivityCommentsBinding
//
////class AdapterComments {
////}
//
//
//class AdapterComments(var comment:List<Comments>):RecyclerView.Adapter<CommentViewHolder> (){
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
//        val binding=ActivityCommentsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        return CommentViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int {
//        return comment.size
//    }
//
//    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
//        val list=comment[position]
//        holder.binding.tvPostBody.text=list.body
//        holder.binding.tvPostName.text=list.name
//    }
//}
//
//class CommentViewHolder(var binding: ActivityCommentsBinding):RecyclerView.ViewHolder(binding.root){
//
//}