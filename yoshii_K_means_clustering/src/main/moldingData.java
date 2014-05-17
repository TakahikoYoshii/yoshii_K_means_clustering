package main;

import java.util.ArrayList;

public class moldingData {
	private String inputData ;
	private String NumberOfCluster;
	private ArrayList<String> Data = new ArrayList<String>();
	
	public moldingData(String moldString) {
		this.inputData = moldString;
	}

	public ArrayList<String> splitLine() {
		String[] lineString = this.inputData.split("\n");
		ArrayList<String> lineList = new ArrayList<String>();
		for(String line:lineString){
			lineList.add(line);
			System.out.println(line);
		}
		lineList.remove(1);
		return lineList;
	}

	public String getNumberOfCluster() {
		return this.NumberOfCluster;
	}

	public ArrayList<String> getData() {
		return this.Data;
	}

	public void setInputData() {
		ArrayList<String> lineList = this.splitLine();
		this.NumberOfCluster = lineList.get(0);
		for(int i=1; i<lineList.size(); i++){
			this.Data.add(lineList.get(i));
		}
	}

}
