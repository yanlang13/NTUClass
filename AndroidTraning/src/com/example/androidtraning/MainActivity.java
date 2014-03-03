package com.example.androidtraning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	private EditText editText;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edit_message);
    	KeyEnterCheck();
 
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
    	//抓的是activity_main中的edit_message (activity_main的內容會轉到gen/r.java裡)
   	 	//(EditText)用以確保 findViewById的內容是符合EditText型態
    	String message = editText.getText().toString(); //取輸入值
    	intent.putExtra(EXTRA_MESSAGE, message);
   	 	startActivity(intent);
    }
    
	//來自SimpleUI的方法，輸入ENTER時也要OUTPUT 
    public void KeyEnterCheck(){
    	//editText assign to 輸入框
        editText.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View view, int keyCode, KeyEvent event) {
				Log.d("debug",
						"keyOcde =" + keyCode + "keyEvent=" + event.getAction());
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_ENTER) {
						sendMessage(view);
						return true;
					}
				}
				return false;
			}
		});
    }
    
}


