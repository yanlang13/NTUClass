package com.example.mappingtravel;

import android.os.Bundle;
import android.R.anim;
import android.R.array;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class MainActivity extends ActivityWithMenu {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setActionBarColor();
	}
	// 選單開始=========================================================
	// intent讀this，所以要傳入view
	public void MtTravelList(View view) {
		Intent intent = new Intent(this, MainTravelList.class);
		startActivity(intent);
	}

	public void MtLocation(View view) {
		// 顯示距離layout邊界的功能
		PrButtonPosition();
	}

	public void MtPhoto(View view) {
		Intent intent = new Intent(this, MainPhoto.class);
		startActivity(intent);
	}

	public void MtNewTravel(View view) {
		Intent intent = new Intent(this, MainNewTravel.class);
		startActivity(intent);
	}

	/*
	 * //intent(意圖)是指程序去呼叫另外一個程序，眾多方法之一。 Intent intent = new Intent();
	 * intent.setClass(this, MessageActivity.class); startActivity(intent);
	 */

	public void MtImformation(View view) {
	}

	// 選單結束=========================================================
	// 其它功能區開始========================================================
	public void PrButtonPosition() {
		// 目前是顯示距離layout邊界的功能
		Button lButton = (Button) findViewById(R.id.button_Location);
		Integer left = lButton.getLeft();
		Integer top = lButton.getTop();
		String s = "left distance: " + left.toString() + ", Top distance: "
				+ top.toString();
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}

	// 其它功能區開始========================================================
}
