//主要頁面

package com.example.mappingtravel;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainTravelMap extends Activity {
	// Additionally, you must add the @SuppressLint("NewApi") tag to the
	// onCreate() method to avoid lint errors.
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarColor();
		setContentView(R.layout.activity_main_travelmap);

		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	// There's also an implementation of onOptionsItemSelected()
	// which handles the behavior for the action bar's Up behavior.
	// Keep this one the way it is.
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
	
	public void setActionBarColor() {
		// 因為color是寫在res資料夾下，所以使用Resources來叫出來。
		Resources res = getResources();
		int actionBarColor = res.getColor(R.color.actionBarColor);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(actionBarColor));
	}
	// menu結束==========================================================
}
