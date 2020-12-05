package com.leafy.ui.plantunitdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leafy.R
import com.leafy.domain.DashboardItem
import com.leafy.models.PlantUnit
import com.leafy.models.PlantUnitData
import kotlinx.android.synthetic.main.dashboard_item_view.view.*

class PlantUnitDetailsItemHolder(val view: View): RecyclerView.ViewHolder(view)

class PlantUnitDetailsListAdapter(): RecyclerView.Adapter<PlantUnitDetailsItemHolder>() {
    var items = listOf<PlantUnitData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PlantUnitDetailsItemHolder, position: Int) {
        val item = items[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantUnitDetailsItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.dashboard_item_view, parent, false)
        return PlantUnitDetailsItemHolder(view)
    }
}
