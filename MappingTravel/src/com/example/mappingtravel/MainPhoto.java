package com.example.mappingtravel;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

//�`�N�O extends ListActivity �ӫD activity

public class MainPhoto extends ListActivity{
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����ϥέ����layout�A�ΤF�|�X�{���~
		// setContentView(R.layout.activity_main_photo);
		// ²��ListArray���(view)
//		 PrSetListAdapter();

		// �۩w�q��listAdapter
		PrUserListAdapter();
		
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	// ��L�\��϶}�l==========================================================
	public void PrSetListAdapter() {
		String[] mStrings = new String[] { "�j��]�p��", "�H�J��", "�F�s�n�Y", "�䨧�G" };
		// �`�N�G����ϥ�main����layout�A�ΤF�|�X�{���~
		// setContentView(R.layout.main);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mStrings));

		// �ҥΫ���L�o�\��A�i�H�z��W��
		getListView().setTextFilterEnabled(true);
	}

	public void PrUserListAdapter() {
		// �Ϥ��ݭn�����res/drawable-xxxx�ؿ����]�o�̥u�����res/drawable-hdpi���^�A
		// ����Ϥ���R.drawable.pic�A���L�]��HashMap��value�����ݭn�ιϤ��]R.drawable.pic�^�A
		// �O�@��int�����A�A�ҥHHashMap��value�����ݭn�אּObject�A�~��e�o�Uint�Mstring�������C
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String[] mPlaces = new String[] { "�x�_��", "�s�_��", "�x�n��", "������", "�]����", };

		String[] mFoods = new String[] { "�j��]�p��", "�H�J��", "�F�s�n�Y", "�䨧�G", "���", };

		String[] mRatings = new String[] { "1", "5", "2", "4", "3", };
		int[] mPics = new int[] { R.drawable.ic_red_number_1,
				R.drawable.ic_red_number_2, R.drawable.ic_red_number_3,
				R.drawable.ic_red_number_4, R.drawable.ic_red_number_5 };
		
		String[] mtoast = new String[] { "z", "z", "z", "z", "z", };
		SimpleAdapter adapter;

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
		adapter = new SimpleAdapter(this, list, R.layout.photo_format,
				new String[] { "pic", "food", "place", "rating" }, new int[] {
						R.id.imageView1, R.id.textView1, R.id.textView2,
						R.id.textView3 });

		// ListActivity�]�wadapter
		setListAdapter(adapter);

		// �ҥΫ���L�o�\��
		getListView().setTextFilterEnabled(true);
		
		//�I��item�|���Ҥ���
		getListView().setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.e("test", ""+arg2);
			}
			
		});
		
	}
}
// ��L�\��ϵ���==========================================================

