package com.brendakhamali.checkposts.model

data class Comments(
    val postId:Int,
    val id:Int,
    val name:String,
    val email:String,
    val body: String
)
