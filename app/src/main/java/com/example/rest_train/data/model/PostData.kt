package com.example.rest_train.data.model

data class PostData ( val userId: Int, val title: String, val body: String) {
    constructor() : this(0, "", "")

}