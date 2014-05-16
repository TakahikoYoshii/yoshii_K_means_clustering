package main;

import java.util.ArrayList;
import java.util.List;

public class Controler {
	private List<String> inputData = new ArrayList<String>();

	public Controler(String numberOfCluster, List<String> stringList, ClusteringStrategy strategy) {
		this.inputData = stringList; 
	}

	public Vector modelingVector() {
		return new Vector(inputData);
	}

	public ClusterList getResult() {
		return new ClusterList();
	}
}
