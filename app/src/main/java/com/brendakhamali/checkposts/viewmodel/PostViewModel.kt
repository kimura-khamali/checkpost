package com.brendakhamali.checkposts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brendakhamali.checkposts.model.Comments
import com.brendakhamali.checkposts.model.Post
import com.brendakhamali.checkposts.model.PostRequest
import com.brendakhamali.checkposts.repository.Postrepository
import kotlinx.coroutines.launch


class PostViewModel : ViewModel() {
    val postsRepo = Postrepository()
    val postliveDate = MutableLiveData<List<Post>>()
    val errorLiveDate = MutableLiveData<String>()
    val postLiveData = MutableLiveData<String>()
    val commentsLiveData = MutableLiveData<List<Comments>>()
    val addPostLiveData = MutableLiveData<Post>()

    fun fetchPosts() {
        viewModelScope.launch {
            val response = postsRepo.fetchposts()
            if (response.isSuccessful) {
                postliveDate.postValue(response.body())
            } else {
                errorLiveDate.postValue(response.errorBody()?.string())
            }
        }
    }

    fun fetchComments(postId: Int) {
        viewModelScope.launch {
            val response = postsRepo.fetchComments(postId)
            if (response.isSuccessful) {
                commentsLiveData.postValue(response.body())
            } else {
                errorLiveDate.postValue(response.errorBody()?.string())
            }
        }
    }

    fun addPost(postRequest: PostRequest) {
        viewModelScope.launch {
            val response = postsRepo.addPost(postRequest)
            if (response.isSuccessful) {
                addPostLiveData.postValue(response.body())  // Correct this line
            } else {
                errorLiveDate.postValue(response.errorBody()?.string())
            }
        }
    }
}


//coutine set of function that will always be done  -cooperate withone another