package com.example.androidtraning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    /** Called when the user clicks the Send button 
    In order for the system to match this method to the method name given to android:onClick, 
    the signature must be exactly as shown. Specifically, the method must:
        Be public
        Have a void return value
        Have a View as the only parameter (this will be the View that was clicked)
	*/
    public void sendMessage(View view){
    	 Intent intent = new Intent(this, DisplayMessageActivity.class);
    	 //�쪺�Oactivity_main����edit_message (activity_main�����e�|���gen/r.java��)
    	 //(EditText)�ΥH�T�O findViewById�����e�O�ŦXEditText���A
    	 EditText editText = (EditText) findViewById(R.id.edit_message);
    	 String message = editText.getText().toString(); //����J��
    	 intent.putExtra(EXTRA_MESSAGE, message);
    	 startActivity(intent);
    }
    
    
    
}


