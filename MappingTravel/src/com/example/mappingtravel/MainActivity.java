package com.example.mappingtravel;

import android.os.Bundle;
import android.R.integer;
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

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setActionBarColor();
	}

	// menu�}�l==========================================================
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// actionBar�W�����\��
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

	// menu���\��
	public void openSearch() {
	}

	// menu���\��
	public void openSettings() {
	}

	// menu���\��
	public void openEmail() {
	}

	// actionBar color setting
	public void setActionBarColor() {
		// �]��color�O�g�bres��Ƨ��U�A�ҥH�ϥ�Resources�ӥs�X�ӡC
		Resources res = getResources();
		int actionBarColor = res.getColor(R.color.actionBarColor);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(actionBarColor));
	}

	// menu����==========================================================

	// ���}�l=========================================================
	public void MtTravel(View view) {
		Intent intent = new Intent(this, TravelMap.class);
		startActivity(intent);
	}

	public void MtLocation(View view) {
		Button lButton = (Button) findViewById(R.id.button_Location);
		Integer left = lButton.getLeft();
		Integer top = lButton.getTop();
		String s = "left distance: " + left.toString() 
				+ ", Top distance: " +top.toString(); 
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}

	public void MtPhoto(View view) {
	}

	public void MtImformation(View view) {
	}
	// ��浲��=========================================================

}
