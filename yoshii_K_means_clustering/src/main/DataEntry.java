package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class DataEntry {
	private static DataEntry dataEntry;
	private InputStream in;
	private String numberOfCluster;
	private ArrayList<ArrayList<String>> EntryData = new ArrayList<ArrayList<String>>();
	private boolean isEND = true; 
	InputStreamReader reader;
	
	public static DataEntry getInstance(){
		if(dataEntry == null){
			dataEntry = new DataEntry();
		}
		return dataEntry;
	}
	
	private DataEntry() {
		this.in = System.in;
	}

	public void setInputMethod(InputStream in) {
		this.in = in;
	}
	public void createReader() throws UnsupportedEncodingException{
		reader = new InputStreamReader(this.in, "UTF-8");
	}
	public void destroyReader() throws IOException{
		reader.close();
	}
	public String getNumberOfCluster() {
		return this.numberOfCluster;
	}
	
	public boolean continueInput(){
		return this.isEND;
	}
	
	public void setNumberOfCluster() throws IOException{
		this.numberOfCluster = enterNumberOfCluster();
	}

	public String enterNumberOfCluster() throws IOException {
			int input = reader.read();
			String inputString = Character.toString((char)input);
			if(!(Pattern.matches(".*[0-9].*", inputString))){
				isEND = false;
				return null;
			}
			return inputString;
	}

	public void closeStream() throws IOException {
		this.in.close();
	}

	public ArrayList<ArrayList<String>> getEntryData() {
		return this.EntryData;
	}

	public void enterData() throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append(enterNumberOfCluster());
	}

	private void sample(String line) {
		if(!(Pattern.matches(".*[0-9].*", line))){
			isEND = false;
		}else{
			ArrayList<String> stringList = new ArrayList<String>();
			for(int i = 0 ; i <line.length() ; i++){
				stringList.add(Character.toString(line.charAt(i)));
			}
			EntryData.add(stringList);
		}
	}
	
	public static void main(String[] args) {
		DataEntry entry = DataEntry.getInstance();
		try{
		entry.createReader();
		System.out.println("START ENTRY NUMBER OF CLUSTER");
		entry.setNumberOfCluster();
		System.out.println("END ENTRY NUMBER OF CLUSTER");		
		System.out.println("START ENTRY DATA");
		while(entry.continueInput()){
			entry.enterData();
		}
		System.out.println("END ENTRY DATA");
		entry.destroyReader();
		}catch(IOException e){
			throw new RuntimeException();
		}
	}
//	private String readLine(){		
//	try(
//	InputStreamReader reader = new InputStreamReader(this.in, "UTF-8");
//	BufferedReader lineReader = new BufferedReader(reader);){
//	return lineReader.readLine();
//	}catch(IOException e){
//		return null;
//	}
//}

}
