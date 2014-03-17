package com.example.simpleui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.security.auth.callback.Callback;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends Activity {
	private TextView textView;
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		textView = (TextView) findViewById(R.id.message);
		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle("loading");
		progressDialog.show();
		getData();
	}

	public void getData() {
		// 填目標class->message
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("message");
		// .findinBackgroud的話要實作一個callback
		// .find()這邊會停留住，因為要確保資料被抓回來。但這樣很容易讓使用者覺得卡卡的
		// 所以改用findinBackgroud

		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> messages, ParseException e) {
				String result = "";
				for (ParseObject message : messages) {
					// for each中 每一個message都是parse object
					// 所以要用getString (key);
					String text = message.getString("text");
					boolean isEncrypt = message.getBoolean("isEncrypt");
					result += text + "," + isEncrypt + "\n";
				}
				textView.setText(result);
				progressDialog.dismiss();
			}
		});
	}
}
