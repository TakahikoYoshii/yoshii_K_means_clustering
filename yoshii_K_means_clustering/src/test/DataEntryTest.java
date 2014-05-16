package test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import main.DataEntry;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class DataEntryTest {
	static DataEntry stuEntry = DataEntry.getInstance();
	static InputStream stream ;
	
	@Before
	public void setUp(){
		System.setIn(stream);
	}
	@After
	public void tearDown(){
        System.setIn(null);
	}
	@Test
	public void testインスタンスを生成することができる(){
		assertThat(stuEntry, instanceOf(DataEntry.class));
	}
	@Test
	public void test入力方法を標準入力にセットできる() {
		try{
			stuEntry.setInputMethod(System.in);
			assertTrue(true);
		}catch(Exception e){
			fail();
		}
	}
	@Test
	public void testクラスタ数３を入力すると３が入力されている() throws IOException {
		String inputString = "3";
		byte[] input = inputString.getBytes();
		stream = new ByteArrayInputStream(input);
		stuEntry.setInputMethod(stream);
		stuEntry.enterNumberOfCluster();
		String actual = stuEntry.getNumberOfCluster();
		String expected = "3";
		assertThat(actual, is(expected));
	}
	@Test
	public void test２かけ２のデータ入力を文字列型の配列で受け取れる() throws IOException {
		String inputString = "01\n10";
		byte[] input = inputString.getBytes();
		stream = new ByteArrayInputStream(input);
		stuEntry.setInputMethod(stream);
		stuEntry.enterData();
		ArrayList<ArrayList<String>> actual = stuEntry.getEntryData();
		ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>();
		expected.add(new ArrayList<String>(){{add("0");add("1");}});
		expected.add(new ArrayList<String>(){{add("1");add("0");}});
		assertThat(actual, is(expected));
	}
}
