package com.brendakhamali.checkposts.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.brendakhamali.checkposts.databinding.ActivityCommentsBinding
import com.brendakhamali.checkposts.model.Post
import com.brendakhamali.checkposts.viewmodel.PostViewModel


class CommentsActivity : AppCompatActivity() {
    private var postId = 0
    private lateinit var binding: ActivityCommentsBinding
    val postViewModel: PostViewModel by viewModels()
    private lateinit var adapterComments: AdapterComments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extra = intent.extras
        if (extra != null){
            postId = extra.getInt("POST_ID")

        }

    }

    override fun onResume(){
        super.onResume()
        postViewModel.postLiveData.observe(this){posts->
            binding.tvPostName.text = postId.toString()
            binding.tvPostBody.text = postId.toString()
        }

        postViewModel.errorLiveDate.observe(this){error->
            Toast.makeText(this,error,Toast.LENGTH_LONG).show()
        }
    }



    private fun setupRecyclerView(){
        adapterComments = AdapterComments(emptyList())
        binding.rvComments.layoutManager = LinearLayoutManager(this)
        binding.rvComments.adapter = adapterComments
    }

    private fun showErrorToast(error: String) {
        Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
    }
}














































//class CommentsActivity : AppCompatActivity() {
//    private var postId: Int = 0
//    private val postViewModel: PostViewModel by viewModels()
//    private lateinit var binding: ActivityCommentsBinding
//    private lateinit var adapterComments: AdapterComments
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        postViewModel.fetchPostById()
//        binding = ActivityCommentsBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Retrieve postId from intent extras
//        postId = intent.getIntExtra("POST_ID", 0)
//        if (postId != 0) {
//            postViewModel.fe(postId)
//        }
//
//        setupRecyclerView()
////        observeErrorData()
//    }
//        override fun onResume() {
//        super.onResume()
//        postViewModel.postLiveData.observe(this) { post ->
//            binding.tvPostTitle.text = post.
//            binding.tvPostBody.text = post?.b
//        }
//        postViewModel.errorLiveDate.observe(this) { error ->
//            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
//
//        }
//    }
//    private fun setupRecyclerView() {
//        adapterComments = AdapterComments(emptyList())
//        binding.rvComments.layoutManager = LinearLayoutManager(this)
//        binding.rvComments.adapter = adapterComments
//    }

//    private fun observePostData() {
//        postViewModel.postLiveData.observe(this, Observer { post ->
//            updatePostUI(postid)
//        })
//    }

//    private fun observeErrorData() {
//        postViewModel.errorLiveDate.observe(this, Observer { error ->
//            showErrorToast(error)
//        })
//    }
//
//    private fun updatePostUI(post: Post?) {
//        binding.tvPostTitle.text = post?.title
//        binding.tvPostBody.text = post?.body
//    }

//    private fun showErrorToast(error: String) {
//        Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
//    }
//}




