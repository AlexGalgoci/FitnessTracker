package com.example.fitnesstracker.ui.socialfeed

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.R

class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val caption: TextView = view.findViewById(R.id.textViewCaption)
        val content: TextView = view.findViewById(R.id.textViewContent)
        val image: ImageView = view.findViewById(R.id.imageViewPost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.caption.text = post.caption
        holder.content.text = post.content
        holder.image.setImageURI(Uri.parse(post.imageUri))
    }

    override fun getItemCount() = posts.size

    fun updatePosts(newPosts: List<Post>) {
        (posts as MutableList).clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }
}
