package com.example.testpickupmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testpickupmap.ui.main.MainFragment
import com.example.testpickupmap.ui.main.MapInterface
import com.example.testpickupmap.ui.main.MapsFragment

class MainActivity : AppCompatActivity(), MapInterface {
    var mapsFrag: MapsFragment = MapsFragment.newInstance()
    var mainFrag: MainFragment = MainFragment.newInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.map_space, mapsFrag)
                .replace(R.id.button_box, mainFrag)
                .commitNow()
        }
    }

    override fun setHomePoint() {
        mapsFrag.addHomePoint()
    }

    override fun setPoint() {
        mapsFrag.addPoint()
    }

    override fun removeMapPoint() {
        mapsFrag.removeThePoint()
    }

    override fun highlightPoint() {
        mapsFrag.highlightPoint()
    }

    override fun unhighlightPoint() {
        mapsFrag.unhighlightPoint()
    }
}