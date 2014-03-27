package com.example.examplelist;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CameraActivity extends Activity {
	private GoogleMap gMap;

	public void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		setContentView(R.layout.camera);
		setUpMapIfNeeded();
	}// end of onCreate

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}// end of onResume()

	private void setUpMapIfNeeded() { // call from onResume()
		if (gMap == null) {
			gMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.gMap)).getMap();
			if (gMap != null) {
				// 關閉ZoomControls(會被button擋到)
				gMap.getUiSettings().setZoomControlsEnabled(false);
			}
		}
	}// end of setUpMapIfNeeded()

	public void onTiltMore(View v) { // call from XML
		// getCameraPosition()取得camera的位置(是CameraPostion Class)
		// return the center of the padded region.
		// CameraPostion 是談camera的位置、傾角、比例尺、指北針轉動角度
		// LatLng是純經緯度，CameraPosition會再多zoom, tilt, bearing等三項資訊
		CameraPosition currentCameraPosition = gMap.getCameraPosition();
		// 取得傾角
		float currentTilt = currentCameraPosition.tilt;
		
		float newTile = currentTilt + 10;
		
		CameraPosition updateCameraPosition = new CameraPosition.Builder(
				currentCameraPosition).tilt(currentTilt).build();
	}// end of onTiltMore

	public void onTiltLess(View v) { // call from XML

	}// end of onTiltLess
	
	private void changeCamera(){// call from onTilrMoew, on TiltLess
		
	}// end of changeCamera
	
	
}// end of CameraActivity

