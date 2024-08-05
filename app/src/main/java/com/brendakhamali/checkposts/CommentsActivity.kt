package com.brendakhamali.checkposts

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.brendakhamali.checkposts.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    private var postId = 0
    private lateinit var binding: ActivityCommentsBinding
    private lateinit var adapterComments: AdapterComments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extra = intent.extras
        if (extra != null) {
            postId = extra.getInt("POST_ID")
            fetchPost(postId)
            fetchCommentsByPostID(postId)
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapterComments = AdapterComments(emptyList())
        binding.rvComments.layoutManager = LinearLayoutManager(this)
        binding.rvComments.adapter = adapterComments
    }

    fun fetchPost(postId: Int) {
        val apiClient = Apiclient.buildApiClient(PostApiInterface::class.java)
        val request = apiClient.fetchPostById(postId)

        request.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    binding.tvPostTitle.text = post?.title
                    binding.tvPostBody.text = post?.body
                } else {
                    Toast.makeText(
                        this@CommentsActivity,
                        response.errorBody()?.string(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun fetchCommentsByPostID(postId: Int) {
        val apiClient = Apiclient.buildApiClient(PostApiInterface::class.java)
        val request = apiClient.fetchCommentsByPostId(postId)
        request.enqueue(object : Callback<List<Comments>> {
            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                if (response.isSuccessful) {
                    val comments = response.body() ?: emptyList()
                    if (comments.isNotEmpty()) {
                        adapterComments.comments = comments
                        adapterComments.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@CommentsActivity, "No comments found", Toast.LENGTH_LONG)
                            .show()
                    }
                } else {
                    Toast.makeText(this@CommentsActivity, "Error:${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                Toast.makeText(this@CommentsActivity, "Failure: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}

