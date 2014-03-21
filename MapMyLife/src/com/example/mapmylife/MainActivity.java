package com.example.mapmylife;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActivityWithMenu {
	
	private Button BTN_GMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setActionBarColor();
		
		BTN_GMap = (Button) findViewById(R.id.BTN_GMap);
		BTN_GMap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, GmapActivity.class);
				startActivity(intent);
			}
		});
	}
}