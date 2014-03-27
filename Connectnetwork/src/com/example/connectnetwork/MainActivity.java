package com.example.connectnetwork;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	//先連接網路
	public void fetch(View view){
		String urlString = "http://yahoo.com";
		//1. java內建
		try {
			//確認url的正確性
			URL url = new URL(urlString);
			//開啟連線，但也會有例外
			URLConnection connection = url.openConnection();
			//讀網頁(同讀檔的概念)
			BufferedReader buffer = new BufferedReader(new InputStreamReader());
			
			//一次讀一行，讀到檔案為空
			
			//result儲存讀取的結果，再放到textview
			
		} catch (Exception e) { // url的正確性
			// TODO: handle exception
		}catch (){
			
		}
		//2.aptch提供
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
