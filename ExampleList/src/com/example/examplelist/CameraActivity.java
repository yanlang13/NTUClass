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
		// 取回sharePrefrences中的CameraPosition
		SharedPreferences sp = getSharedPreferences("Preferences",
				Context.MODE_PRIVATE);
		String latString = sp.getString("latitude", "0.0");
		String lngString = sp.getString("longitude", "0.0");
		//將string 轉為latlng
		LatLng lastPosition = new LatLng(Double.valueOf(latString), Double.valueOf(lngString));
		
		//moreCamera設定camera
		//CameraUpdateFactory是return CameraUpdate class
		gMap.moveCamera(CameraUpdateFactory.newLatLng(lastPosition));
		
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
				// 關閉ZoomControls(會被button擋到)
				gMap.getUiSettings().setZoomControlsEnabled(false);

				// 處理各式listener
				setUpMapListener();
			}
		}
	}// end of setUpMapIfNeeded()

	private void setUpMapListener() { // call from setUpMapIfNeeded
		// 一種是implement listener，就可以直接呼叫method。
		// 一種就是使用匿名類別
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
		//確認有進入onPause
		Log.d("debug", "onPause running");
		saveThePreferences();
	}// end of onPause

	public void saveThePreferences() {// call from onPause
		// 取得cp
		CameraPosition cp = gMap.getCameraPosition();
		String lat = String.valueOf(cp.target.latitude);
		String lng = String.valueOf(cp.target.longitude);

		// 創造檔名，以及讀取模式。 SharedPreferences是儲存簡單的KEY-VALUE
		SharedPreferences sp = getSharedPreferences("Preferences",
				Context.MODE_PRIVATE);

		// editor負責操作檔案
		SharedPreferences.Editor spe = sp.edit();
		//put key-value
		spe.putString("latitude", lat);
		spe.putString("longitude", lng);
		// 完成必做，才會修改
		spe.commit();


	}// end of saveTheLastCP

	public void onSaveCamera(View view) { // call from XML
		// 存檔等待
		progressDialog.setTitle("Saving");
		progressDialog.setMessage("Please Wait.");
		progressDialog.show();

		// 將CameraPosition的 lat lng分開，用LatLng class接
		CameraPosition cp = gMap.getCameraPosition();
		LatLng save = cp.target;
		Double lat = save.latitude;
		Double lng = save.longitude;

		// cpp必須new在這裡，才能產生新的ObjectID
		cpp = new CameraPositionParse();
		cpp.setLatitude(lat);
		cpp.setLongtitude(lng);
		cpp.saveEventually(new SaveCallback() {
			@Override
			public void done(ParseException e) {
				progressDialog.dismiss();
			}
		});
		// // 最簡單的ParseObject，官方建議使用subclass可加快速度
		// ParseObject cameraLocation = new ParseObject("cameraLocation");
		// cameraLocation.put("latitude", lat);
		// cameraLocation.put("longitude", lng);
		// // 存檔有四種，記得使用saveCallBack，不然就是不要main thread
		// // 1.saveInBackground，背景存檔
		// // 2.saveEventually，無網路時會暫存，等下次連線再存檔
		// // 3.saveAll(object)將list object都存檔
	} // end of onSaveCamera

	// 地圖傾斜角度功能==============================================
	public void onTiltMore(View v) { // call from XML
		// getCameraPosition()取得camera的位置(是CameraPostion Class)
		// return the center of the padded region.
		// CameraPostion 是談camera的位置、傾角、比例尺、指北針轉動角度
		// LatLng是純經緯度，CameraPosition會再多zoom, tilt, bearing等三項資訊
		CameraPosition currentCameraPosition = gMap.getCameraPosition();
		// 取得傾角
		float currentTilt = currentCameraPosition.tilt;
		float newTilt = currentTilt + 10;
		newTilt = (newTilt > 90) ? 90 : newTilt;
		// CameraPosition.Builder和CameraPosition是兩個不同的CLASS
		CameraPosition updateCameraPosition = new CameraPosition.Builder(
				currentCameraPosition).tilt(newTilt).build();

		// 需要調用CameraUpdateFactory來完成cameraPosition的移動
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
		// 三種移動cameraPosition的方式(接CameraUpdate Class)
		// 1.moveCamera(CameraUpdate), 瞬間移動
		// gMap.moveCamera(update);

		// 2.animateCamera(CameraUpdate), 緩慢移動，有animate
		// gMap.animateCamera(update);

		// 3.animateCamera(CameraUpdate, GoogleMap.CancelableCallback)
		// callback發生在animate停止的時候(會啟動onCancel)，完整結束則是onFinish()
		changeCamera(update, null);
	}// end of changeCamera(CameraUpdate update)

	private void changeCamera(CameraUpdate update, CancelableCallback callback) {// call
		gMap.animateCamera(update, callback);
	}// end of changeCamera
		// 地圖傾斜角度功能==============================================
}// end of CameraActivity

