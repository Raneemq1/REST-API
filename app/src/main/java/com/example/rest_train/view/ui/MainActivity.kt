package com.example.resttrain.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rest_train.databinding.ActivityMainBinding
import com.example.rest_train.R
import com.example.resttrain.data.model.Post
import com.example.resttrain.data.service.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

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
         * Initialize call instance
         */

        val call: Call<Post> = apiInterface.getPost(1)

        /**
         * Handle call results
         * onResponse display the post title
         * onFailure  display the failure message
         */
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                binding.titleText.setText(response.body()?.title)
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                binding.titleText.setText(t.toString())
            }

        })

    }
}