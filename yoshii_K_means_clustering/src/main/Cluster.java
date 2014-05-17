package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Cluster {
	private ArrayList<Vector> vectorList = new ArrayList<Vector>();
	private HashMap<String, BigDecimal> centralPoint = new HashMap<String,BigDecimal>();

	public void setVector(Vector vector) {
		this.vectorList.add(vector);
	}

	public ArrayList<Vector> getVectorList() {
		return this.vectorList;
	}

	public HashMap<String, BigDecimal> getCentralPoint() {
		return this.centralPoint;
	}

	public void setCentralPoint() {
		BigDecimal x = new BigDecimal(0);
		BigDecimal y = new BigDecimal(0);
		for(Vector vector:vectorList){
			x = x.add(new BigDecimal(vector.getData().get("X")));
			y = y.add(new BigDecimal(vector.getData().get("Y")));
		}
		BigDecimal size = new BigDecimal(vectorList.size());
		x = x.divide(size);
		y = y.divide(size);
		centralPoint.put("X", x);
		centralPoint.put("Y", y);
	}

	public void clean() {
		vectorList.clear();
	}
}
