package com.brendakhamali.checkposts.repository

import com.brendakhamali.checkposts.api.Apiclient
import com.brendakhamali.checkposts.api.PostApiInterface
import com.brendakhamali.checkposts.model.Comments
import com.brendakhamali.checkposts.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class Postrepository {
    val apiClient = Apiclient.buildApiClient(PostApiInterface::class.java)

    suspend fun fetchposts(): Response<List<Post>>{
        return withContext(Dispatchers.IO){
            apiClient.getPosts()
        }
    }
    suspend fun fetchPostById(postId: Int): Response<Post>{
        return withContext(Dispatchers.IO){
            apiClient.fetchPostById(postId)

        }
        }
    suspend fun fetchComments(postId: Int): Call<List<Comments>> {
        return withContext(Dispatchers.IO) {
            apiClient.fetchCommentsByPostId(postId)
        }
    }
}



//etchPostById(