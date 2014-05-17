package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import main.Cluster;
import main.Vector;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class ClusterTest {
	Cluster stu ;
	
	@Before
	public void setUp(){
		stu = new Cluster();
	}

	@Test
	public void testインスタンス化できる() {
		assertThat(stu, instanceOf(Cluster.class));
	}
	@Test
	public void testVectorをわたすと内部のリストに一つずつ保持する() {
		Vector vector1 = new Vector();
		stu.setVector(vector1);
		Vector vector2 = new Vector();
		stu.setVector(vector2);
		ArrayList<Vector> vectorList = stu.getVectorList();
		assertThat(vectorList, hasItems(vector1, vector2));
	}
	@Test
	public void testVectorをわたすと内部のリストに一つずつ保持するそのときVectorの内部データが変わらないことが確認できる() {
		Vector vector1 = new Vector(); vector1.setXdata(2); vector1.setYdata(3);
		stu.setVector(vector1);
		Vector vector2 = new Vector(); vector2.setXdata(1); vector2.setYdata(4);
		stu.setVector(vector2);
		ArrayList<Vector> vectorList = stu.getVectorList();
		assertThat(vectorList.get(0).getData().get("X"), is(2));
		assertThat(vectorList.get(0).getData().get("Y"), is(3));
		assertThat(vectorList.get(1).getData().get("X"), is(1));
		assertThat(vectorList.get(1).getData().get("Y"), is(4));
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void testcleanメソッドを呼び出すと登録されているVectorListの中身を消す() {
		Vector vector1 = new Vector(); vector1.setXdata(2); vector1.setYdata(3);
		stu.setVector(vector1);
		Vector vector2 = new Vector(); vector2.setXdata(1); vector2.setYdata(4);
		stu.setVector(vector2);
		stu.clean();
		ArrayList<Vector> vectorList = stu.getVectorList();
		assertThat(vectorList.get(0).getData().get("X"), is(2));
		assertThat(vectorList.get(0).getData().get("Y"), is(3));
		assertThat(vectorList.get(1).getData().get("X"), is(1));
		assertThat(vectorList.get(1).getData().get("Y"), is(4));
	}
	@Test
	public void testVectorをわたすと内部のリストに一つずつ保持するそのときクラスタの中心点を保持する() {
		Vector vector1 = new Vector(); vector1.setXdata(3); vector1.setYdata(2);
		stu.setVector(vector1);
		Vector vector2 = new Vector(); vector2.setXdata(1); vector2.setYdata(4);
		stu.setVector(vector2);
		stu.setCentralPoint();
		HashMap<String, BigDecimal> central = stu.getCentralPoint();
		assertThat(central.get("X"), is(new BigDecimal(2.0)));
		assertThat(central.get("Y"), is(new BigDecimal(3.0)));
	}
}
