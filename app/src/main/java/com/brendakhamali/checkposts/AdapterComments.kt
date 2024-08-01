package com.brendakhamali.checkposts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brendakhamali.checkposts.databinding.ActivityCommentsBinding

//class AdapterComments {
//}


class AdapterComments(var comment:List<Comments>):RecyclerView.Adapter<CommentViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding=ActivityCommentsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return comment.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val list=comment[position]
        holder.binding.tvPostBody.text=list.body
        holder.binding.tvPostName.text=list.name
    }
}

class CommentViewHolder(var binding: ActivityCommentsBinding):RecyclerView.ViewHolder(binding.root){

}