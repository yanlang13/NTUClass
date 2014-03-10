package com.example.simpleui;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText editText;
	private Button button1;
	private CheckBox isEncrypt;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// R-> Gen/R.java(product from resource)
		// project clean �p�G�L�k�B�@���� (build automatically���}�~�|�P�B )
		setContentView(R.layout.activity_main);

		// �x�s���(�h�ƫ��A���i)�A���]�w�CEX:���n�_�ʵ���
		// sharedPreferences�O���Ƽg��package�U��shared_prefs(����ĳ�s�o�̡A�M�I�A�Ĳv�t)
		sp = getSharedPreferences("settings", Context.MODE_PRIVATE);

		// ������ܼ�(�O�o�ncast�A�]��findViewById�O�Ǧ^View types)
		editText = (EditText) findViewById(R.id.editText1);
		editText.setHint("type something...");

		button1 = (Button) findViewById(R.id.button1);
		button1.setText("Send");

		editText.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				/*
				 * onkey�ɡA�N�O�n��o�Ȧs���(�g�J) All changes you make in an editor are
				 * batched, and not copied back to the original
				 * SharedPreferences until you call commit() or apply()
				 */
				Editor editor = sp.edit();
				editor.putString("text", editText.getText().toString());
				// commit�|�@����ʩҦ���editor���e
				editor.commit();

				Log.d("debug",
				// ���@��key�|�����keyEvent�A���O��down and up
						"keyOcde =" + keyCode + "keyEvent=" + event.getAction());
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_ENTER) {
						sendMessage();
						// return true��A�N���|�b����U�@�ӨB�J(�s�W�@��)
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

		// ���^�Ȧs�����
		editText.setText(sp.getString("text", ""));

		// checkbox���Ȧs
		isEncrypt = (CheckBox) findViewById(R.id.checkBox1);
		isEncrypt.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Editor editor = sp.edit();
				editor.putBoolean("isEncrypt", false);
				editor.commit();
			}
		});
		isEncrypt.setChecked(sp.getBoolean("isEncrypt", false));
	}

	// method �����Opublic �M���JView
	// ������activity_main����android:onClick
	public void Submit2(View view) {
		sendMessage();
	}

	private void sendMessage() {
		String text = editText.getText().toString();
		
		if (isEncrypt.isChecked()) {
			text = "*****";
		}

		editText.getText().clear();
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();

		// intent(�N��)�O���{�ǥh�I�s�t�~�@�ӵ{�ǡA���h��k���@�C
		Intent intent = new Intent();
		intent.setClass(this, MessageActivity.class);
		// putExtra Key, Value�������Aextra���N��N�O�����v�Tintent��call
		intent.putExtra("text", text);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
