package com.example.connectnetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = (TextView) findViewById(R.id.textView1);

	}

	// 連接網路後，fetch必須放到另外一個thread，這樣才不會crash (android 3.0以後的限制)
	public void fetch(View view) {

		// android 獨特的方式:把thread分解成三個部分(在task執行前、中、後)
		//第二個可用來監控進度，第三個Type是指回傳的值
		AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
			String urlStr = "http://yahoo.com";
			@Override
			protected void onPreExecute() {
			}
			@Override
			protected String doInBackground(Void... params) {
				try {
					// 確認網址
					URL url = new URL(urlStr);
					URLConnection connection = url.openConnection();

					// 讀網址內容(原始碼)
					BufferedReader buffer = new BufferedReader(
							new InputStreamReader(connection.getInputStream()));

					// 用StringBuilder加快速度
					StringBuilder stringBuilder = new StringBuilder();
					String line;
					while ((line = buffer.readLine()) != null) {
						stringBuilder.append(line);
					}
					return stringBuilder.toString();
					// 網址錯誤的exception
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// 網頁問題的exception
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// end of try
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				textView.setText(result);
			}
		};
		task.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
