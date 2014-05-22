package main;

import java.math.BigDecimal;
import java.util.Random;

public class K_means_Strategy implements ClusteringStrategy {

	private int cluster;
	private VectorList vectorList;
	private ClusterList clusterList ;
	public boolean isEmpty = false;
	
	public K_means_Strategy(int numberOfCluster, VectorList vectorList) {
		this.cluster = numberOfCluster;
		this.vectorList = vectorList;
		this.clusterList = this.createClusterList();
	}
	

	private ClusterList createClusterList() {
		ClusterList list = new ClusterList();
		for(int i=0; i<this.cluster ; i++){
			list.setCluster(new Cluster());
		}
		return list;
	}
	public ClusterList getClusterList(){
		return this.clusterList;
	}
	public void setCentralPoint(){
		for(Cluster cluster : clusterList.getList()){
			cluster.setCentralPoint();
		}
	}
	public void firstClustering(long seed) {
		Random random = new Random(seed);
		for(Vector vector : vectorList.getVectorList()){
			int r = random.nextInt(cluster);		
			clusterList.getList().get(r).setVector(vector);
		}		
		this.setCentralPoint();
	}

	public void clustering() {
		//クラスタの中身を一度空にする
		for(Cluster cluster : clusterList.getList()){
			cluster.clean();
		}
		//距離の地番近いものに入れ直す
		for(Vector vector : vectorList.getVectorList()){
			BigDecimal distance = this.getDistance(vector, clusterList.getList().get(0)); 
			int minimum = 0;
			for(int i=1; i<clusterList.getList().size(); i++){
				if(distance.equals(distance.min(this.getDistance(vector, clusterList.getList().get(i))))){
					distance = this.getDistance(vector, clusterList.getList().get(i));
					minimum = i;
				}
			}
			clusterList.getList().get(minimum).setVector(vector);
		}
	}

	private BigDecimal getDistance(Vector vector, Cluster cluster) {
		BigDecimal x = cluster.getCentralPoint().get("X").subtract(new BigDecimal(vector.getData().get("X"))).abs(); 
		BigDecimal y = cluster.getCentralPoint().get("Y").subtract(new BigDecimal(vector.getData().get("Y"))).abs();
		return x.pow(2).add(y.pow(2));
	}


	public boolean validateThreshold() {
		for(Cluster cluster : clusterList.getList()){
			BigDecimal differ = cluster.movedCentralPoint();
			if(!(differ == differ.min(new BigDecimal(0.1)))){
				return false;
			}
		}
		return false;
	}
	
	public boolean isEmpty(){
		for(Cluster cluster : clusterList.getList()){
			if(cluster.isEmpty()){
				return true;
			}
			continue;
		}
		return false;
	}
}