//package com.brendakhamali.checkposts.ui
//
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.ViewModel
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.brendakhamali.checkposts.model.Comments
//import com.brendakhamali.checkposts.model.Post
//import com.brendakhamali.checkposts.api.Apiclient
//import com.brendakhamali.checkposts.api.PostApiInterface
//import com.brendakhamali.checkposts.databinding.ActivityCommentsBinding
//import com.brendakhamali.checkposts.viewmodel.PostViewModel
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class CommentsActivity : AppCompatActivity() {
//    private var postId = 0
//    val postVieModel: PostViewModel by viewModels()
//
//    //    private val TAG = "COMMENTSACTIVITY"
////private val TAG = "TAG"
//    private lateinit var binding: ActivityCommentsBinding
//    private lateinit var adapterComments: AdapterComments
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        Log.d(TAG, "COMMENTSACTIVITY ONCREATE")
//        binding = ActivityCommentsBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Retrieve postId from intent extras
//        if (intent.extras?.let) {
//            postId = intent.getIntExtra("POST_ID", 0)
//            if (postId != 0) {
////                postVieModel.fetchPostByid
//                postVieModel.fetchPosts()
//            }
////            fetchPost(postId)
////            fetchCommentsByPostID(postId)
//        }
//
//
////        setupRecyclerView()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        postVieModel.postliveDate.observe(this) { post ->
//            binding.tvPostTitle.text = post
//            binding.tvPostBody.text = post?.body
//        }
//        postsViewmodel.errorLiveDate.observe(this) { error ->
//            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
//
//        }
//    }
//
////    private fun setupRecyclerView() {
////        adapterComments = AdapterComments(emptyList())
////        binding.rvComments.layoutManager = LinearLayoutManager(this)
////        binding.rvComments.adapter = adapterComments
////    }
//
//    private fun setupRecyclerView() {
//        adapterComments = AdapterComments(emptyList())
//        binding.rvComments.layoutManager = LinearLayoutManager(this)
//        binding.rvComments.adapter = adapterComments
//    }
//}
//    private suspend fun fetchPost(postId: Int) {
//        val apiClient = Apiclient.buildApiClient(PostApiInterface::class.java)
//        val request = apiClient.fetchPostById(postId)
//
//        request.enqueue(object : Callback<Post> {
//            override fun onResponse(call: Call<Post>, response: Response<Post>) {
//                if (response.isSuccessful) {
//                    val post = response.body()
//                    binding.tvPostTitle.text = post?.title
//                    binding.tvPostBody.text = post?.body
//                } else {
//                    Toast.makeText(
//                        this@CommentsActivity,
//                        response.errorBody()?.string(),
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//
//            override fun onFailure(call: Call<Post>, t: Throwable) {
//                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//
//    private fun fetchCommentsByPostID(postId: Int) {
//        val apiClient = Apiclient.buildApiClient(PostApiInterface::class.java)
//        val request = apiClient.fetchCommentsByPostId(postId)
//
//        request.enqueue(object : Callback<List<Comments>> {
//            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
//                if (response.isSuccessful) {
//                    val comments = response.body() ?: emptyList()
//                    if (comments.isNotEmpty()) {
//                        adapterComments.comments = comments
//                        adapterComments.notifyDataSetChanged()
//                    } else {
//                        Toast.makeText(this@CommentsActivity, "No comments found", Toast.LENGTH_LONG).show()
//                    }
//                } else {
//                    Toast.makeText(this@CommentsActivity, "Error: ${response.message()}", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
//                Toast.makeText(this@CommentsActivity, "Failure: ${t.message}", Toast.LENGTH_LONG).show()
//            }
//        })


//    override fun onStart() {
//        super.onStart()
//        Log.d(TAG, "COMMENTSACTIVITY ONSTART")
//        // You can refresh data or UI elements here if needed
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d(TAG, "COMMENTSACTIVITY ONRESUME")
//        // Resume any paused tasks or refresh UI here
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d(TAG, "COMMENTSACTIVITY ONPAUSE")
//        // Save data or pause ongoing tasks here
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d(TAG, "COMMENTSACTIVITY ONSTOP")
//        // Release resources that are not needed while the activity is not visible
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG, "COMMENTSACTIVITY ONDESTROY")
//        // Clean up resources, if needed
//    }





































//package com.brendakhamali.checkposts
//
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.brendakhamali.checkposts.databinding.ActivityCommentsBinding
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class CommentsActivity : AppCompatActivity() {
//    private var postId = 0
//    val TAG = "COMMENTSACTIVITY"
//    private lateinit var binding: ActivityCommentsBinding
//    private lateinit var adapterComments: AdapterComments
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        Log.d(TAG, "COMMENTSACTIVITY ONCREATE")
//        binding = ActivityCommentsBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        val extra = intent.extras
//        if (extra != null) {
//            postId = extra.getInt("POST_ID")
//            fetchPost(postId)
//            fetchCommentsByPostID(postId)
//        }
//        setupRecyclerView()
//    }
//
//    private fun setupRecyclerView() {
//        adapterComments = AdapterComments(emptyList())
//        binding.rvComments.layoutManager = LinearLayoutManager(this)
//        binding.rvComments.adapter = adapterComments
//    }
//
//
//
//    fun fetchPost(postId: Int) {
//        val apiClient = Apiclient.buildApiClient(PostApiInterface::class.java)
//        val request = apiClient.fetchPostById(postId)
//
//        request.enqueue(object : Callback<Post> {
//            override fun onResponse(call: Call<Post>, response: Response<Post>) {
//                if (response.isSuccessful) {
//                    val post = response.body()
//                    binding.tvPostTitle.text = post?.title
//                    binding.tvPostBody.text = post?.body
//                } else {
//                    Toast.makeText(
//                        this@CommentsActivity,
//                        response.errorBody()?.string(),
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//
//            override fun onFailure(call: Call<Post>, t: Throwable) {
//                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//
//    private fun fetchCommentsByPostID(postId: Int) {
//        val apiClient = Apiclient.buildApiClient(PostApiInterface::class.java)
//        val request = apiClient.fetchCommentsByPostId(postId)
//        request.enqueue(object : Callback<List<Comments>> {
//            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
//                if (response.isSuccessful) {
//                    val comments = response.body() ?: emptyList()
//                    if (comments.isNotEmpty()) {
//                        adapterComments.comments = comments
//                        adapterComments.notifyDataSetChanged()
//                    } else {
//                        Toast.makeText(this@CommentsActivity, "No comments found", Toast.LENGTH_LONG)
//                            .show()
//                    }
//                } else {
//                    Toast.makeText(this@CommentsActivity, "Error:${response.message()}", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
//                Toast.makeText(this@CommentsActivity, "Failure: ${t.message}", Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//}
//
