package com.example.app.features.map

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.app.features.BaseFragment
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.google.maps.GeoApiContext
import com.google.maps.android.PolyUtil
import com.google.maps.model.DirectionsResult
import kotlinx.android.synthetic.main.fragment_map.view.*
import com.example.app.R
import com.example.app.features.MainActivity
import java.util.*


class MapFragment : BaseFragment(), MapFragmentView, OnMapReadyCallback,
    GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener,
    GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener,
    GoogleMap.OnMarkerDragListener,
    GoogleMap.OnMapClickListener,
    GoogleMap.OnMapLongClickListener,
    GoogleMap.OnMarkerClickListener {

    lateinit var mMapView: MapView
    private lateinit var googleMap: GoogleMap
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private var init = false

    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var geoApiContext: GeoApiContext

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(getLayoutID(), container, false)

        mMapView = rootView.mapView
        mMapView.onCreate(savedInstanceState)

        mMapView.onResume() // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        geoApiContext = GeoApiContext.Builder()
            .apiKey("AIzaSyDVsJx-Hyq6w4laps9vUcA1gbq-mWLtH78")
            .build()
        googleApiClient = GoogleApiClient.Builder(context!!)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()
        googleApiClient.connect()

        mMapView.getMapAsync(this)

        return rootView
    }

    private fun getCity() {
        val gcd = Geocoder(context, Locale.getDefault())
        val addresses = gcd.getFromLocation(latitude, longitude, 1)
        if (addresses.size > 0) {
            if (!init) {
                init = true
            }
        }
    }

    override fun getLayoutID() = R.layout.fragment_map

    override fun onMapReady(mMap: GoogleMap?) {
        googleMap = mMap!!

        val permList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)

        ActivityCompat.requestPermissions(activity!!, permList, 1)

        if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "No Location Permissions", Toast.LENGTH_LONG).show()
        } else {
            googleMap.isMyLocationEnabled = true
            googleMap.setOnMyLocationButtonClickListener(this)
            googleMap.setOnMyLocationClickListener(this)
            googleMap.setOnMapClickListener(this)
            googleMap.setOnMapLongClickListener(this)
            googleMap.setOnMarkerClickListener(this)
            googleMap.setOnMarkerDragListener(this)
            getCurrentLocation()
        }
    }


    private fun moveMap() {
        /**
         * Creating the latlng object to store lat, long coordinates
         * adding marker to map
         * move the camera with animation
         */
        val latLng = LatLng(latitude, longitude)

        val cameraPosition = CameraPosition.Builder().target(latLng).zoom(15f).build()

        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        googleMap.uiSettings.isZoomControlsEnabled = true
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        val location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient)
        if (location != null) {
            //Getting longitude and latitude
            longitude = location.longitude
            latitude = location.latitude

            getCity()

            //moving the map to location
            moveMap()
        }
    }

    override fun showMessage(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Toast.makeText(context, activity.toString(), Toast.LENGTH_LONG).show() // MainActivity
    }

    override fun onMyLocationButtonClick(): Boolean {
        getCurrentLocation()
        return false

    }

    override fun onMyLocationClick(location: Location) {
        Toast.makeText(context, "Current location:\n$location", Toast.LENGTH_LONG).show()
    }

    override fun onConnected(bundle: Bundle?) {
        getCurrentLocation()
    }

    override fun onConnectionSuspended(i: Int) {

    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Toast.makeText(context, "onConnectionFailed", Toast.LENGTH_SHORT).show()
    }

    override fun onMapLongClick(latLng: LatLng) {
        googleMap.clear()
    }

    override fun onMapClick(p0: LatLng?) {
        googleMap.clear()
        googleMap.addMarker(MarkerOptions().position(p0!!).draggable(true))
        latitude = p0.latitude
        longitude = p0.longitude
        Toast.makeText(context, "Lat:$latitude, Lng:$longitude", Toast.LENGTH_SHORT).show()
    }

    override fun onMarkerDragStart(marker: Marker) {
        Toast.makeText(context, "onMarkerDragStart", Toast.LENGTH_SHORT).show()
    }

    override fun onMarkerDrag(marker: Marker) {
        Toast.makeText(context, "onMarkerDrag", Toast.LENGTH_SHORT).show()
    }

    override fun onMarkerDragEnd(marker: Marker) {
        Toast.makeText(context, "onMarkerDragEnd", Toast.LENGTH_SHORT).show()
        // getting the Co-ordinates
        latitude = marker.position.latitude
        longitude = marker.position.longitude

        moveMap()
    }


    override fun onMarkerClick(marker: Marker): Boolean {
        marker.showInfoWindow()
        Toast.makeText(context, "onMarkerClick", Toast.LENGTH_SHORT).show()
        return true
    }

    companion object {
        fun newInstance() = MapFragment()
    }

}