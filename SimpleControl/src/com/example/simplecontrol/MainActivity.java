package com.example.simplecontrol;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
//layout會有很多個，所以要看UI是在哪個layout下再做import
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.textView1);
		
	}
	
	
	public void moveLeft(View view){
		//取得UI的方式(layout下的UI應該都可以用此方法取得，Button也可以)
		RelativeLayout.LayoutParams params = (LayoutParams) textView.getLayoutParams();
		//距離左邊邊界的距離
		params.leftMargin-= 10;
		textView.setLayoutParams(params);
		
		
	}
	public void moveRight(View view){
		RelativeLayout.LayoutParams params = (LayoutParams) textView.getLayoutParams();
		params.leftMargin+= 10;
		textView.setLayoutParams(params);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
