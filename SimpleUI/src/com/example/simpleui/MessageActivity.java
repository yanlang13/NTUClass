package com.example.simpleui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends Activity {
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
	
		// getIntent�N�O����MainActivity���A�ҥH�����Okey(��̳��OString name)
		String text = getIntent().getStringExtra("text");
		textView = (TextView) findViewById(R.id.message);
		textView.setText(text);
	}
}
