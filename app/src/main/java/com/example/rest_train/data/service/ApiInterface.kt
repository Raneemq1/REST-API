package com.example.rest_train.data.service

import com.example.rest_train.data.model.Post
import com.example.rest_train.data.model.PostData
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    /**
     * Return param of type Call<Post>
     * Notion "post/1" to get the data from url with this specification
     * @GET("posts/1") or @GET("posts/{id}) and use path notation to get the information of specific post
     * @GET("posts") and add @Query("userId") userId:String) it returns list
     */
    @GET("posts")
    fun getPostQuery(@Query("userId") userId:String): Call<List<Post>>


    @GET("posts/{id}")
    fun getPostPath(@Path("id") userId:Int): Call<Post>


    /**
     * Create post method to store new object
     */
    @POST("posts")
    fun storePost(@Body post:PostData):Call<PostData>
}