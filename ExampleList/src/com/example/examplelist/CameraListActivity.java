package com.example.examplelist;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseQuery;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.StaticLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CameraListActivity extends Activity {
	private ArrayList<String> arrayListName;
	private ArrayList<String> arrayListDesc;
	private ArrayList<Double> arrayListLat;
	private ArrayList<Double> arrayListLng;
	private ProgressDialog progressDialog;
	private ListView listView;
	private TextView textView;
	private NumberFormat nF; // 控制經緯度的小數點

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cameralist);
		textView = (TextView) findViewById(R.id.textView11);
		listView = (ListView) findViewById(R.id.listView1);
		arrayListName = new ArrayList<String>();
		arrayListDesc = new ArrayList<String>();
		arrayListLat = new ArrayList<Double>();
		arrayListLng = new ArrayList<Double>();
		nF = new DecimalFormat("#.###");
		progressDialog = new ProgressDialog(this);

		progressDialog.show();
		getDataFromParse();
		setUpAllListener();

	}// end of onCreate

	// 所有的listener
	private void setUpAllListener() { // call from onCreate
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				setAlertDialog().show();
				return false;
			}//end of onItemLongClick
		});// end of listView.setOnItemLongClickListener
	}// end of setUpAllListener()
	
	private AlertDialog setAlertDialog(){// call from setUpAllListener()
		AlertDialog.Builder aDialogBuilder = new AlertDialog.Builder(
				CameraListActivity.this);
//		LayoutInflater inflater = CameraListActivity.this.getLayoutInflater();
//		View dialogView = inflater.inflate(R.layout.camera_list_dialog, null);
//		aDialogBuilder.setView(dialogView);
		
		final String editSelect [] = new String[]{"Modify", "Delete"};
		aDialogBuilder.setItems(editSelect , new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(CameraListActivity.this, editSelect[which], Toast.LENGTH_SHORT).show();
			}
		});
		
		return aDialogBuilder.create();
		
	}// end of setAlertDialog()
	
	
	// {{getDataFromParse()取得parse資料，setUpListView()放到lsit
	// 取得parse上的資料
	private void getDataFromParse() { // call from onCreate
		// ParseQuery 使用 subclass CameraSaveParse
		// getQuery Creates a new query for the given ParseObject subclass type.
		ParseQuery<CameraSaveParse> query = ParseQuery
				.getQuery(CameraSaveParse.class);
		query.findInBackground(new FindCallback<CameraSaveParse>() {
			@Override
			public void done(List<CameraSaveParse> results, ParseException e) {
				// 確認Parse沒有問題
				if (e == null) {
					for (CameraSaveParse csp : results) {
						arrayListName.add(csp.getName());
						arrayListDesc.add(csp.getDesc());
						ParseGeoPoint pgp = new ParseGeoPoint();
						pgp = csp.getParseGeoPoint("ParseGeoPoint");
						arrayListLat.add(pgp.getLatitude());
						arrayListLng.add(pgp.getLongitude());
					}// end of for each
					setUpListView();
				} else {
					Toast.makeText(getApplication(), e.toString(),
							Toast.LENGTH_LONG).show();
				}

				progressDialog.dismiss();
			}// end of done
		});// end of query.findInBackground
	}// end of getDataFromParse

	// 將parse資料放到listView
	private void setUpListView() { // call from getDataFromParse()
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < arrayListName.size(); i++) {
			HashMap<String, Object> item = new HashMap<>();
			item.put("Name", "Name: " + arrayListName.get(i));
			item.put("Desc", "Description: \n" + arrayListDesc.get(i));
			item.put("Lat", "Latitude:" + nF.format(arrayListLat.get(i)));
			item.put("Lng", "Longtitude:" + nF.format(arrayListLng.get(i)));
			list.add(item);
		}

		// 新增SimpleAdapter
		String[] from = { "Name", "Desc", "Lat", "Lng" };
		int[] to = { R.id.list_name, R.id.list_desc, R.id.list_lat,
				R.id.list_lng };

		SimpleAdapter simpleAdapter;
		simpleAdapter = new SimpleAdapter(getApplication(), list,
				R.layout.camera_list_view, from, to);

		// ListActivity設定adapter
		listView.setAdapter(simpleAdapter);
	}// end of setListView
		// }}
}// end of CameraListActivity

