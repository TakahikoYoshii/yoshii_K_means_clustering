package main;

import java.util.ArrayList;
import java.util.List;

public class VectorList {
	private ArrayList<Vector> vectorList = new ArrayList<Vector>(); 
	ArrayList<String> inputData ;
	
	public VectorList(List<String> inputData) {
		this.inputData = (ArrayList<String>) inputData;
		createVectorList();
	}

	public ArrayList<Vector> getVectorList() {
		return vectorList;
	}
	private void createVectorList(){
		for(int y=0; y<inputData.size(); y++){
			ArrayList<String> lineList = this.splitBranck(inputData.get(y));			
			for(int x=0; x<lineList.size(); x++){
				if(Integer.parseInt(lineList.get(x))==1){
					Vector vector = new Vector();
					vector.setXdata(x);
					vector.setYdata(y);
//					System.out.println(x);
					vectorList.add(vector);
				}
			}
		}
	}

	private ArrayList<String> splitBranck(String lineString){
		ArrayList<String> splitBranck = new ArrayList<String>();
		String[] str = lineString.split(" ");
		for(String string : str){
			splitBranck.add(string);
		}
		return splitBranck; 
	}
}
