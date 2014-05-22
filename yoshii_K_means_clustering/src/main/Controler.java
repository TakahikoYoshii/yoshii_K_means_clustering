package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controler {
	private List<String> inputData = new ArrayList<String>();
	private int numberOfCluster;
	private ClusterList clusterList;
	private VectorList vectorList;
	private String strategy;
	private String outputData;

	public Controler(String numberOfCluster, List<String> stringList, String strategy) {
		this.inputData = stringList;
		this.numberOfCluster = Integer.parseInt(numberOfCluster);
		this.vectorList = new VectorList(inputData);
		this.strategy = strategy;
	}

	public VectorList modelingVector() {
		return vectorList;
	}

	public String getResult() {
		return outputData;
	}

	public void executeClustering() {
		if(this.strategy.equals("K_means")){
			this.executeK_means();
		}else{
			throw new RuntimeException();
		}
	}
	
	private void executeK_means(){
		K_means_Strategy strategy = new K_means_Strategy(this.numberOfCluster, this.vectorList);
		long seed = 49;
		Random ran = new Random();
		label : do{
			strategy.firstClustering(seed);
			if(strategy.isEmpty()){seed = ran.nextLong(); continue label;}
			strategy.clustering();
			if(strategy.isEmpty()){seed = ran.nextLong(); continue label;}
			while(strategy.validateThreshold()){
				strategy.setCentralPoint();
				if(strategy.isEmpty()){seed = ran.nextLong(); continue label;}
				strategy.clustering();  
				if(strategy.isEmpty()){seed = ran.nextLong(); continue label;}
				strategy.setCentralPoint();
				if(strategy.isEmpty()){seed = ran.nextLong(); continue label;}
			}
		}while(strategy.isEmpty());
		this.clusterList = strategy.getClusterList();

	}

	public void convertResultToString() {
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < clusterList.getList().size() ; i++){
			for(int j = 0 ; j < clusterList.getList().get(i).getVectorList().size() ; j++){
				sb.append("("+clusterList.getList().get(i).getVectorList().get(j).getData().get("X")+",");
				sb.append(clusterList.getList().get(i).getVectorList().get(j).getData().get("Y")+")");
				if(j != clusterList.getList().get(i).getVectorList().size()-1)
					sb.append(":");
			}
			if(i != clusterList.getList().size()-1)
				sb.append(" ");
		}
		this.outputData = sb.toString();
	}

	public String execute() {
		this.executeClustering();
		this.convertResultToString();
		return this.getResult();
	}
}
