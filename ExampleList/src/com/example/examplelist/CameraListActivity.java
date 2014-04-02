package com.example.examplelist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.android.gms.drive.internal.l;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CameraListActivity extends Activity {
	private String information;
	private ArrayList<String> arrayListName;
	private ArrayList<String> arrayListDesc;
	private ArrayList<String> arrayListLat;
	private ArrayList<String> arrayListLng;
	private ProgressDialog progressDialog;
	private ListView listView;
	private TextView textView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cameralist);
		textView = (TextView) findViewById(R.id.textView11);
		listView = (ListView) findViewById(R.id.listView1);
		arrayListName = new ArrayList<String>();
		arrayListDesc = new ArrayList<String>();
		arrayListLat = new ArrayList<String>();
		arrayListLng = new ArrayList<String>();

		progressDialog = new ProgressDialog(this);
		progressDialog.show();
		getDataFromParse();

	}// end of onCreate

	private void getDataFromParse() {
		// ParseQuery 使用 subclass CameraSaveParse
		// getQuery Creates a new query for the given ParseObject subclass type.
		ParseQuery<CameraSaveParse> query = ParseQuery
				.getQuery(CameraSaveParse.class);
		query.findInBackground(new FindCallback<CameraSaveParse>() {
			@Override
			public void done(List<CameraSaveParse> results, ParseException e) {
				
				//確認Parse沒有問題
				if (e == null) {
					information = "";
					for (CameraSaveParse csp : results) {
						arrayListName.add(csp.getName());
						arrayListDesc.add(csp.getDesc());
						arrayListLat.add(csp.getLatitude());
						arrayListLng.add(csp.getLongtitude());
					}// end of for each
					ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
					for (int i = 0; i < arrayListName.size(); i++) {
						HashMap<String, Object> item = new HashMap<>();
						item.put("Name", arrayListName.get(i));
						item.put("Desc", arrayListDesc.get(i));
						item.put("Lat", arrayListLat.get(i));
						item.put("Lng", arrayListLng.get(i));
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
				}else{
					Toast.makeText(getApplication(), e.toString(), Toast.LENGTH_LONG).show();
				}
				progressDialog.dismiss();
			}// end of done
		});// end of query.findInBackground
	}// end of getDataFromParse
}// end of CameraListActivity

