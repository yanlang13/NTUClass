package com.example.takephoto;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
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
		Parse.initialize(this, "3CNECEDI0tZiqskFsqFqLStJ5xEaTvZuYH2Ny6iX",
				"w09lwMCZQOR79X1EIpdrR7ZxmbZj6EENnxuQMVyD");
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
		// �T�{�^�Ӫ����e
		if (requestcode == REQUEST_CODE_TAKE_PHOTO) {
			// result_ok���F��^��
			if (resultcode == RESULT_OK) {
				// Bitmap (�����O��)
				Bitmap image = (Bitmap) data.getExtras().get("data");
				save(image);
				imageView.setImageBitmap(image);
			}
		}
	}

	// �g�J�ɮר�SD�d
	private void save(Bitmap bitmap) {
		// ���SD�d type�]�O�w�q�benv���A�o���쪺�O��Ƨ�(�i�ण�s�b)
		File imageDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

		// �T�{��Ƨ����A
		if (!imageDir.exists()) {
			imageDir.mkdir();
		}

		// ���X�ɮ�
		File imageFile = new File(imageDir, "photo.png");

		// �g�ɮסA�]�O��y�������AbufferOutput�|��fileoutput��
		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(imageFile));
			//bytearray�ΥH�x�s��parse
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 90, baos);
			baos.flush();
			baos.close();
			
			final ParseFile pfile = new ParseFile("photo.png", baos.toByteArray());
				pfile.saveInBackground(new SaveCallback() {
					@Override
					public void done(ParseException e) {
						Log.e("debug", pfile.getUrl());
					}
				});
			
			// compress�ഫ�Aformat �榡�Aquality 0-100,??)
			bitmap.compress(Bitmap.CompressFormat.PNG, 90, bos);
			Log.e("debug", "stroagepath= " + imageFile.getAbsolutePath());
			bos.flush();
			bos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
