package com.example.examplelist;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class CameraListActivity extends Activity {
	private String name;
	private ProgressDialog progressDialog;
	private TextView textView;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cameralist);
		
		textView = (TextView) findViewById(R.id.textView11);
		progressDialog = new ProgressDialog(this);
		
		progressDialog.show();
		// ParseQuery ¨Ï¥Î subclass CameraSaveParse
		// getQuery Creates a new query for the given ParseObject subclass type.
		ParseQuery<CameraSaveParse> query = ParseQuery
				.getQuery(CameraSaveParse.class);
		query.findInBackground(new FindCallback<CameraSaveParse>() {
			@Override
			public void done(List<CameraSaveParse> results, ParseException e) {
				for (CameraSaveParse csp : results){
					 String cspName = csp.getName();
						name += cspName+", ";
				}
				textView.setText(name);
				progressDialog.dismiss();
			}// end of done
		});// end of query.findInBackground
	}// end of onCreate
}// end of CameraListActivity
private void getDataFromParse(){
	
}// end of getDataFromParse


