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
	//���s������
	public void fetch(View view){
		String urlString = "http://yahoo.com";
		//1. java����
		try {
			//�T�{url�����T��
			URL url = new URL(urlString);
			//�}�ҳs�u�A���]�|���ҥ~
			URLConnection connection = url.openConnection();
			//Ū����(�PŪ�ɪ�����)
			BufferedReader buffer = new BufferedReader(new InputStreamReader());
			
			//�@��Ū�@��AŪ���ɮ׬���
			
			//result�x�sŪ�������G�A�A���textview
			
		} catch (Exception e) { // url�����T��
			// TODO: handle exception
		}catch (){
			
		}
		//2.aptch����
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
