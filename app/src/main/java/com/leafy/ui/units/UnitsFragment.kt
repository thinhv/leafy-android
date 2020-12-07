package com.leafy.ui.units

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.leafy.R
import com.leafy.models.PlantUnit
import com.leafy.repository.Status
import com.leafy.ui.plantunitdetails.PlantUnitDetailsActivity
import kotlinx.android.synthetic.main.fragment_units.*

class UnitsFragment : Fragment(), OnClickPlantUnitItemListener {

    private lateinit var viewModel: UnitsViewModel
    private lateinit var adapter: UnitsListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(UnitsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_units, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()
    }

    private fun setupAdapter() {
        adapter = UnitsListAdapter(onClickPlantUnitItemListener = this)
        val layoutManager = LinearLayoutManager(activity)
        plant_units_rc.layoutManager = layoutManager
        plant_units_rc.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.plantUnits.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.ERROR -> progressBar.visibility = View.GONE
                Status.LOADING -> progressBar.visibility = View.VISIBLE
                Status.SUCCESS -> progressBar.visibility = View.GONE
            }
            adapter.items = it.data ?: listOf<PlantUnit>()
        })
    }

    override fun onClickPlanUnitItem(item: PlantUnit) {
        activity?.let{
            val intent = Intent (it, PlantUnitDetailsActivity::class.java)
            intent.putExtra("PLANT_UNIT_ID", item.id)
            it.startActivity(intent)
        }
    }
}