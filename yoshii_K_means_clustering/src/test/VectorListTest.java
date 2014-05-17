package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.Vector;
import main.VectorList;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;


public class VectorListTest {
	VectorList stu ;
	ArrayList<String> testData = new ArrayList<String>();
	
	@Before
	public void setUp(){
		testData.add("0 0 0 0 0 0 0 0 0 0 0");//0
		testData.add("0 0 0 0 0 0 1 0 0 0 0");//1
		testData.add("0 0 1 0 0 0 0 0 0 0 0");//2
		testData.add("0 0 0 0 0 0 0 0 0 0 0");//3
		testData.add("0 0 0 1 0 0 0 0 0 0 0");//4
		testData.add("0 0 0 0 0 0 0 0 0 0 0");//5
		testData.add("0 0 0 0 0 0 1 0 0 0 0");//6
		testData.add("0 0 0 0 0 0 0 0 0 0 0");//7
		testData.add("0 0 0 0 0 0 1 0 0 0 0");//8
		testData.add("0 0 0 0 0 0 0 0 0 0 0");//9
		testData.add("0 0 0 0 0 0 0 0 0 1 0");//10
//		testData.add("0 1 2 3 4 5 6 7 8 9 10);//10
	}
	
	@Test
	public void test引数に２次元データのStrin型のデータを渡されてインスタンスが生成される() {
		ArrayList<String> data = new ArrayList<String>();
		stu = new VectorList(data);
		assertThat(stu, instanceOf(VectorList.class));
	}
	@Test
	public void test引数にtestDataを渡されてベクトル化したデータを保持できる() {
		stu = new VectorList(testData);
		ArrayList<Vector> vectorList = stu.getVectorList();
		assertThat(vectorList.get(0).getData().get("X"), is(6));assertThat(vectorList.get(0).getData().get("Y"), is(1));
		assertThat(vectorList.get(1).getData().get("X"), is(2));assertThat(vectorList.get(1).getData().get("Y"), is(2));
		assertThat(vectorList.get(2).getData().get("X"), is(3));assertThat(vectorList.get(2).getData().get("Y"), is(4));
		assertThat(vectorList.get(3).getData().get("X"), is(6));assertThat(vectorList.get(3).getData().get("Y"), is(6));
		assertThat(vectorList.get(4).getData().get("X"), is(6));assertThat(vectorList.get(4).getData().get("Y"), is(8));
		assertThat(vectorList.get(5).getData().get("X"), is(9));assertThat(vectorList.get(5).getData().get("Y"), is(10));
	}
//	(2, 2)
//	(3, 4)
//	(6, 1)
//
//	(6, 6)
//	(6, 8)
//	(9, 10)

}
