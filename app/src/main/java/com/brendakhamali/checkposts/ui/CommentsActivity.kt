package com.brendakhamali.checkposts.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.brendakhamali.checkposts.databinding.ActivityCommentsBinding
import com.brendakhamali.checkposts.model.Comments
import com.brendakhamali.checkposts.viewmodel.PostViewModel



class CommentsActivity : AppCompatActivity() {
    private var postId = 0
    private lateinit var binding: ActivityCommentsBinding
    val postViewModel: PostViewModel by viewModels()
    lateinit var comments: AdapterComments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extra = intent.extras
        if (extra != null){
            postId = extra.getInt("POST_ID")
            postViewModel.fetchPosts()
            postViewModel.fetchComments(postId)
        }

        binding.rvComments.layoutManager=LinearLayoutManager(this)

    }


    override fun onResume(){
        super.onResume()
        postViewModel.postLiveData.observe(this){post->
            binding.tvPostName.text = post?.toString()
            binding.tvPostBody.text = post?.toString()

        }

        postViewModel.errorLiveDate.observe(this) { error ->
            Toast.makeText(this@CommentsActivity, error, Toast.LENGTH_LONG).show()
        }

        postViewModel.commentsLiveData.observe(this) { comments ->
            displayComments(comments)
        }
        binding.leftCheck.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }

    fun displayComments(comments: List<Comments>){
        binding.rvComments.adapter=AdapterComments(comments)
    }

}
