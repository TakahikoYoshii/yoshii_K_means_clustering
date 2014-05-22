package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.Controler;
import main.MoldingData;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class MoldingDataTest {
	MoldingData sut;
	String testString = "2\n\n" + "0 0 0 0 0 0 0 0 0 0 0\n"// 1
			+ "0 0 0 0 0 0 1 0 0 0 0\n"// 2
			+ "0 0 1 0 0 0 0 0 0 0 0\n"// 3
			+ "0 0 0 0 0 0 0 0 0 0 0\n"// 4
			+ "0 0 0 1 0 0 0 0 0 0 0\n"// 5
			+ "0 0 0 0 0 0 0 0 0 0 0\n"// 6
			+ "0 0 0 0 0 0 1 0 0 0 0\n"// 7
			+ "0 0 0 0 0 0 0 0 0 0 0\n"// 8
			+ "0 0 0 0 0 0 1 0 0 0 0\n"// 9
			+ "0 0 0 0 0 0 0 0 0 0 0\n"// 10
			+ "0 0 0 0 0 0 0 0 0 1 0\n";// 11

	@Before
	public void setUp() {
		sut = new MoldingData(this.testString);
	}

	@Test
	public void testテスト用文字列を改行ごとに区切ってリストにいれて返す() {
		ArrayList<String> actual = sut.splitLine();
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
		sut.setInputData();
		String actualCluster = sut.getNumberOfCluster();
		ArrayList<String> actualData = sut.getData();
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
	@Test
	public void testテスト用文字列を整形してcontrolerクラスに渡し帰ってきた文字列を整形できる() {
		sut.setInputData();
		Controler controler = new Controler(sut.getNumberOfCluster(), sut.getData(), "K_means");
		String actual = sut.moldOutputData(controler.execute());
		assertThat(actual, is("(2,2)\n(3,4)\n(6,1)\n\n(6,6)\n(6,8)\n(9,10)\n\n"));
	}
	@Test
	public void testデータのソート機能の確認() {
		String[] array = new String[]{"(2,4)","(1,4)","(1,2)","(1,14)"};
		String actual = sut.sort(array);
		assertThat(actual, is("(1,2)\n(1,4)\n(1,14)\n(2,4)\n"));
	}
}
