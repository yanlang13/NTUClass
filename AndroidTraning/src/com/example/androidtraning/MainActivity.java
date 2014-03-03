package com.example.androidtraning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText = (EditText) findViewById(R.id.edit_message);
		KeyEnterCheck();

	}

	// �p�G�u��return super. ...�N���|��setting�F
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_search:
			openSearch();
			return true;
		case R.id.action_settings:
			openSettings();
			return true;
		case R.id.action_email:
			openEmail();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	//menu���\��
	public void openSearch(){
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		intent.putExtra(EXTRA_MESSAGE, "Searching...");
		startActivity(intent);
	}
	
	//menu���\��
	public void openSettings(){}
	
	//menu���\��
	public void openEmail(){
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		intent.putExtra(EXTRA_MESSAGE, "Sending E-mail...");
		startActivity(intent);
	}
	
	
	
	//  Called when the user clicks the Send button In order for the system to
	//  match this method to the method name given to android:onClick, the
	//  signature must be exactly as shown. Specifically, the method must: Be
	//  public Have a void return value Have a View as the only parameter (this
	//  will be the View that was clicked)
	// �e�X��J�ت����e
	public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		// �쪺�Oactivity_main����edit_message (activity_main�����e�|���gen/r.java��)
		// (EditText)�ΥH�T�O findViewById�����e�O�ŦXEditText���A
		String message = editText.getText().toString(); // ����J��
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

	// �Ӧ�SimpleUI����k�A��JENTER�ɤ]�nOUTPUT
	public void KeyEnterCheck() {
		// editText assign to ��J��
		editText.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View view, int keyCode, KeyEvent event) {
				Log.d("debug",
						"keyOcde =" + keyCode + "keyEvent=" + event.getAction());
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_ENTER) {
						sendMessage(view);
						return true;
					}
				}
				return false;
			}
		});
	}

}
