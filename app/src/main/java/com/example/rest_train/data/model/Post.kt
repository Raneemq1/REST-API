package com.example.rest_train.data.model

/**
 * Build a class similar to json object
 */
data class Post(val id: Int, val userId: Int, val title: String, val body: String) {
    constructor() : this(0, 0, "", "")
}