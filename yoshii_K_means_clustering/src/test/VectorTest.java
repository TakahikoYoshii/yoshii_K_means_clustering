package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import main.Vector;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class VectorTest {
	Vector stu;
	
	@Before
	public void setUp(){
		stu  = new Vector();
	}
	@Test
	public void testインスタンスを生成できる() {
		assertThat(stu, instanceOf(Vector.class));
	}
	@Test
	public void testXとYの値をセットできるXは２Yは３とする() {
		stu.setXdata(2);
		stu.setYdata(3);
		HashMap<String, Integer> actual = stu.getData();
		assertThat(actual.get("X"), is(2));
		assertThat(actual.get("Y"), is(3));
	}
}
