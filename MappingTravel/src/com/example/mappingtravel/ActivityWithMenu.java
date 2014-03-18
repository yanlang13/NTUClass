package com.example.mappingtravel;

import java.util.Arrays;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.OnNavigationListener;
import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ShareActionProvider;
import android.widget.SpinnerAdapter;
import android.app.FragmentTransaction; 

public abstract class ActivityWithMenu extends Activity {

	private ShareActionProvider shareActionProvider;
	private OnNavigationListener mOnNavigationListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//===============
		SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this,
				R.array.List_DropDown,
				android.R.layout.simple_dropdown_item_1line);
		mOnNavigationListener = new OnNavigationListener() {
			// Get the same strings provided for the drop-down's ArrayAdapter
			String[] strings = getResources().getStringArray(
					R.array.List_DropDown);

			@Override
			public boolean onNavigationItemSelected(int position, long itemId) {
				// Create new fragment from our own Fragment class
				ListContentFragment newFragment = new ListContentFragment();
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				// Replace whatever is in the fragment container with this
				// fragment
				// and give the fragment a tag name equal to the string at the
				// position selected
				ft.replace(R.id.fragment_container, newFragment,
						strings[position]);
				// Apply changes
				ft.commit();
				return true;
			}
		};
		//===============
	}

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
	 * is. actionBar上面的功能
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

	// menu的功能
	public void doSearch() {
		Log.e("checkIn", "dosearch");
	}

	// menu的功能
	public void doSettings() {
	}

	// menu的功能
	public void openEmail() {
	}

	// menu的其他設定=======================================================
	// actionBar color setting
	public void setActionBarColor() {
		// 因為color是寫在res資料夾下，所以使用Resources來叫出來。
		Resources res = getResources();
		ActionBar bar = getActionBar();
		int actionBarColor = res.getColor(R.color.actionBarColor_lightgreen);
		bar.setBackgroundDrawable(new ColorDrawable(actionBarColor));
	}

}
