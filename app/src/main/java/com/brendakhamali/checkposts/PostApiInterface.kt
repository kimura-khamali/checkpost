package com.brendakhamali.checkposts

import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface PostApiInterface {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>

    @GET("/posts/{postId}")
    fun fetchPostById(@Path("postId") postId:Int):Call<Post>

    @GET("/posts/{postId}/comments")
    fun fetchCommentsByPostId(@Path("postId") postId:Int):Call<List<Comments>>
}