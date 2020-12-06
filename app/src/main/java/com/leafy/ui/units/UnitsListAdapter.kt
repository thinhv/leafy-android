package com.leafy.ui.units

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leafy.R
import com.leafy.models.PlantUnit
import kotlinx.android.synthetic.main.plant_unit_item_view.view.*

interface OnClickPlantUnitItemListener {
    fun onClickPlanUnitItem(item: PlantUnit)
}

class PlanUnitItemViewHolder(val view: View): RecyclerView.ViewHolder(view)

class UnitsListAdapter(private val onClickPlantUnitItemListener: OnClickPlantUnitItemListener): RecyclerView.Adapter<PlanUnitItemViewHolder>() {
    var items = listOf<PlantUnit>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanUnitItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.plant_unit_item_view, parent, false)
        return PlanUnitItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanUnitItemViewHolder, position: Int) {
        val item = items[position]
        holder.view.plant_unit_name_textview.text = "Unit " + item.id
        holder.view.plant_unit_date_textview.text = item.publishTime.toString()
        holder.view.setOnClickListener {
            onClickPlantUnitItemListener.onClickPlanUnitItem(item)
        }
    }
}