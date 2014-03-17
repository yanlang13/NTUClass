package com.example.takephoto;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
			// 開啟相機，叫起外部應用程式
			Intent intent = new Intent();
			// setAction未必是開啟activity
			intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	// onactivityresult需要跟startactivityresult連動(出去後會回傳值)
	// 需要一個request code來確保有接到。
	// 第一個值確認是誰回來
	protected void onActivityResult(int requestcode, int resultcode, Intent data) {
		super.onActivityResult(requestcode, resultcode, data);
		// 確認回來的內容
		if (requestcode == REQUEST_CODE_TAKE_PHOTO) {
			// result_ok有東西回來
			if (resultcode == RESULT_OK) {
				// Bitmap (指的是圖)
				Bitmap image = (Bitmap) data.getExtras().get("data");
				save(image);
				imageView.setImageBitmap(image);
			}
		}
	}

	// 寫入檔案到SD卡
	private void save(Bitmap bitmap) {
		// 抓到SD卡 type也是定義在env中，這邊抓到的是資料夾(可能不存在)
		File imageDir = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

		// 確認資料夾狀態
		if (imageDir.exists()) {
			imageDir.mkdir();
		}

		// 做出檔案
		File imageFile = new File(imageDir, "photo.png");

		// 寫檔案，也是串流的概念，bufferOutput會比fileoutput快
		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(imageFile));
			// compress轉換，format 格式，quality 0-100,??)
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
