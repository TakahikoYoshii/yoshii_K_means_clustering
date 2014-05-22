package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataEntry {
	private static InputStream inputStream = System.in; 
	private static StringBuffer inputString = new StringBuffer();
	
	public static void setInputStream(InputStream stream){
		inputStream = stream;
	}
	public static String getInput(){
		return inputString.toString();
	}
		
	public static void readInput() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		boolean brancksequence = false;
		System.out.println("Please Input Data ....");
		while(true){
			String input = br.readLine();
//			if(isCollectInput(input)){
//				System.out.print("不正な文字の入力です。");
//				break;
//			}
			if(!(input.equals(""))){
				inputString.append(input+"\n");
			}else{
				if(brancksequence){
//					System.out.println("空白の行を入れずに入力してください。");
					break;
				}else{
					brancksequence = true;
					inputString.append(input+"\n");				}
			}
		}
	}
	
	public static boolean isCollectInput(String string){
		String regex = ".*[0-9].*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		String regex1 = "";
		Pattern pattern1 = Pattern.compile(regex1);
		Matcher matcher1 = pattern1.matcher(string);
		if(matcher.find()||matcher1.find()){
			return false;
		}else{
			return true;
		}
	}
	
	public static void outputData(String output){
		System.out.println(output);
	}
	
	public static void main(String[] args) throws IOException{
		readInput();
		MoldingData moldData = new MoldingData(inputString.toString());
		moldData.setInputData();
		Controler controler = new Controler(moldData.getNumberOfCluster(), moldData.getData(), "K_means");
		System.out.println("Result as Clustering ....");
		String output = moldData.moldOutputData(controler.execute());
		outputData(output);
	}
}
