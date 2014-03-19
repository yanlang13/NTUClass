package com.example.mapmytravel;

import android.os.Bundle;

public class GmapActivity extends ActivityWithMenu {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gmap);
		setActionBarColor();
	}
}
