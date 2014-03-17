package com.example.mappingtravel;


import java.security.PublicKey;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActivityWithMenu {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setActionBarColor();
		
	}

	// ���}�l=========================================================
	
	//intentŪthis�A�ҥH�n�ǤJview
	public void MtTravelList(View view) {
		Intent intent = new Intent(this, MainTravelList.class);
		startActivity(intent);
	}
	
	
	
	public void MtLocation(View view) {
		// ��ܶZ��layout��ɪ��\��
		PrButtonPosition();
	}

	public void MtPhoto(View view) {
		Intent intent = new Intent(this, MainPhoto.class);
		startActivity(intent);
	}
	
	public void MtNewTravel(View view){
		Intent intent = new Intent(this, MainNewTravel.class);
		startActivity(intent);
	}
	
		/*
		 * //intent(�N��)�O���{�ǥh�I�s�t�~�@�ӵ{�ǡA���h��k���@�C Intent intent = new Intent();
		 * intent.setClass(this, MessageActivity.class); startActivity(intent);
		 */

	public void MtImformation(View view) {
	}

	// ��浲��=========================================================

	// �䥦�\��϶}�l========================================================
	public void PrButtonPosition() {
		// �ثe�O��ܶZ��layout��ɪ��\��
		Button lButton = (Button) findViewById(R.id.button_Location);
		Integer left = lButton.getLeft();
		Integer top = lButton.getTop();
		String s = "left distance: " + left.toString() + ", Top distance: "
				+ top.toString();
		Toast.makeText(this, s, Toast.LENGTH_LONG).show();
	}

	// �䥦�\��϶}�l========================================================
}
