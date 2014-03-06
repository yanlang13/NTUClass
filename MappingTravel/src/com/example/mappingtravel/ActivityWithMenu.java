package com.example.mappingtravel;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;

public class ActivityWithMenu extends Activity {
	public boolean onCreateOptionMenu(Menu menu){
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main_activity_actions, menu);
		return true;
	} 
}
