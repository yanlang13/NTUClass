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
import android.app.ProgressDialog;
import android.os.Bundle;
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
	private NumberFormat nF; // ����g�n�ת��p���I

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

		//������listener
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				return false;
			}
		});// end of listView.setOnItemLongClickListener
	}// end of onCreate

	/*
	 * ���oparse�W�����
	 */
	private void getDataFromParse() {
		// ParseQuery �ϥ� subclass CameraSaveParse
		// getQuery Creates a new query for the given ParseObject subclass type.
		ParseQuery<CameraSaveParse> query = ParseQuery
				.getQuery(CameraSaveParse.class);
		query.findInBackground(new FindCallback<CameraSaveParse>() {
			@Override
			public void done(List<CameraSaveParse> results, ParseException e) {
				// �T�{Parse�S�����D
				if (e == null) {
					for (CameraSaveParse csp : results) {
						arrayListName.add(csp.getName());
						arrayListDesc.add(csp.getDesc());
						ParseGeoPoint pgp = new ParseGeoPoint();
						pgp = csp.getParseGeoPoint("ParseGeoPoint");

						arrayListLat.add(pgp.getLatitude());
						arrayListLng.add(pgp.getLongitude());

					}// end of for each
					ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
					for (int i = 0; i < arrayListName.size(); i++) {
						HashMap<String, Object> item = new HashMap<>();
						item.put("Name", arrayListName.get(i));
						item.put("Desc", arrayListDesc.get(i));
						item.put("Lat", nF.format(arrayListLat.get(i)));
						item.put("Lng", nF.format(arrayListLng.get(i)));
						list.add(item);
					}

					// �s�WSimpleAdapter
					String[] from = { "Name", "Desc", "Lat", "Lng" };
					int[] to = { R.id.list_name, R.id.list_desc, R.id.list_lat,
							R.id.list_lng };

					SimpleAdapter simpleAdapter;
					simpleAdapter = new SimpleAdapter(getApplication(), list,
							R.layout.camera_list_view, from, to);

					// ListActivity�]�wadapter
					listView.setAdapter(simpleAdapter);
				} else {
					Toast.makeText(getApplication(), e.toString(),
							Toast.LENGTH_LONG).show();
				}

				progressDialog.dismiss();
			}// end of done
		});// end of query.findInBackground
	}// end of getDataFromParse

}// end of CameraListActivity

