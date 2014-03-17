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
		// ��ؼ�class->message
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("message");
		// .findinBackgroud���ܭn��@�@��callback
		// .find()�o��|���d��A�]���n�T�O��ƳQ��^�ӡC���o�˫ܮe�����ϥΪ�ı�o�d�d��
		// �ҥH���findinBackgroud

		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> messages, ParseException e) {
				String result = "";
				for (ParseObject message : messages) {
					// for each�� �C�@��message���Oparse object
					// �ҥH�n��getString (key);
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
