package com.leafy.ui.feeds

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leafy.R
import com.leafy.domain.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_item_view.view.*

class PostItemViewHolder(val view: View): RecyclerView.ViewHolder(view)

class PostListAdapter(): RecyclerView.Adapter<PostItemViewHolder>() {
    var posts = listOf<GetPlantsQuery.Plant?>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        val post = posts[position]
        holder.view.profile_imageView.setImageResource(R.drawable.template_profile_image)
        post?.imageUrl?.let {
            Picasso.get().load(Uri.parse(it)).into(holder.view.imageView)
        }
        holder.view.textView.text = post?.user?.username
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.post_item_view, parent, false)
        return PostItemViewHolder(view)
    }
}