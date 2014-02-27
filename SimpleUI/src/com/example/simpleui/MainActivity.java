package com.example.simpleui;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private EditText editText;
	private Button button1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//R-> Gen/R.java(product from resource)
		//project clean �p�G�L�k�B�@���� (build automatically���}�~�|�P�B )
		setContentView(R.layout.activity_main);
		
		//������ܼ�(�O�o�ncast�A�]��findViewById�O�Ǧ^View types)
		editText = (EditText) findViewById(R.id.editText1);
		editText.setHint("type something...");
		
		button1 = (Button) findViewById(R.id.button1);
		button1.setText("Send");
		
		//OnClickListener�ݭn��@�@��interface(�o��O�ΦW����)
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = editText.getText().toString();
				//debug�\��A�|�X�{�bDDMS����LogCat��
				Log.d("debug", "[button1]" + text);
				editText.getText().clear();
			}
		});
	}
	//method �����Opublic �M���JView
	//������activity_main����android:onClick
	public void Submit2(View view){
		String text = editText.getText().toString();
		Log.d("debug", "[button2]" + text);
		editText.getText().clear();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
