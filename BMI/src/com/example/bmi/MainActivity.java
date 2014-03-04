package com.example.bmi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText editText1;
	private EditText editText2;
	private TextView textView1;
	private Double bmi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void calculateBMI(View view) {
		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		textView1 = (TextView) findViewById(R.id.textView3);
		String s1 = editText1.getText().toString();
		String s2 = editText2.getText().toString();
		bmi = Double.valueOf(s2)/ Math.pow(Double.valueOf(s1)/100,2);
		Double rBmi = Math.rint(bmi);
		textView1.setText(rBmi.toString());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
