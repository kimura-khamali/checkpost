package com.brendakhamali.checkposts.api

import com.brendakhamali.checkposts.model.Comments
import com.brendakhamali.checkposts.model.Post
import com.brendakhamali.checkposts.model.PostRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface PostApiInterface {


    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("/posts/{postId}")
    suspend fun fetchPostById(@Path("postId") postId: Int): Response<Post>

    @GET("/posts/{postId}/comments")
    suspend fun fetchComments(@Path("postId") postId: Int): Response<List<Comments>>


    @POST("/posts")
    suspend fun addPost(@Body postRequest: PostRequest): Response<Post>

}