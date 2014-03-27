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

	// �s��������Afetch�������t�~�@��thread�A�o�ˤ~���|crash (android 3.0�H�᪺����)
	public void fetch(View view) {

		// android �W�S���覡:��thread���Ѧ��T�ӳ���(�btask����e�B���B��)
		//�ĤG�ӥi�ΨӺʱ��i�סA�ĤT��Type�O���^�Ǫ���
		AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
			String urlStr = "http://yahoo.com";
			@Override
			protected void onPreExecute() {
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
