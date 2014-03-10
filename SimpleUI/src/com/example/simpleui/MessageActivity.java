package com.example.simpleui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends Activity {
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);

		// getIntent就是拿到MainActivity的，所以拿的是key(兩者都是String name)
		String text = getIntent().getStringExtra("text");
		writeFile(text);

		textView = (TextView) findViewById(R.id.message);
		textView.setText(readFile());

	}

	// 寫一個文字檔，再讀出來(串流-水管說)
	private void writeFile(String text) {
		// 隔開資料，方便確認。
		text += "\n";
		
		try {
			// mode是指寫檔方式。。。存檔位置 手機沒root就看不到。 package-data-data-files
			FileOutputStream fos = openFileOutput("history.txt",
					Context.MODE_APPEND);
			// write有三種，這邊使用一個byte array (string to byte array)
			fos.write(text.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String readFile() {
		// 提高buffer，要確定量小於這個。一般用while做到沒有值為止。
		try {
			FileInputStream fis = openFileInput("history.txt");
			byte[] buffer = new byte[1024];
			fis.read(buffer);
			return new String(buffer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
