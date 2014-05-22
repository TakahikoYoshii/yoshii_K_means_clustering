package test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import main.DataEntry;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class DataEntryTest {
	String actual ;
	InputStream stream ;
	String testData = "2\n\n" + "0 0 0 0 0 0 0 0 0 0 0\n"// 1
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
	public void setUp(){
		byte[] byteArray = testData.getBytes();
		stream = new ByteArrayInputStream(byteArray);
	}
	@Test
	public void test入力できるかチェック(){
		DataEntry.setInputStream(stream);
		actual= DataEntry.getInput();
		assertThat(actual, is(testData));
	}
}
