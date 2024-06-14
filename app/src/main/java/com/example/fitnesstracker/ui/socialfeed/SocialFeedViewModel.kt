package com.example.fitnesstracker.ui.socialfeed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SocialFeedViewModel : ViewModel() {
    private val _posts = MutableLiveData<MutableList<Post>>(mutableListOf())
    val posts: LiveData<MutableList<Post>> = _posts

    fun addPost(post: Post) {
        _posts.value?.add(post)
        _posts.value = _posts.value
    }

    fun updatePosts(posts: List<Post>) {
        _posts.value = posts.toMutableList()
    }
}
