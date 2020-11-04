package com.leafy.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leafy.R
import com.leafy.domain.DashboardItem
import com.leafy.domain.Post
import kotlinx.android.synthetic.main.dashboard_item_view.view.*
import kotlinx.android.synthetic.main.post_item_view.view.*

class DashboardItemViewHolder(val view: View): RecyclerView.ViewHolder(view)

class DashboardListAdapter(): RecyclerView.Adapter<DashboardItemViewHolder>() {
    var items = listOf<DashboardItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: DashboardItemViewHolder, position: Int) {
        val item = items[position]
        holder.view.plant_imageView.setImageResource(item.image)
        holder.view.dashboard_item_textView.text = item.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.dashboard_item_view, parent, false)
        return DashboardItemViewHolder(view)
    }
}