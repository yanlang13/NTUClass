package com.example.mappingtravel;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//tt
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setActionBarColor();
	}

	// menu開始==========================================================
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// actionBar上面的功能
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_search:
			openSearch();
			return true;
		case R.id.action_settings:
			openSettings();
			return true;
		case R.id.action_email:
			openEmail();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// menu的功能
	public void openSearch() {
	}

	// menu的功能
	public void openSettings() {
	}

	// menu的功能
	public void openEmail() {
	}

	// actionBar color setting
	public void setActionBarColor() {
		// 因為color是寫在res資料夾下，所以使用Resources來叫出來。
		Resources res = getResources();
		int actionBarColor = res.getColor(R.color.actionBarColor);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(actionBarColor));
	}

	// menu結束==========================================================

	// 選單開始=========================================================
	public void MtTravel(View view) {
		Intent intent = new Intent(this, MainTravelMap.class);
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
