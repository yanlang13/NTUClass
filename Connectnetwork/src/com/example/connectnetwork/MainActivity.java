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
	private EditText editText;
	private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = (TextView) findViewById(R.id.textView1);
		editText = (EditText) findViewById(R.id.editText1);
		progress = new ProgressDialog(this);
	}

	// �s��������Afetch�������t�~�@��thread�A�o�ˤ~���|crash (android 3.0�H�᪺����)
	public void fetch(View view) {
		String address = "";
		// ����r��ݭnencoder�A�ҥ~�i��Oencoding�L�k�䴩
		try {
			address = URLEncoder.encode(editText.getText().toString(), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// ���google API�B�z
		final String urlStr = String
				.format("http://maps.googleapis.com/maps/api/geocode/json?address=%s&sensor=false",
						address);

		// android �W�S���覡:��thread���Ѧ��T�ӳ���(�btask����e�B���B��)
		// �Ĥ@�ӱҰʮɿ�J���ѼơA�ĤG�ӥi�ΨӺʱ��i�סA�ĤT��Type�O���^�Ǫ���
		AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
			// ���google API���o�g�n�׮y��

			@Override
			protected void onPreExecute() {
				// �إ߶i�װ��
				progress.setTitle("loading");
				progress.show();
			}

			@Override
			protected String doInBackground(Void... params) {
				try {
					// �T�{���}
					URL url = new URL(urlStr);
					URLConnection connection = url.openConnection();

					// Ū���}���e(��l�X)
					BufferedReader buffer = new BufferedReader(
							new InputStreamReader(connection.getInputStream()));

					// ��StringBuilder�[�ֳt��
					StringBuilder stringBuilder = new StringBuilder();
					String line;
					while ((line = buffer.readLine()) != null) {
						stringBuilder.append(line);
					}
					return stringBuilder.toString();
					// ���}���~��exception
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// �������D��exception
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// end of try
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				textView.setText(result);
				// �����i�װ��
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
				// httpclient�O��device
				DefaultHttpClient httpClient = new DefaultHttpClient();
				// httpget�ΥH��w���}�Ϋ᭱���Ѽ�v=...&....
				HttpGet get = new HttpGet(urlStr);

				// �ŧi�n�B�z���F��(response handler)
				// httpclient.excute����get�A�᭱��response handler�����O�B�z�覡(�ݨ��o���F��)
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
