package com.example.simpleui;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText editText;
	private Button button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// R-> Gen/R.java(product from resource)
		// project clean �p�G�L�k�B�@���� (build automatically���}�~�|�P�B )
		setContentView(R.layout.activity_main);

		// ������ܼ�(�O�o�ncast�A�]��findViewById�O�Ǧ^View types)
		editText = (EditText) findViewById(R.id.editText1);
		editText.setHint("type something...");

		button1 = (Button) findViewById(R.id.button1);
		button1.setText("Send");
		
		editText.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				Log.d("debug",
						//���@��key�|�����keyEvent�A���O��down and up
						"keyOcde =" + keyCode + "keyEvent=" + event.getAction());
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_ENTER) {
						sendMessage();
						//return true��A�N���|�b����U�@�ӨB�J(�s�W�@��)
						return true;
					}
				}
				return false;
			}
		});

		// OnClickListener�ݭn��@�@��interface(�o��O�ΦW����)
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendMessage();
			}
		});
	}

	// method �����Opublic �M���JView
	// ������activity_main����android:onClick
	public void Submit2(View view) {
		sendMessage();
	}

	private void sendMessage() {
		String text = editText.getText().toString();
		editText.getText().clear();
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
