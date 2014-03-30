package com.example.examplelist;
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("CameraLocation")
public class CameraPositionParse extends ParseObject {

	public void setLatitude(double lat) {
		put("Latitude", lat);
	}// end of setLatitude

	public void setLongtitude(double lng) {
		put("Longtitude", lng);
	}// end of setLongtitude

	public double getLatitude() {
		return getDouble("Latitude");
	}// end of getLatitude

	public double getLongtitude() {
		return getDouble("Longtitude");
	}// end of getLongtitude
}// end of CameraPositionParse


