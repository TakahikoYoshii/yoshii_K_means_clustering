package test;

import static org.junit.Assert.*;

import java.util.List;

import main.Controler;
import main.VectorList;
import main.MoldingData;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class ControlerTest {
	List<String> stringList ;
	Controler sut ;
	String numberOfCluster ;
	String testString = "\n\n"
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
	String strategy = "K_means";
	
	@Before
	public void setUp(){
		MoldingData data = new MoldingData(this.testString);
		data.setInputData();
		numberOfCluster = data.getNumberOfCluster();
		stringList = data.getData();
		sut = new Controler(numberOfCluster, stringList, strategy);		
	}

	@Test
	public void test分割するクラスタ数と入力データのStringListとクラスタリング手法であるKmeans法を引数に渡してインスタンスを生成できる() {
		assertThat(sut, instanceOf(Controler.class));
	}
	@Test
	public void test自分の持っているstringListをベクトル化してVector型のインスタンスを取得できる() {
		VectorList actualVector = sut.modelingVector();
		assertThat(actualVector, instanceOf(VectorList.class));
	}
	
	@Test
	public void testClustering結果を取得できるがクラスタリングを実行する前はNULLとなる() {
		String actual = sut.getResult();
		assertThat(actual, nullValue());
	}

	@Test
	public void testClusterListを渡されると中のベクトルデータを受け取れる() {
		sut.executeClustering();
		sut.convertResultToString();
		String actual = sut.getResult();
		assertThat(actual, not(nullValue()));
	}
	@Test
	public void testStrategyを指定するとその手法でクラスタリングを実行してくれる() {
		String actual = sut.execute();
		assertThat(actual, is("(6,6):(6,8):(9,10) (6,1):(2,2):(3,4)"));
	}
}
