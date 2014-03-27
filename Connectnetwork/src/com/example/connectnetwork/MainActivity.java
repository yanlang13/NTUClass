package com.example.connectnetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;
	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = (TextView) findViewById(R.id.textView1);
		editText = (EditText) findViewById(R.id.editText1);
	}

	// 連接網路後，fetch必須放到另外一個thread，這樣才不會crash (android 3.0以後的限制)
	public void fetch(View view) {

		// android 獨特的方式:把thread分解成三個部分(在task執行前、中、後)
		// 第一個啟動時輸入的參數，第二個可用來監控進度，第三個Type是指回傳的值
		AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
			String address = editText.getText().toString();
			final String urlStr = String
					.format("http://maps.googleapis.com/maps/api/geocode/json?address=%s&sensor=false",
							address);

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

	public void fetch2(View view) {
		final String urlStr = "http://tw.yahoo.com/";

		AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

			@Override
			protected void onPreExecute() {
			}

			@Override
			protected String doInBackground(Void... params) {
				// httpclient是指device
				DefaultHttpClient httpClient = new DefaultHttpClient();
				// httpget用以鎖定網址及後面的參數v=...&....
				HttpGet get = new HttpGet(urlStr);

				// 宣告要處理的東西(response handler)
				// httpclient.excute執行get，後面的response handler指的是處理方式(看取得的東西)
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				try {
					return httpClient.execute(get, responseHandler);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "";
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
