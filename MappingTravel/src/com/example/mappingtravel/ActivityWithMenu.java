package com.example.mappingtravel;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public abstract class ActivityWithMenu extends Activity {
	
	public boolean onCreateOptionMenu(Menu menu){
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main_activity_actions, menu);
		return true;
	} 
	// There's also an implementation of onOptionsItemSelected()
	// which handles the behavior for the action bar's Up behavior.
	// Keep this one the way it is.
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu items for use in the action bar
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.main_activity_actions, menu);
			
			// Get the menu item.
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

		//menu����L�]�w=======================================================
		// actionBar color setting
		public void setActionBarColor() {
			// �]��color�O�g�bres��Ƨ��U�A�ҥH�ϥ�Resources�ӥs�X�ӡC
			Resources res = getResources();
			int actionBarColor = res.getColor(R.color.actionBarColor);
			ActionBar bar = getActionBar();
			bar.setBackgroundDrawable(new ColorDrawable(actionBarColor));
		}

	
}

