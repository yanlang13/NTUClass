package com.example.mappingtravel;
import android.app.Application;
import android.content.Context;

public class App extends Application {

	private static Context mContext;

	public static Context getContext() {
		return mContext;
	}

	public static void setContext(Context nContext) {
		mContext = nContext;
	}
}

//To use this class do:
//public class App extends Application implements OnInitListener {
//
//    private static Context mContext;
//
//    public static Context getContext() {
//        return mContext;
//    }
//
//    public static void setContext(Context mContext) {
//        this.mContext = mContext;
//    }
//
//    ...
//
//}
//
//In your manifest:
//<application
//        android:icon="..."
//        android:label="..."
//        android:name="com.example.yourmainpackagename.App" >
//                       class that extends Application ^^^
//
//In Activity B:
//public class B extends Activity {
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.sampleactivitylayout);
//
//        App.setContext(this);
//                  ...
//        }
//...
//}
//
//In class A:
//Context c = App.getContext();
