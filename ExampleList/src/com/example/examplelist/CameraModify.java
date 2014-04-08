package com.example.examplelist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class CameraModify extends Activity{
	private EditText et_name, et_desc;
	@Override
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_modify);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		et_name = (EditText) findViewById(R.id.camera_modify_name);
		et_desc = (EditText) findViewById(R.id.camera_modify_desc);
	
		getDataFromListActivity();
		
	}// end of onCreate
	
	private void getDataFromListActivity(){ // call from onCreate
		Bundle bundle = getIntent().getExtras();
		String name = bundle.getString("Name");
		String desc = bundle.getString("Desc");
		et_name.setText(name);
		et_desc.setText(desc);
	}// end of getDataFromListActivity
	
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == android.R.id.home){
			Intent upIntent = NavUtils.getParentActivityIntent(this);
			if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
				TaskStackBuilder.create(this)
				.addNextIntentWithParentStack(upIntent)
				.startActivities();
				
			} else {
				NavUtils.navigateUpFromSameTask(this);
			}
		}//end of if item.getItemId
		return super.onOptionsItemSelected(item);
	}// end of on onOptionsItemSelected
}// end of  onOptionsItemSelected
