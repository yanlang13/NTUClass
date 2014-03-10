package com.example.simpleui;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText editText;
	private Button button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// R-> Gen/R.java(product from resource)
		// project clean 如果無法運作的話 (build automatically打開才會同步 )
		setContentView(R.layout.activity_main);

		// 實體化變數(記得要cast，因為findViewById是傳回View types)
		editText = (EditText) findViewById(R.id.editText1);
		editText.setHint("type something...");
		
		button1 = (Button) findViewById(R.id.button1);
		button1.setText("Send");
		
		editText.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				Log.d("debug",
						//按一次key會有兩個keyEvent，分別為down and up
						"keyOcde =" + keyCode + "keyEvent=" + event.getAction());
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_ENTER) {
						sendMessage();
						//return true後，就不會在執行下一個步驟(新增一行)
						return true;
					}
				}
				return false;
			}
		});

		// OnClickListener需要實作一個interface(這邊是匿名實體)
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendMessage();
			}
		});
	}

	// method 必須是public 然後丟入View
	// 對應到activity_main中的android:onClick
	public void Submit2(View view) {
		sendMessage();
	}

	private void sendMessage() {
		String text = editText.getText().toString();
		editText.getText().clear();
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
		
		//intent(意圖)是指程序去呼叫另外一個程序，眾多方法之一。
		Intent intent = new Intent();
		intent.setClass(this, MessageActivity.class);
		//putExtra Key, Value的概念，extra的意思就是指不影響intent的call
		intent.putExtra("text", text);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
