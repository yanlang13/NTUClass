package com.example.push;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.PushService;

public class MainActivity extends Activity {
	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editText = (EditText) findViewById(R.id.editText1);

		Parse.initialize(this, "3CNECEDI0tZiqskFsqFqLStJ5xEaTvZuYH2Ny6iX",
				"w09lwMCZQOR79X1EIpdrR7ZxmbZj6EENnxuQMVyD");
		PushService.setDefaultPushCallback(this, MainActivity.class);
		// channel就像是電台頻道的感覺，要有才能收到push
		PushService.subscribe(this, "all", MainActivity.class);
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

	public void click(View view) {
		String string = editText.getText().toString();

		ParsePush push = new ParsePush();
		// push要指定channel
		push.setChannel("all");
		push.setMessage(string);
		push.sendInBackground();
	}
}