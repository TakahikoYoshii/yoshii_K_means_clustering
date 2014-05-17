package main;

import java.util.ArrayList;

public class ClusterList {


	private ArrayList<Cluster> ClusterList = new ArrayList<Cluster>();

	public void setCluster(Cluster cluster) {
		this.ClusterList.add(cluster);
	}

	public ArrayList<Cluster> getList() {
		return this.ClusterList;
	}

}
