//¥D­n­¶­±

package com.example.mappingtravel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainTravelMap extends ActivityWithMenu {
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
	
	//share function to other activities
	public void shareMap(View view){
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(android.content.Intent.EXTRA_TEXT, "Share map for the others.");
		startActivity(intent); 
	}
}
