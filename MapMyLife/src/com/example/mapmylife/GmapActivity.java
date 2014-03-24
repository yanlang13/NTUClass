package com.example.mapmylife;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.Bundle;

public class GmapActivity extends ActivityWithMenu {

	private GoogleMap mMap;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarColor();
		setContentView(R.layout.activity_gmap);
		setUpMapIfNeeded();
	}// end of onCreate();

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// 使用activity時，supportMapFragment，和getSupportFragmentManager都要將support拿掉(appv4)
			mMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.mMap)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap();
				mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			}
		}
	}// end of setUpMapIfNeeded();

	private void setUpMap() {
		/**
		 * This is where we can add markers or lines, add listeners or move the
		 * camera. This should only be called once and when we are sure that
		 * mMap is not null.
		 */
		MarkerOptions mk = new MarkerOptions();
		LatLng position = new LatLng(10, 10);
		mk.position(position);
		mk.title("maker1");
		mMap.addMarker(mk);
		mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title(
				"Maker"));
	}// end of setUpMap();

}// end of class GmapActiviy

