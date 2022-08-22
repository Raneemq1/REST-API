package com.example.rest_train.view.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rest_train.R
import com.example.rest_train.data.model.Post
import com.example.rest_train.data.model.PostData
import com.example.rest_train.data.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var titleText: TextView
    private lateinit var titleText2: TextView
    private lateinit var titlePost: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        titleText = findViewById(R.id.titleText)
        titleText2 = findViewById(R.id.titleText2)
        titlePost = findViewById(R.id.titlePost)

        /**
         * Create post instance
         */
        val post: PostData = PostData(2, "The invisible guest", "Hello I suggest this movie to u")

        /**
         * Initialize retrofit builder
         * Determine convertor type
         */
        val builder: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        /**
         * Create an api interface instance
         */
        val apiInterface: ApiInterface = builder.create(ApiInterface::class.java)


        /**
         * Initialize call instance for getting the posts of userid =1
         */
        val call: Call<List<Post>> = apiInterface.getPostQuery("1")

        /**
         * Initialize call instance for getting the posts of userid =2
         */

        val call1: Call<Post> = apiInterface.getPostPath(2)


        /**
         * Handle call results of get query
         * onResponse display the post title
         * onFailure  display the failure message
         */
        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                titleText.text = response.body()?.get(2)?.title
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                titleText.text = t.toString()
            }

        })

        /**
         * Handle call results of get path
         * onResponse display the post title
         * onFailure  display the failure message
         */
        call1.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                titleText2.text = response.body()?.title
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                titleText2.text = t.toString()
            }

        })


        /**
         * Initialize call instance for storing new post
         */

        val callPost: Call<PostData> = apiInterface.storePost(post)

        callPost.enqueue(object : Callback<PostData> {
            override fun onResponse(call: Call<PostData>, response: Response<PostData>) {
                titlePost.text = response.body()?.title
            }

            override fun onFailure(call: Call<PostData>, t: Throwable) {
                titlePost.text = t.toString()
            }

        })


    }
}