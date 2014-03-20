package com.example.push;

import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.os.Build;
import android.provider.Settings.Secure;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.PushService;

public class MainActivity extends Activity {
	private EditText editText;
	private Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editText = (EditText) findViewById(R.id.editText1);
		spinner = (Spinner) findViewById(R.id.spinner1);

		Parse.initialize(this, "3CNECEDI0tZiqskFsqFqLStJ5xEaTvZuYH2Ny6iX",
				"w09lwMCZQOR79X1EIpdrR7ZxmbZj6EENnxuQMVyD");
		PushService.setDefaultPushCallback(this, MainActivity.class);
		// channel�N���O�q�x�W�D���Pı�A�n���~�ব��push
		PushService.subscribe(this, "all", MainActivity.class);

		// �o��channel�u���@�x���
		PushService.subscribe(this, "device_id_" + getDeviceId(),
				MainActivity.class);

		// �NID�W�Ǩ�parse
		ParseObject object = new ParseObject("DeviceId");
		object.put("DeviceId", getDeviceId());
		object.saveInBackground();

		// spinner�ϥ�
		loadDeviceIds();
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

		// ���wid�ǰe
		String id = (String) spinner.getSelectedItem();

		ParsePush push = new ParsePush();
		// push�n���wchannel
		push.setChannel(id);
		push.setMessage(string);
		push.sendInBackground();
	}

	// ���oandroid system��DeviceId(�㦳�ߤ@��)
	private String getDeviceId() {
		return Secure.getString(getContentResolver(), Secure.ANDROID_ID);
	}

	// ��parse�W����DeviceId;
	private void loadDeviceIds() {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("DeviceId");
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> objects, ParseException e) {

				String[] ids = new String[objects.size()];
				for (int i = 0; i < objects.size(); i++) {
					ids[i] = objects.get(i).getString("deviceId");
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						MainActivity.this,
						android.R.layout.simple_spinner_item, ids);
				spinner.setAdapter(adapter);

			}
		});
	}
}