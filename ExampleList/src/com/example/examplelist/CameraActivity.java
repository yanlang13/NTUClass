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
				// ����ZoomControls(�|�Qbutton�ר�)
				gMap.getUiSettings().setZoomControlsEnabled(false);
			}
		}
	}// end of setUpMapIfNeeded()

	public void onTiltMore(View v) { // call from XML
		// getCameraPosition()���ocamera����m(�OCameraPostion Class)
		// return the center of the padded region.
		// CameraPostion �O��camera����m�B�ɨ��B��ҤءB���_�w��ʨ���
		// LatLng�O�¸g�n�סACameraPosition�|�A�hzoom, tilt, bearing���T����T
		CameraPosition currentCameraPosition = gMap.getCameraPosition();
		// ���o�ɨ�
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

