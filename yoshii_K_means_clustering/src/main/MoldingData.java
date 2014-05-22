package main;

import java.util.ArrayList;
import java.util.Comparator;

public class MoldingData implements Comparator<String> {
	private String inputData;
	private String NumberOfCluster;
	private ArrayList<String> Data = new ArrayList<String>();

	public MoldingData(String moldString) {
		this.inputData = moldString;
	}

	public ArrayList<String> splitLine() {
		String[] lineString = this.inputData.split("\n");
		ArrayList<String> lineList = new ArrayList<String>();
		for (String line : lineString) {
			lineList.add(line);
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
		for (int i = 1; i < lineList.size(); i++) {
			this.Data.add(lineList.get(i));
		}
	}

	public String moldOutputData(String result) {
		String[] clusterArray = result.split(" ");
		String[] dataSortedClusterArray = new String[clusterArray.length];
		for (int i=0 ; i<clusterArray.length ; i++) {
			String[] dataArray = clusterArray[i].split(":");
			dataSortedClusterArray[i] = this.sort(dataArray);
		}
		String outputString = this.sort(dataSortedClusterArray);
		return outputString;
	}

	public String sort(String[] dataArray) {
		for (int i = dataArray.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (compare(dataArray[j], dataArray[j + 1]) > 0) {
					String tmp = dataArray[j];
					dataArray[j] = dataArray[j + 1];
					dataArray[j + 1] = tmp;
				}
			}
		}
		String result = "";
		for(String string : dataArray){
			result = result + string +"\n";
		}
		return result;
	}

	@Override
	public int compare(String thisString, String otherString) {
		thisString = thisString.replaceAll(",", "　");
		otherString = otherString.replaceAll(",", "　");
		int thisNumber;
		int otherNumber;
		thisNumber = Integer.parseInt(thisString.substring(1, thisString.indexOf("　")));
		otherNumber = Integer.parseInt(otherString.substring(1, otherString.indexOf("　")));
		if(thisNumber > otherNumber){
			return 1;
		}else if(thisNumber < otherNumber){
			return -1;
		}else{
			thisNumber = Integer.parseInt(thisString.substring(thisString.indexOf("　")+1, thisString.indexOf(")")));
			otherNumber = Integer.parseInt(otherString.substring(otherString.indexOf("　")+1, otherString.indexOf(")")));
			if(thisNumber > otherNumber){
				return 1;
			}else if(thisNumber < otherNumber){
				return -1;
			}else{
				throw new RuntimeException();
			}
		}
	}
}
