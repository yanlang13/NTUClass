package com.example.mapmytravel;

import android.os.Bundle;

public class MainActivity extends ActivityWithMenu {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setActionBarColor();
	}
}