package com.example.mappingtravel;


import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBarColorSetting();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void MtTravel(View view){
		Intent intent = new Intent(this, TravelMap.class);
		startActivity(intent);
	}
	public void MtLocation(View view){}
	public void MtPhoto(View view){}
	public void MtImformation(View view){}

	//actionBar color setting
	public void actionBarColorSetting(){
	//因為color是寫在res資料夾下，所以使用Resources來叫出來。
	Resources res = getResources();
	int color = res.getColor(R.color.actionBarColor);
	ActionBar bar = getActionBar();
	bar.setBackgroundDrawable(new ColorDrawable(color));

	}
}
