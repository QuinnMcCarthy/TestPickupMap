package com.example.testpickupmap.ui.main

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.testpickupmap.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsFragment : Fragment() {

    var map: GoogleMap? = null

    val markers: ArrayList<Marker> = ArrayList()

    private val callback = object : OnMapReadyCallback {
        override fun onMapReady(googleMap: GoogleMap) {
            map = googleMap
            /**
             * Manipulates the map once available.
             * This callback is triggered when the map is ready to be used.
             * This is where we can add markers or lines, add listeners or move the camera.
             * In this case, we just add a marker near Sydney, Australia.
             * If Google Play services is not installed on the device, the user will be prompted to
             * install it inside the SupportMapFragment. This method will only be triggered once the
             * user has installed Google Play services and returned to the app.
             */
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    fun addHomePoint(
        home: LatLng = LatLng(42.57406, -87.92433)
    ) = map?.addMarker(
        MarkerOptions()
            .position(home)
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
            .title("Marker in HOME")
    )?.let {
        it.tag = "Home"
        markers.add(it)
        zoomOnCoordinate(home)
    }

    fun addPoint() = map?.cameraPosition?.target?.let { coordinate ->
        map?.addMarker(
            MarkerOptions()
                .position(coordinate)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
                .title("Dropped Pin")
        )?.let {
            it.tag = coordinate.toString()
            markers.add(it)
            zoomOnCoordinate(coordinate)
        }
    }

    fun removeThePoint() {
        val lastMarker = markers.removeLastOrNull()
        lastMarker?.let {
            val tag: String = (lastMarker.tag as? String ?: "tag fail")
            Toast.makeText(context!!, tag, Toast.LENGTH_SHORT).show()
            lastMarker.remove()
        }
        markers.lastOrNull()?.let { zoomOnCoordinate(it.position) }

    }

    fun highlightPoint() {
        markers.lastOrNull()
            ?.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pin_highlighted))
    }

    fun unhighlightPoint() {
        markers.lastOrNull()?.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
    }

    private fun zoomOnCoordinate(coordinate: LatLng) {
        val pos: CameraPosition = CameraPosition
            .builder()
            .target(coordinate)
            .zoom(10.0f)
            .build()
        map?.animateCamera(CameraUpdateFactory.newCameraPosition(pos))
    }

    companion object {
        fun newInstance() = MapsFragment()
    }
}