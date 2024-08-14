package com.brendakhamali.checkposts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brendakhamali.checkposts.model.Post
import com.brendakhamali.checkposts.repository.Postrepository
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    val postsRepo = Postrepository()
    val postliveDate = MutableLiveData<List<Post>>()
    val errorLiveDate = MutableLiveData<String>()
    val postLiveData = MutableLiveData<String>()
    val commentsLiveData = MutableLiveData<String>()

    fun fetchPosts() {
        viewModelScope.launch {
            val response = postsRepo.fetchposts()
            if (response.isSuccessful) {
                postliveDate.postValue(response.body())
            } else {
                errorLiveDate.postValue(response.errorBody()?.string()

                )
            }
        }

        suspend fun fetchPostById(postid: Int){
            val response = postsRepo.fetchPostById(postid)
            if (response.isSuccessful) {
                postLiveData.postValue(response.body().toString())
            } else {
                errorLiveDate.postValue(response.errorBody()?.string()

                )}
        }
    }
    suspend fun fetchCommentsById(postid: Int){
        val response = postsRepo.fetchPostById(postid)
        if (response.isSuccessful) {
            commentsLiveData.postValue(response.body().toString())
        } else {
            errorLiveDate.postValue(response.errorBody()?.string()

            )}
    }
}





//coutine set of function that will always be done  -cooperate withone another