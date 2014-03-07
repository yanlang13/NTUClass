package com.example.mappingtravel;

import java.util.ArrayList;
import java.util.HashMap;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

//注意是 extends ListActivity 而非 activity

public class MainPhoto extends ActivityWithMenu {
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_photo);
		// 自定義的listAdapter
		PrUserListAdapter();
		
		setActionBarColor();
		
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	// 其他功能區開始==========================================================
	public void PrUserListAdapter() {
		ListView listView;
		SimpleAdapter adapter;

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String[] mPlaces = new String[] { "台北", "新北", "桃園", "新竹", "苗粟", };
		String[] mFoods = new String[] { "A", "B", "C", "D", "E", };
		String[] mRatings = new String[] { "1", "2", "3", "4", "5", };

		int[] mPics = new int[] { R.drawable.ic_red_number_1,
				R.drawable.ic_red_number_2, R.drawable.ic_red_number_3,
				R.drawable.ic_red_number_4, R.drawable.ic_red_number_5 };

		listView = (ListView) findViewById(R.id.listView1);

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
		adapter = new SimpleAdapter(this, list, R.layout.photo_format,
				new String[] { "pic", "food", "place", "rating" }, new int[] {
						R.id.imageView1, R.id.textView1, R.id.textView2,
						R.id.textView3 });

		// ListActivity設定adapter
		listView.setAdapter(adapter);

		// 啟用按鍵過濾功能
		listView.setTextFilterEnabled(true);
		// 點擊item會有所反應
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				int arg21 = arg2+1;
				Log.e("IamgePress", arg21+"");
			}
		});
	}
	// 其他功能區結束==========================================================
}
