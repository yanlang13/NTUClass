package com.example.simpleui;

import javax.security.auth.callback.Callback;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import com.example.simpleui.R.id;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class MainActivity extends Activity {

	private EditText editText;
	private Button button1;
	private CheckBox isEncrypt;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Parse.initialize(this, "3CNECEDI0tZiqskFsqFqLStJ5xEaTvZuYH2Ny6iX",
				"w09lwMCZQOR79X1EIpdrR7ZxmbZj6EENnxuQMVyD");

		super.onCreate(savedInstanceState);
		// R-> Gen/R.java(product from resource)
		// project clean 如果無法運作的話 (build automatically打開才會同步 )
		setContentView(R.layout.activity_main);

		// 儲存資料(多數型態都可)，偏設定。EX:不要震動等等
		// sharedPreferences是把資料寫到package下的shared_prefs(不建議存這裡，危險，效率差)
		sp = getSharedPreferences("settings", Context.MODE_PRIVATE);

		// 實體化變數(記得要cast，因為findViewById是傳回View types)
		editText = (EditText) findViewById(R.id.editText1);
		editText.setHint("type something...");

		button1 = (Button) findViewById(R.id.button1);
		button1.setText("Send");

		editText.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				/*
				 * onkey時，就是要抓得暫存資料(寫入) All changes you make in an editor are
				 * batched, and not copied back to the original
				 * SharedPreferences until you call commit() or apply()
				 */
				Editor editor = sp.edit();
				editor.putString("text", editText.getText().toString());
				// commit會一次更動所有的editor內容
				editor.commit();

				Log.d("debug",
				// 按一次key會有兩個keyEvent，分別為down and up
						"keyOcde =" + keyCode + "keyEvent=" + event.getAction());
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_ENTER) {
						sendMessage();
						// return true後，就不會在執行下一個步驟(新增一行)
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

		// 取回暫存的資料
		editText.setText(sp.getString("text", ""));

		// checkbox的暫存
		isEncrypt = (CheckBox) findViewById(R.id.checkBox1);
		isEncrypt.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Editor editor = sp.edit();
				editor.putBoolean("isEncrypt", false);
				editor.commit();
			}
		});
		isEncrypt.setChecked(sp.getBoolean("isEncrypt", false));
	}

	// method 必須是public 然後丟入View
	// 對應到activity_main中的android:onClick
	public void Submit2(View view) {
		sendMessage();
	}

	private void sendMessage() {
		String text = editText.getText().toString();
		
		//存到Parse上
		ParseObject messageObject = new ParseObject("message");
		messageObject.put("text", text);
		messageObject.put("isEncrypt", isEncrypt.isChecked());
		//inBackground()指在背景執行，所以method後續也會繼續執行。
//		messageObject.saveInBackground();
		
		//savecallback當什麼事情發生後，才執行。
		messageObject.saveInBackground(new SaveCallback() {
			@Override
			public void done(ParseException e) {
				if(e == null){
					Log.e("debug", "done");
				}else{
					Log.e("debug","error");
				}
			}
		});
		
		
		Log.d("debug", "after saveInbackground");
		//避免這個問題的話，方法一(速度會變慢，0.x秒會讓使用者有感覺。)。
		//多數都用saveInBackground();
//		try {
//			messageObject.save();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		
		
		if (isEncrypt.isChecked()) {
			text = "*****";
		}

		editText.getText().clear();
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();

		// intent(意圖)是指程序去呼叫另外一個程序，眾多方法之一。
		Intent intent = new Intent();
		intent.setClass(this, MessageActivity.class);
		// putExtra Key, Value的概念，extra的意思就是指不影響intent的call
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
