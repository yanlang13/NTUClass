package com.example.examplelist;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseException;
import com.parse.SaveCallback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.sax.EndElementListener;
import android.text.AlteredCharSequence;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CameraActivity extends Activity {

	private GoogleMap gMap;
	private ProgressDialog progressDialog;
	private CameraPositionParse cpp;

	private TextView pressTextView, cameraTextView;

	public void onCreate(Bundle saveInstanceState) {

		super.onCreate(saveInstanceState);
		setContentView(R.layout.camera);
		setUpMapIfNeeded();

		pressTextView = (TextView) findViewById(R.id.press_position);
		cameraTextView = (TextView) findViewById(R.id.camera_position);
		progressDialog = new ProgressDialog(this);
		callTheLastCameraPostion();
	}// end of onCreate

	private void callTheLastCameraPostion() { // call from onCreate
		// ���^sharePrefrences����CameraPosition
		SharedPreferences sp = getSharedPreferences("Preferences",
				Context.MODE_PRIVATE);
		String latString = sp.getString("latitude", "0.0");
		String lngString = sp.getString("longitude", "0.0");
		float bearing = sp.getFloat("bearing", 0);
		float tilt = sp.getFloat("tilt", 0);
		float zoom = sp.getFloat("zoom", 0);
		//�Nstring �ରlatlng
		LatLng lastPosition = new LatLng(Double.valueOf(latString), Double.valueOf(lngString));
		
		CameraPosition cp = new CameraPosition(lastPosition, zoom, tilt, bearing);
		
		//moreCamera�]�wcamera
		//CameraUpdateFactory�Oreturn CameraUpdate class
		gMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
		
	} // end of callTheLastCameraPostion

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

				// �B�z�U��listener
				setUpMapListener();
			}
		}
	}// end of setUpMapIfNeeded()

	private void setUpMapListener() { // call from setUpMapIfNeeded
		// �@�جOimplement listener�A�N�i�H�����I�smethod�C
		// �@�شN�O�ϥΰΦW���O
		gMap.setOnCameraChangeListener(new OnCameraChangeListener() {
			@Override
			public void onCameraChange(CameraPosition cp) {
				cameraTextView.setText("Camera Centre: " + cp.target.toString());
			}
		});// end of gMap.setOnCameraChangeListener

		gMap.setOnMapClickListener(new OnMapClickListener() {
			@Override
			public void onMapClick(LatLng point) {
				pressTextView.setText("tap position: " + point.toString());
			}
		});// end of gMap.setOnMapClickListener

		gMap.setOnMapLongClickListener(new OnMapLongClickListener() {
			@Override
			public void onMapLongClick(LatLng point) {
				pressTextView.setText("long pressed position: "
						+ point.toString());
			}
		});// end of gMap.setOnMapLongClickListener
	} // end of setUpMapListener

	protected void onPause() {
		super.onPause();
		//�T�{���i�JonPause
		Log.d("debug", "onPause running");
		saveThePreferences();
	}// end of onPause

	public void saveThePreferences() {// call from onPause
		// ���ocp
		CameraPosition cp = gMap.getCameraPosition();
		String lat = String.valueOf(cp.target.latitude);
		String lng = String.valueOf(cp.target.longitude);
		float bearing = cp.bearing;
		float tilt = cp.tilt;
		float zoom = cp.zoom;
		
		
		// �гy�ɦW�A�H��Ū���Ҧ��C SharedPreferences�O�x�s²�檺KEY-VALUE
		SharedPreferences sp = getSharedPreferences("Preferences",
				Context.MODE_PRIVATE);

		// editor�t�d�ާ@�ɮ�
		SharedPreferences.Editor spe = sp.edit();
		//put key-value
		spe.putString("latitude", lat);
		spe.putString("longitude", lng);
		spe.putFloat("bearing", bearing);
		spe.putFloat("tilt", tilt);
		spe.putFloat("zoom", zoom);
		// ���������A�~�|�ק�
		spe.commit();


	}// end of saveTheLastCP
	
	
	public void onCameraList(View view){
		Intent intent = new Intent(this, CameraListActivity.class);
		startActivity(intent);
	}
	
	
	public void onSaveCamera(View view) { // call from XML
		// �s�ɵ���
		progressDialog.setTitle("Saving");
		progressDialog.setMessage("Please Wait.");
		progressDialog.show();

		// �NCameraPosition�� lat lng���}�A��LatLng class��
		CameraPosition cp = gMap.getCameraPosition();
		LatLng save = cp.target;
		Double lat = save.latitude;
		Double lng = save.longitude;

		// cpp����new�b�o�̡A�~�ಣ�ͷs��ObjectID
		cpp = new CameraPositionParse();
		cpp.setLatitude(lat);
		cpp.setLongtitude(lng);
		cpp.saveEventually(new SaveCallback() {
			@Override
			public void done(ParseException e) {
				progressDialog.dismiss();
			}
		});
		// // ��²�檺ParseObject�A�x���ĳ�ϥ�subclass�i�[�ֳt��
		// ParseObject cameraLocation = new ParseObject("cameraLocation");
		// cameraLocation.put("latitude", lat);
		// cameraLocation.put("longitude", lng);
		// // �s�ɦ��|�ءA�O�o�ϥ�saveCallBack�A���M�N�O���nmain thread
		// // 1.saveInBackground�A�I���s��
		// // 2.saveEventually�A�L�����ɷ|�Ȧs�A���U���s�u�A�s��
		// // 3.saveAll(object)�Nlist object���s��
	} // end of onSaveCamera

	// �a�϶ɱר��ץ\��==============================================
	public void onTiltMore(View v) { // call from XML
		// getCameraPosition()���ocamera����m(�OCameraPostion Class)
		// return the center of the padded region.
		// CameraPostion �O��camera����m�B�ɨ��B��ҤءB���_�w��ʨ���
		// LatLng�O�¸g�n�סACameraPosition�|�A�hzoom, tilt, bearing���T����T
		CameraPosition currentCameraPosition = gMap.getCameraPosition();
		// ���o�ɨ�
		float currentTilt = currentCameraPosition.tilt;
		float newTilt = currentTilt + 10;
		newTilt = (newTilt > 90) ? 90 : newTilt;
		// CameraPosition.Builder�MCameraPosition�O��Ӥ��P��CLASS
		CameraPosition updateCameraPosition = new CameraPosition.Builder(
				currentCameraPosition).tilt(newTilt).build();

		// �ݭn�ե�CameraUpdateFactory�ӧ���cameraPosition������
		changeCamera(CameraUpdateFactory
				.newCameraPosition(updateCameraPosition));
	}// end of onTiltMore

	public void onTiltLess(View v) { // call from XML
		CameraPosition currentCameraPosition = gMap.getCameraPosition();
		float currentTilt = currentCameraPosition.tilt;

		float newTilt = currentTilt - 10;
		newTilt = (newTilt < 0) ? 0 : newTilt;

		CameraPosition updateCameraPosition = new CameraPosition.Builder(
				currentCameraPosition).tilt(newTilt).build();

		changeCamera(CameraUpdateFactory
				.newCameraPosition(updateCameraPosition));
	}// end of onTiltLess

	private void changeCamera(CameraUpdate update) {// call from onTiltMore, on
													// TiltLess
		// �T�ز���cameraPosition���覡(��CameraUpdate Class)
		// 1.moveCamera(CameraUpdate), ��������
		// gMap.moveCamera(update);

		// 2.animateCamera(CameraUpdate), �w�C���ʡA��animate
		// gMap.animateCamera(update);

		// 3.animateCamera(CameraUpdate, GoogleMap.CancelableCallback)
		// callback�o�ͦbanimate����ɭ�(�|�Ұ�onCancel)�A���㵲���h�OonFinish()
		changeCamera(update, null);
	}// end of changeCamera(CameraUpdate update)

	private void changeCamera(CameraUpdate update, CancelableCallback callback) {// call
		gMap.animateCamera(update, callback);
	}// end of changeCamera
		// �a�϶ɱר��ץ\��==============================================
}// end of CameraActivity

