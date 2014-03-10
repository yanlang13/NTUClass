package com.example.mappingtravel;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

public abstract class ActivityWithMenu extends Activity {

	private ShareActionProvider shareActionProvider;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);

		// Locate MenuItem with ShareActionProvider
		MenuItem item = menu.findItem(R.id.action_share);
		// Fetch and store ShareActionProvider
		shareActionProvider = (ShareActionProvider) item.getActionProvider();
		setShareIntent(createShareIntent());
		// Return true to display menu

		/*
		 * If you want to add menu items to one of the descendant activities,
		 * override onCreateOptionsMenu() in that activity. Call
		 * super.onCreateOptionsMenu(menu) so the original menu items are
		 * created, then add new menu items with menu.add().
		 */
		return super.onCreateOptionsMenu(menu);
	}

	// Call to update the share intent
	private void setShareIntent(Intent shareIntent) {
		if (shareActionProvider != null) {
			shareActionProvider.setShareIntent(shareIntent);
		}
	}

	// Let see how we create an intent to share with other application
	private Intent createShareIntent() {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(Intent.EXTRA_TEXT,
				"http://androidtrainningcenter.blogspot.in");
		return shareIntent;
	}

	/*
	 * There's also an implementation of onOptionsItemSelected() which handles
	 * the behavior for the action bar's Up behavior. Keep this one the way it
	 * is. actionBar�W�����\��
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_search:
			doSearch();
			return true;
		case R.id.action_settings:
			doSettings();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// menu���\��
	public void doSearch() {
		Log.e("checkIn", "dosearch");
	}

	// menu���\��
	public void doSettings() {
	}

	// menu���\��
	public void openEmail() {
	}

	// menu����L�]�w=======================================================
	// actionBar color setting
	public void setActionBarColor() {
		// �]��color�O�g�bres��Ƨ��U�A�ҥH�ϥ�Resources�ӥs�X�ӡC
		Resources res = getResources();
		int actionBarColor = res.getColor(R.color.actionBarColor);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(actionBarColor));
	}

}
