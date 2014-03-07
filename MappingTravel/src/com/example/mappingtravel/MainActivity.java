package com.example.mappingtravel;

import android.os.Bundle;
import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	public void MtTravel(View view) {
		Intent intent = new Intent(this, MainTravelMap.class);
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
