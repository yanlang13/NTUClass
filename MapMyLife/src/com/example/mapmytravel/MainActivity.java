package com.example.mapmytravel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActivityWithMenu {
	
	private Button buttonGMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setActionBarColor();
		
		buttonGMap = (Button) findViewById(R.id.GMap);
		buttonGMap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, GmapActivity.class);
				startActivity(intent);
			}
		});
	}
}