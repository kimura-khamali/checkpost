package com.brendakhamali.checkposts.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.brendakhamali.checkposts.R
import com.brendakhamali.checkposts.databinding.AdPostListBinding
import com.brendakhamali.checkposts.model.PostRequest
import com.brendakhamali.checkposts.viewmodel.PostViewModel

class AdPostActivity : AppCompatActivity() {
    lateinit var binding: AdPostListBinding
    val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=AdPostListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.left_arrow_svg)
    }

    override fun onResume() {
        super.onResume()
        binding.btnPost.setOnClickListener{
            valdatePost()
        }
        postViewModel.addPostLiveData.observe(this){
                message->
            binding.progressBar.visibility= View.GONE
//            Toast.makeText(this,message,Toast.LENGTH_LONG).show()
            Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show()

            clearForm()
        }

        postViewModel.errorLiveDate.observe(this){
                error ->
            binding.progressBar.visibility=View.GONE
            Toast.makeText(this,error,Toast.LENGTH_LONG).show()
        }
    }

    fun valdatePost(){
        val userId=binding.etUserId.text.toString()
        val title=binding.etTitle.text.toString()
        val body=binding.etBody.text.toString()
        var error = false
        if(userId.isBlank()){
            binding.etUserId.error="User Id is required"
            error=true
        }

        if(title.isBlank()){
            binding.etTitle.error="Title is required"
            error=true
        }

        if(body.isBlank()){
            binding.etBody.error="Body is required"
            error=true
        }

        if(!error){
            binding.progressBar.visibility=View.GONE
            val postRequest=PostRequest(userId=userId.toInt(),title=title, body=body)
            postViewModel.addPost(postRequest)
        }

    }

    fun clearForm(){
        binding.etUserId.text.clear()
        binding.etTitle.text.clear()
        binding.etBody.text.clear()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}

