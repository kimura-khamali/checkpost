package com.brendakhamali.checkposts.api

import com.brendakhamali.checkposts.model.Comments
import com.brendakhamali.checkposts.model.Post
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface PostApiInterface {
    @GET("/posts")
//    fun getPosts(): Call<List<Post>>
    suspend fun getPosts(): Response<List<Post>>

    @GET("/posts/{postId}")
//    fun fetchPostById(@Path("postId") postId:Int):Call<Post>
    suspend fun fetchPostById(@Path("postId") postId:Int):Response<Post>

    @GET("/posts/{postId}/comments")
    fun fetchCommentsByPostId(@Path("postId") postId:Int):Call<List<Comments>>
}