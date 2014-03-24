package com.example.examplelist;



import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class MainActivity extends ListActivity {
	
	public static class CustomArrayAdapter extends ArrayAdapter<ExampleDetails> {
		public CustomArrayAdapter(Context context, ExampleDetails[] examples){
			 super(context, R.layout.feature, R.id.title, examples);
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ListAdapter adapter = new CustomArrayAdapter(this, ExampleDetialsList.EXAMPLES);

        setListAdapter(adapter);
	}// end of onCreate()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}// end of onCreateOptionsMenu()

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}// end of OptionsItemSelected()
}// end of Activity{}