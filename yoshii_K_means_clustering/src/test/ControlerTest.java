package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import main.ClusterList;
import main.ClusteringStrategy;
import main.Controler;
import main.K_means_Strategy;
import main.Vector;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class ControlerTest {
	List<String> stringList = new ArrayList<String>();
	ClusteringStrategy strategy = new K_means_Strategy();
	Controler stu ;
	String numberOfCluster;
	
	@Before
	public void setUp(){
		stu = new Controler(numberOfCluster, stringList, strategy);		
	}
	@Test
	public void test分割するクラスタ数と入力データのStringListとクラスタリング手法であるKmeans法を引数に渡してインスタンスを生成できる() {
		assertThat(stu, instanceOf(Controler.class));
	}
	@Test
	public void test自分の持っているstringListをベクトル化してVector型のインスタンスを取得できる() {
		Vector actualVector = stu.modelingVector();
		assertThat(actualVector, instanceOf(Vector.class));
	}
	
	@Test
	public void testClustering結果のクラスタのリストを取得できる() {
		ClusterList actualList = stu.getResult();
		assertThat(actualList, instanceOf(ClusterList.class));
	}
}
