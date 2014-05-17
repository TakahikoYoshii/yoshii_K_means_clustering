package main;

import java.util.ArrayList;
import java.util.List;

public class Controler {
	private List<String> inputData = new ArrayList<String>();

	public Controler(String numberOfCluster, List<String> stringList, ClusteringStrategy strategy) {
		this.inputData = stringList; 
	}

	public VectorList modelingVector() {
		return new VectorList(inputData);
	}

	public ClusterList getResult() {
		return new ClusterList();
	}
}
