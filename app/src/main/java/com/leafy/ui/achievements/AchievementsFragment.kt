package com.leafy.ui.achievements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.leafy.R

class AchievementsFragment : Fragment() {

    private lateinit var viewModel: AchievementsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel =
                ViewModelProviders.of(this).get(AchievementsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        viewModel.plantUnits.observe(viewLifecycleOwner, Observer {
            print("-----------")
            print(it.message)
            print(it.data)
            print(it.data?.size)
            print("-----------")
        })
        return root
    }
}