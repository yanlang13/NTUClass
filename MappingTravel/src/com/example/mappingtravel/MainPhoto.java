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

//�`�N�O extends ListActivity �ӫD activity
public class MainPhoto extends ListActivity {
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarColor();
		// ����ϥέ����layout�A�ΤF�|�X�{���~
		// setContentView(R.layout.activity_main_photo);
		// ²��ListArray���(view)
		// PrSetListAdapter();

		// �۩w�q��listAdapter
		PrUserListAdapter();

		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	// menu�}�l==========================================================
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	// actionBar�W�����\��
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

	// menu���\��
	public void openSearch() {
	}

	// menu���\��
	public void openSettings() {
	}

	// menu���\��
	public void openEmail() {
	}

	// actionBar color setting
	public void setActionBarColor() {
		// �]��color�O�g�bres��Ƨ��U�A�ҥH�ϥ�Resources�ӥs�X�ӡC
		Resources res = getResources();
		int actionBarColor = res.getColor(R.color.actionBarColor);
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(actionBarColor));
	}

	// menu����==========================================================
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
		adapter = new SimpleAdapter(this, list, R.layout.activity_main_photo,
				new String[] { "pic", "food", "place", "rating" }, new int[] {
						R.id.imageView1, R.id.textView1, R.id.textView2,
						R.id.textView3 });

		// ListActivity�]�wadapter
		setListAdapter(adapter);

		// �ҥΫ���L�o�\��
		getListView().setTextFilterEnabled(true);
	}
}
// ��L�\��ϵ���==========================================================

