package com.leafy.ui.feeds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leafy.R
import com.leafy.domain.Post
import kotlinx.android.synthetic.main.post_item_view.view.*

class PostItemViewHolder(val view: View): RecyclerView.ViewHolder(view)

class PostListAdapter(): RecyclerView.Adapter<PostItemViewHolder>() {
    var posts = listOf<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        val post = posts[position]
        holder.view.imageView.setImageResource(post.image)
        holder.view.profile_imageView.setImageResource(post.profileImage)
        holder.view.textView.text = post.username
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.post_item_view, parent, false)
        return PostItemViewHolder(view)
    }
}