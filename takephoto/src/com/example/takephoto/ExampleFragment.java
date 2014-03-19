package com.example.takephoto;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExampleFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		inflater.getClass();
		  // Inflate the layout for this fragment,true=>layout¬Ocontainerªºchild
		return  inflater.inflate(R.layout.fragment_main, container, false);
	}
}
