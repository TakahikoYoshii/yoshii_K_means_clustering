package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.moldingData;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
public class moldingDataTest {
	String testString = "2\n\n"
			+ "0 0 0 0 0 0 0 0 0 0 0\n"//1
			+ "0 0 0 0 0 0 1 0 0 0 0\n"//2
			+ "0 0 1 0 0 0 0 0 0 0 0\n"//3
			+ "0 0 0 0 0 0 0 0 0 0 0\n"//4
			+ "0 0 0 1 0 0 0 0 0 0 0\n"//5
			+ "0 0 0 0 0 0 0 0 0 0 0\n"//6
			+ "0 0 0 0 0 0 1 0 0 0 0\n"//7
			+ "0 0 0 0 0 0 0 0 0 0 0\n"//8
			+ "0 0 0 0 0 0 1 0 0 0 0\n"//9
			+ "0 0 0 0 0 0 0 0 0 0 0\n"//10
			+ "0 0 0 0 0 0 0 0 0 1 0\n";//11
	@Test
	public void testテスト用文字列を改行ごとに区切ってリストにいれて返す() {
			moldingData stu = new moldingData(this.testString);
			ArrayList<String> actual = stu.splitLine();
			assertThat(actual.get(0), is("2"));
			assertThat(actual.get(1), is("0 0 0 0 0 0 0 0 0 0 0"));
			assertThat(actual.get(2), is("0 0 0 0 0 0 1 0 0 0 0"));
			assertThat(actual.get(3), is("0 0 1 0 0 0 0 0 0 0 0"));
			assertThat(actual.get(4), is("0 0 0 0 0 0 0 0 0 0 0"));
			assertThat(actual.get(5), is("0 0 0 1 0 0 0 0 0 0 0"));
			assertThat(actual.get(6), is("0 0 0 0 0 0 0 0 0 0 0"));
			assertThat(actual.get(7), is("0 0 0 0 0 0 1 0 0 0 0"));
			assertThat(actual.get(8), is("0 0 0 0 0 0 0 0 0 0 0"));
			assertThat(actual.get(9), is("0 0 0 0 0 0 1 0 0 0 0"));
			assertThat(actual.get(10), is("0 0 0 0 0 0 0 0 0 0 0"));
			assertThat(actual.get(11), is("0 0 0 0 0 0 0 0 0 1 0"));
	}
	
	@Test
	public void testテスト用文字列を渡すとクラスタ数が２とデータに分けてデータを保持することができる() {
			moldingData stu = new moldingData(this.testString);
			stu.setInputData();
			String actualCluster = stu.getNumberOfCluster();
			ArrayList<String> actualData = stu.getData();
			assertThat(actualCluster, is("2"));
			assertThat(actualData.get(0), is("0 0 0 0 0 0 0 0 0 0 0"));
			assertThat(actualData.get(1), is("0 0 0 0 0 0 1 0 0 0 0"));
			assertThat(actualData.get(2), is("0 0 1 0 0 0 0 0 0 0 0"));
			assertThat(actualData.get(3), is("0 0 0 0 0 0 0 0 0 0 0"));
			assertThat(actualData.get(4), is("0 0 0 1 0 0 0 0 0 0 0"));
			assertThat(actualData.get(5), is("0 0 0 0 0 0 0 0 0 0 0"));
			assertThat(actualData.get(6), is("0 0 0 0 0 0 1 0 0 0 0"));
			assertThat(actualData.get(7), is("0 0 0 0 0 0 0 0 0 0 0"));
			assertThat(actualData.get(8), is("0 0 0 0 0 0 1 0 0 0 0"));
			assertThat(actualData.get(9), is("0 0 0 0 0 0 0 0 0 0 0"));
			assertThat(actualData.get(10), is("0 0 0 0 0 0 0 0 0 1 0"));
	}
}
