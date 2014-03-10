package com.example.mappingtravel;

import java.util.ArrayList;
import java.util.HashMap;
import android.os.Build;
import android.os.Bundle;
import android.R.integer;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

//�`�N�O extends ListActivity �ӫD activity

public class MainPhoto extends ActivityWithMenu {
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_photo);
		
		// �۩w�q��listAdapter
		PrUserListAdapter();
		
		setActionBarColor();
		
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	// ��L�\��϶}�l==========================================================
	public void PrUserListAdapter() {
		ListView listView;
		SimpleAdapter adapter;

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String[] mPlaces = new String[] { "�x�_", "�s�_", "���", "�s��", "�]��", };
		String[] mFoods = new String[] { "A", "B", "C", "D", "E", };
		String[] mRatings = new String[] { "1", "2", "3", "4", "5", };

		int[] mPics = new int[] { R.drawable.ic_red_number_1,
				R.drawable.ic_red_number_2, R.drawable.ic_red_number_3,
				R.drawable.ic_red_number_4, R.drawable.ic_red_number_5 };

		listView = (ListView) findViewById(R.id.listView1);

		// ���ƥ[�JArrayList��
		for (int i = 0; i < mPlaces.length; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("pic", mPics[i]);
			item.put("food", mFoods[i]);
			item.put("place", "�a�I�G" + mPlaces[i]);
			item.put("rating", "�����G" + mRatings[i] + " �P");
			list.add(item);
		}

		// �s�WSimpleAdapter
		String [] from = { "pic", "food", "place", "rating" };
		int [] to = {R.id.imageView1, R.id.textView1, R.id.textView2,
				R.id.textView3 };
		
		adapter = new SimpleAdapter(this, list, R.layout.photo_format,
				from, to);

		// ListActivity�]�wadapter
		listView.setAdapter(adapter);

		// �ҥΫ���L�o�\��
		listView.setTextFilterEnabled(true);
		// �I��item�|���Ҥ���
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				int arg21 = arg2+1;
				Log.e("IamgePress", arg21+"");
			}
		});
	}
	// ��L�\��ϵ���==========================================================
}
