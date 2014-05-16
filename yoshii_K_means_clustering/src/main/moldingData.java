package main;

import java.util.ArrayList;

public class moldingData {
	private String Data ;
	public moldingData(String moldString) {
		this.Data = moldString;
	}

	public ArrayList<String> splitLine() {
		String[] lineString = this.Data.split("\n");
		ArrayList<String> lineList = new ArrayList<String>();
		for(String line:lineString){
			lineList.add(line);
			System.out.println(line);
		}
		lineList.remove(1);
		return lineList;
	}
}
