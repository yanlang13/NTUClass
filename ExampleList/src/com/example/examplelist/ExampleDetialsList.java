package com.example.examplelist;

/**
 * A list of all the demos we have available.
 */
public final class ExampleDetialsList {

	/** This class should not be instantiated. */
	private ExampleDetialsList() {
	}

	public static final ExampleDetails[] EXAMPLES = { 
		new ExampleDetails(
			R.string.example1_label, R.string.example1_description,
			Example1Activity.class), };
} // end of ExampleDetialsList{}
