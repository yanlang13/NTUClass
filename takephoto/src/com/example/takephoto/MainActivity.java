package com.example.takephoto;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

	private ImageView imageView;
	private static final int REQUEST_CODE_TAKE_PHOTO = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView) findViewById(R.id.imageView1);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Log.d("debug", "settings");
			return true;
		case R.id.action_takephoto:
			// �}�Ҭ۾��A�s�_�~�����ε{��
			Intent intent = new Intent();
			// setAction�����O�}��activity
			intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	// onactivityresult�ݭn��startactivityresult�s��(�X�h��|�^�ǭ�)
	// �ݭn�@��request code�ӽT�O������C
	// �Ĥ@�ӭȽT�{�O�֦^��
	protected void onActivityResult(int requestcode, int resultcode, Intent data) {
		super.onActivityResult(requestcode, resultcode, data);
		if (requestcode == REQUEST_CODE_TAKE_PHOTO) {
			if (resultcode == RESULT_OK) {
				Bitmap image = (Bitmap) data.getExtras().get("data");
				imageView.setImageBitmap(image);
			}
		}
	}

	// �T�{�^�Ӫ����e
	// result_ok���F��^��

	// Bitmap (�����O��)

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
