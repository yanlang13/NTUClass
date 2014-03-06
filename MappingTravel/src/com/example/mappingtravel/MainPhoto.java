package com.example.mappingtravel;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

//注意是 extends ListActivity 而非 activity
public class MainPhoto extends ListActivity {
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarColor();
		// 不能使用原先的layout，用了會出現錯誤
		// setContentView(R.layout.activity_main_photo);
		// 簡易ListArray選單(view)
		// PrSetListAdapter();

		// 自定義的listAdapter
		PrUserListAdapter();

		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	// menu開始==========================================================
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// actionBar上面的功能
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_search:
			openSearch();
			return true;
		case R.id.action_settings:
			openSettings();
			return true;
		case R.id.action_email:
			openEmail();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// menu的功能
	public void openSearch() {
	}

	// menu的功能
	public void openSettings() {
	}

	// menu的功能
	public void openEmail() {
	}

	// actionBar color setting
	public void setActionBarColor() {
		// 因為color是寫在res資料夾下，所以使用Resources來叫出來。
		Resources res = getResources();
		int actionBarColor = res.getColor(R.color.actionBarColor);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(actionBarColor));
	}

	// menu結束==========================================================
	// 其他功能區開始==========================================================
	public void PrSetListAdapter() {
		String[] mStrings = new String[] { "大餅包小餅", "蚵仔煎", "東山鴨頭", "臭豆腐" };
		// 注意：不能使用main中的layout，用了會出現錯誤
		// setContentView(R.layout.main);

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mStrings));

		// 啟用按鍵過濾功能，可以篩選名稱
		getListView().setTextFilterEnabled(true);
	}

	public void PrUserListAdapter() {
		// 圖片需要先放到res/drawable-xxxx目錄中（這裡只有放到res/drawable-hdpi中），
		// 抓取圖片用R.drawable.pic，不過因為HashMap的value部份需要用圖片（R.drawable.pic），
		// 是一個int的型態，所以HashMap的value部份需要改為Object，才能容得下int和string的類型。
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String[] mPlaces = new String[] { "台北市", "新北市", "台南市", "高雄市", "苗粟縣", };

		String[] mFoods = new String[] { "大餅包小餅", "蚵仔煎", "東山鴨頭", "臭豆腐", "潤餅", };

		String[] mRatings = new String[] { "1", "5", "2", "4", "3", };
		int[] mPics = new int[] { R.drawable.ic_red_number_1,
				R.drawable.ic_red_number_2, R.drawable.ic_red_number_3,
				R.drawable.ic_red_number_4, R.drawable.ic_red_number_5 };

		SimpleAdapter adapter;

		// 把資料加入ArrayList中
		for (int i = 0; i < mPlaces.length; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("pic", mPics[i]);
			item.put("food", mFoods[i]);
			item.put("place", "地點：" + mPlaces[i]);
			item.put("rating", "評分：" + mRatings[i] + " 星");
			list.add(item);
		}

		// 新增SimpleAdapter
		adapter = new SimpleAdapter(this, list, R.layout.activity_main_photo,
				new String[] { "pic", "food", "place", "rating" }, new int[] {
						R.id.imageView1, R.id.textView1, R.id.textView2,
						R.id.textView3 });

		// ListActivity設定adapter
		setListAdapter(adapter);

		// 啟用按鍵過濾功能
		getListView().setTextFilterEnabled(true);
	}
}
// 其他功能區結束==========================================================

