package com.example.testpickupmap.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.testpickupmap.R

class MainFragment(val mapInterface: MapInterface) : Fragment() {

    companion object {
        fun newInstance(mapInterface: MapInterface) = MainFragment(mapInterface)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val layout =  inflater.inflate(R.layout.main_fragment, container, false)
        layout.findViewById<Button>(R.id.add_home_pin_button).setOnClickListener {
            mapInterface.setHomePoint()
        }
        layout.findViewById<Button>(R.id.add_pin_button).setOnClickListener {
            mapInterface.setPoint()
        }
        layout.findViewById<Button>(R.id.remove_pin_button).setOnClickListener {
            mapInterface.removeMapPoint()
        }
        layout.findViewById<Button>(R.id.highlight_pin_button).setOnClickListener {
            mapInterface.highlightPoint()
        }
        layout.findViewById<Button>(R.id.unhighlight_pin_button).setOnClickListener {
            mapInterface.unhighlightPoint()
        }
        return layout

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }



}