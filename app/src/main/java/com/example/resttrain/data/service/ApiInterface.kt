package com.example.resttrain.data.service

import com.example.resttrain.data.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    /**
     * Return param of type Call<Post>
     * Notion "post/1" to get the data from url with this specification
     */
    @GET("posts/2")
    fun getPost(): Call<Post>
}