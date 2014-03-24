package com.example.calculator;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText editText_Display;
	public String str = "";
	Character op = 'q';
	float i, num, numtemp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editText_Display = (EditText) findViewById(R.id.EDT_Display);

	}

	private void insert(String j) {
		// TODO Auto-generated method stub
		str = str + j;
		num = Float.valueOf(str).floatValue();
		editText_Display.setText(str);
	}

	public void N0(View v) {
		insert("0");
	}

	public void N1(View v) {
		insert("1");
	}

	public void N2(View v) {
		insert("2");
	}

	public void N3(View v) {
		insert("3");
	}

	public void N4(View v) {
		insert("4");
	}

	public void N5(View v) {
		insert("5");
	}

	public void N6(View v) {
		insert("6");
	}

	public void N7(View v) {
		insert("7");
	}

	public void N8(View v) {
		insert("8");
	}

	public void N9(View v) {
		insert("9");
	}
	
	public void point(View v) {
		insert(".");
	}
	private void perform() {
		// TODO Auto-generated method stub
		str = "";
		numtemp = num;
	}

	public void Euqal(View v) {
		calculate();
	}

	private void calculate() {
		// TODO Auto-generated method stub
		if (op == '+')
			num = numtemp + num;
		else if (op == '-')
			num = numtemp - num;
		else if (op == '/')
			num = numtemp / num;
		else if (op == '*')
			num = numtemp * num;
		editText_Display.setText("" + num);
	}

	public void Plus(View v) {
		perform();
		op = '+';
	}

	public void Minus(View v) {
		perform();
		op = '-';
	}

	public void Times(View v) {
		perform();
		op = '*';
	}

	public void Divide(View v) {
		perform();
		op = '/';
	}

	public void Clear(View v) {
		reset();
	}

	private void reset() {
		// TODO Auto-generated method stub
		str = "";
		op = 'q';
		num = 0;
		numtemp = 0;
		editText_Display.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}