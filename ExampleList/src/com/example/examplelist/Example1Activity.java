package com.example.examplelist;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.animation.AnimatorSet.Builder;
import android.app.Activity;
import android.os.Bundle;
import android.sax.EndElementListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Example1Activity extends Activity {
	private GoogleMap mMap; 
	private Button buttonMapType; //change map type

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example1);
		setUpMapIfNeeded();
	}// end of onCcreate{}
	
	//change map type
	public void ClickMapType(View view){
		Log.d("debuf", "click map type");
		showAlertDialog();
	}//End of ClickMapType()
	
	//select map type
	public void showAlertDialog(){
		
	}//End of showAlertDialog()
	
	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}// end of onResume()
	
	private void setUpMapIfNeeded() { // call from onResume()
		if (mMap == null) {
			mMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.mMap)).getMap();
			if (mMap != null) {
				setUpMap();
			}
		}
	}// end of setUpMapIfNeeded()
	
	private void setUpMap() { // call from setUpMapIfNeeded()
		mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title(
				"Marker"));
	}// end of setUpMap()
}