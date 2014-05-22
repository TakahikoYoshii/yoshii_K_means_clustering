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
		if(vectorList.size() != 0){
		BigDecimal size = new BigDecimal(vectorList.size());
		x = x.divide(size, 2, BigDecimal.ROUND_HALF_UP);
		y = y.divide(size, 2, BigDecimal.ROUND_HALF_UP);
		centralPoint.put("X", x);
		centralPoint.put("Y", y);
		}
	}

	public void clean() {
		this.centralPoint.put("oldX", centralPoint.get("X"));
		this.centralPoint.put("oldY", centralPoint.get("Y"));
		vectorList.clear();
	}

	public BigDecimal movedCentralPoint() {
		BigDecimal x = this.centralPoint.get("X").subtract(this.centralPoint.get("oldX")).abs(); 
		BigDecimal y = this.centralPoint.get("Y").subtract(this.centralPoint.get("oldY")).abs(); 
		return x.pow(2).add(y.pow(2));
	}
	public boolean isEmpty(){
		if(this.vectorList.size()==0){
			return true;
		}else{
			return false;
		}
	}
}
