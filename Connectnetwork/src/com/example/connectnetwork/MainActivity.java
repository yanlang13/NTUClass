package com.example.connectnetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView;
	private TextView latTextView;
	private TextView lngTextView;
	private EditText editText;
	private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = (TextView) findViewById(R.id.textView1);
		editText = (EditText) findViewById(R.id.editText1);
		progress = new ProgressDialog(this);

		latTextView = (TextView) findViewById(R.id.textView2);
		latTextView = (TextView) findViewById(R.id.textView3);
	}

	// 連接網路後，fetch必須放到另外一個thread，這樣才不會crash (android 3.0以後的限制)
	public void fetch(View view) {
		String address = "";
		// 中文字串需要encoder，例外可能是encoding無法支援
		try {
			address = URLEncoder.encode(editText.getText().toString(), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// 丟到google API處理
		final String urlStr = String
				.format("http://maps.googleapis.com/maps/api/geocode/json?address=%s&sensor=false",
						address);

		// android 獨特的方式:把thread分解成三個部分(在task執行前、中、後)
		// 第一個啟動時輸入的參數，第二個可用來監控進度，第三個Type是指回傳的值
		AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
			// 丟到google API取得經緯度座標

			@Override
			protected void onPreExecute() {
				// 建立進度圈圈
				progress.setTitle("loading");
				progress.show();
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
				// 解析google mpa api json的結果
				// {}是object, key-value的概念
				try {
					//取得項目
					JSONObject data = new JSONObject(result);
					JSONObject location = data.getJSONArray("results")
					.getJSONObject(0).getJSONObject("geometry")
					.getJSONObject("location");
					double lat = location.getDouble("lat");
					double lng = location.getDouble("lng");

					latTextView.setText("lat:" + lat);
					lngTextView.setText("lng:" + lng);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				textView.setText(result);
				// 結束進度圈圈
				progress.dismiss();
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
