package com.example.examplelist;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

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
	public void ClickMapType(View view){ // call from XML:onClick
		Log.d("debuf", "click map type");
		showAlertDialog("Dialog，button位置");
	}//End of ClickMapType()
	
	//select map type 
	public void showAlertDialog(String message){ //call from ClickMapType
		Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("Map Type:");
		alertDialog.setMessage(message);
		DialogInterface.OnClickListener okClick = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//如果不做任何事情就會直接關閉對話方塊
			}
		};
		//the position they take within the dialog
		//4.x 按鈕位置：positive(正) neutral(中性) negative(反)
		alertDialog.setPositiveButton("setPositiveButton", okClick);
		alertDialog.setNeutralButton("setNeutralButton", okClick);
		alertDialog.setNegativeButton("setNegativeButton", okClick);
		alertDialog.show();
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