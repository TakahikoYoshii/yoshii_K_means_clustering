package main;

import java.util.HashMap;

public class Vector {
	private HashMap<String, Integer> vector = new HashMap<String, Integer>();
	
	public void setXdata(int x) {
		vector.put("X", x);
	}

	public void setYdata(int y) {
		vector.put("Y", y);
	}

	public HashMap<String, Integer> getData() {
		return vector;
	}
}
