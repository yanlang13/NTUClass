package com.example.mappingtravel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

public class TravelMap extends Activity {
	// Additionally, you must add the @SuppressLint("NewApi") tag to the
	// onCreate() method to avoid lint errors.
	@SuppressLint("NewApi")
	// »Ý­n import android.annotation.SuppressLint;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travelmap);
		
        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
	}

	@Override
	//There's also an implementation of onOptionsItemSelected() 
    //which handles the behavior for the action bar's Up behavior. 
    //Keep this one the way it is.
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
