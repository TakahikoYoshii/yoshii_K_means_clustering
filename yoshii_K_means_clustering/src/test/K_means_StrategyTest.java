package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

import main.Cluster;
import main.ClusterList;
import main.K_means_Strategy;
import main.VectorList;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;


public class K_means_StrategyTest {
	K_means_Strategy sut ;
	ArrayList<String> inputData = new ArrayList<String>();
	VectorList vectorList = new VectorList(inputData);
	int numberOfCluster = 0;
	@Test
	public void testクラスタする数とVector型リストのデータをもらってインスタンス化できる() {
		sut = new K_means_Strategy(numberOfCluster, vectorList);
		assertThat(sut, instanceOf(K_means_Strategy.class));
	}
	@Test
	public void testクラスタする数が２のとき２個のClusterクラスのインスタンスを生成してClusterListクラスに登録する() {
		sut = new K_means_Strategy(2, vectorList);
		ClusterList clusterList = sut.getClusterList();
		assertThat(clusterList.getList().get(0), instanceOf(Cluster.class));
		assertThat(clusterList.getList().get(1), instanceOf(Cluster.class));
	}
	@Test
	public void testクラスタする数が3のとき3個のClusterクラスのインスタンスを生成してClusterListクラスに登録する() {
		sut = new K_means_Strategy(3, vectorList);
		ClusterList clusterList = sut.getClusterList();
		assertThat(clusterList.getList().get(0), instanceOf(Cluster.class));
		assertThat(clusterList.getList().get(1), instanceOf(Cluster.class));
		assertThat(clusterList.getList().get(2), instanceOf(Cluster.class));
	}
	@Test
	public void testクラスタする数が２のとき２個のClusterクラスのインスタンスを生成してクラスタリングをしてClusterListクラスに登録する() {
		ArrayList<String> inputData = new ArrayList<String>();
		inputData.add("0 0 0 0");
		inputData.add("0 1 0 0");
		inputData.add("0 0 0 1");
		inputData.add("0 0 1 0");
		inputData.add("0 1 0 0");
		VectorList vectorList = new VectorList(inputData);
		sut = new K_means_Strategy(2, vectorList);
		long seed = 31;
		sut.firstClustering(seed);
		ClusterList clusterList = sut.getClusterList();
		assertThat(clusterList.getList().get(0).getVectorList().get(0).getData().get("X"), is(3));
		assertThat(clusterList.getList().get(0).getVectorList().get(0).getData().get("Y"), is(2));
		assertThat(clusterList.getList().get(0).getVectorList().get(1).getData().get("X"), is(2));
		assertThat(clusterList.getList().get(0).getVectorList().get(1).getData().get("Y"), is(3));
		assertThat(clusterList.getList().get(1).getVectorList().get(0).getData().get("X"), is(1));
		assertThat(clusterList.getList().get(1).getVectorList().get(0).getData().get("Y"), is(1));
		assertThat(clusterList.getList().get(1).getVectorList().get(1).getData().get("X"), is(1));
		assertThat(clusterList.getList().get(1).getVectorList().get(1).getData().get("Y"), is(4));
		MathContext mc = new MathContext(3, RoundingMode.HALF_UP);
		assertThat(clusterList.getList().get(0).getCentralPoint().get("X"), is(new BigDecimal(2.501, mc)));
		assertThat(clusterList.getList().get(0).getCentralPoint().get("Y"), is(new BigDecimal(2.501, mc)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("X"), is(new BigDecimal(1.001, mc)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("Y"), is(new BigDecimal(2.501, mc)));
	}
	@Test
	public void testテストデータで上記のことをする() {
		ArrayList<String> inputData = new ArrayList<String>();
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//1
			inputData.add("0 0 0 0 0 0 1 0 0 0 0");//2
			inputData.add("0 0 1 0 0 0 0 0 0 0 0");//3
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//4
			inputData.add("0 0 0 1 0 0 0 0 0 0 0");//5
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//6
			inputData.add("0 0 0 0 0 0 1 0 0 0 0");//7
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//8
			inputData.add("0 0 0 0 0 0 1 0 0 0 0");//9
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//10
			inputData.add("0 0 0 0 0 0 0 0 0 1 0");//11
		VectorList vectorList = new VectorList(inputData);
		sut = new K_means_Strategy(2, vectorList);
		long seed = 49;
		sut.firstClustering(seed);
		ClusterList clusterList = sut.getClusterList();
		assertThat(clusterList.getList().get(0).getVectorList().get(0).getData().get("X"), is(2));
		assertThat(clusterList.getList().get(0).getVectorList().get(0).getData().get("Y"), is(2));
		assertThat(clusterList.getList().get(0).getVectorList().get(1).getData().get("X"), is(3));
		assertThat(clusterList.getList().get(0).getVectorList().get(1).getData().get("Y"), is(4));
		assertThat(clusterList.getList().get(0).getVectorList().get(2).getData().get("X"), is(6));
		assertThat(clusterList.getList().get(0).getVectorList().get(2).getData().get("Y"), is(6));
		assertThat(clusterList.getList().get(0).getVectorList().get(3).getData().get("X"), is(6));
		assertThat(clusterList.getList().get(0).getVectorList().get(3).getData().get("Y"), is(8));
		assertThat(clusterList.getList().get(1).getVectorList().get(0).getData().get("X"), is(6));
		assertThat(clusterList.getList().get(1).getVectorList().get(0).getData().get("Y"), is(1));
		assertThat(clusterList.getList().get(1).getVectorList().get(1).getData().get("X"), is(9));
		assertThat(clusterList.getList().get(1).getVectorList().get(1).getData().get("Y"), is(10));
		MathContext mc = new MathContext(3, RoundingMode.HALF_UP);
		assertThat(clusterList.getList().get(0).getCentralPoint().get("X"), is(new BigDecimal(4.25)));
		assertThat(clusterList.getList().get(0).getCentralPoint().get("Y"), is(new BigDecimal(5.0001, mc)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("X"), is(new BigDecimal(7.5001, mc)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("Y"), is(new BigDecimal(5.501, mc)));
	}
	@Test
	public void testテストデータのクラスタ数を変えて上記のことをする() {
		ArrayList<String> inputData = new ArrayList<String>();
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//1
			inputData.add("0 0 0 0 0 0 1 0 0 0 0");//2
			inputData.add("0 0 1 0 0 0 0 0 0 0 0");//3
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//4
			inputData.add("0 0 0 1 0 0 0 0 0 0 0");//5
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//6
			inputData.add("0 0 0 0 0 0 1 0 0 0 0");//7
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//8
			inputData.add("0 0 0 0 0 0 1 0 0 0 0");//9
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//10
			inputData.add("0 0 0 0 0 0 0 0 0 1 0");//11
		VectorList vectorList = new VectorList(inputData);
		sut = new K_means_Strategy(3, vectorList);
		long seed = 49;
		sut.firstClustering(seed);
		ClusterList clusterList = sut.getClusterList();
		assertThat(clusterList.getList().get(0).getVectorList().get(0).getData().get("X"), is(6));
		assertThat(clusterList.getList().get(0).getVectorList().get(0).getData().get("Y"), is(1));
		assertThat(clusterList.getList().get(0).getVectorList().get(1).getData().get("X"), is(2));
		assertThat(clusterList.getList().get(0).getVectorList().get(1).getData().get("Y"), is(2));
		assertThat(clusterList.getList().get(0).getVectorList().get(2).getData().get("X"), is(6));
		assertThat(clusterList.getList().get(0).getVectorList().get(2).getData().get("Y"), is(8));
		assertThat(clusterList.getList().get(2).getVectorList().get(0).getData().get("X"), is(3));
		assertThat(clusterList.getList().get(2).getVectorList().get(0).getData().get("Y"), is(4));
		assertThat(clusterList.getList().get(1).getVectorList().get(0).getData().get("X"), is(6));
		assertThat(clusterList.getList().get(1).getVectorList().get(0).getData().get("Y"), is(6));
		assertThat(clusterList.getList().get(1).getVectorList().get(1).getData().get("X"), is(9));
		assertThat(clusterList.getList().get(1).getVectorList().get(1).getData().get("Y"), is(10));
		MathContext mc = new MathContext(3, RoundingMode.HALF_UP);
		assertThat(clusterList.getList().get(0).getCentralPoint().get("X"), is(new BigDecimal(4.67, mc)));
		assertThat(clusterList.getList().get(0).getCentralPoint().get("Y"), is(new BigDecimal(3.6701, mc)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("X"), is(new BigDecimal(7.5001, mc)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("Y"), is(new BigDecimal(8.001, mc)));
		assertThat(clusterList.getList().get(2).getCentralPoint().get("X"), is(new BigDecimal(3.0001, mc)));
		assertThat(clusterList.getList().get(2).getCentralPoint().get("Y"), is(new BigDecimal(4.001, mc)));
	}
	@Test
	public void testもう一度クラスタリングすると一番近い重心のところにクラスタリングされる() {
		ArrayList<String> inputData = new ArrayList<String>();
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//1
			inputData.add("0 0 0 0 0 0 1 0 0 0 0");//2
			inputData.add("0 0 1 0 0 0 0 0 0 0 0");//3
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//4
			inputData.add("0 0 0 1 0 0 0 0 0 0 0");//5
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//6
			inputData.add("0 0 0 0 0 0 1 0 0 0 0");//7
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//8
			inputData.add("0 0 0 0 0 0 1 0 0 0 0");//9
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//10
			inputData.add("0 0 0 0 0 0 0 0 0 1 0");//11
		VectorList vectorList = new VectorList(inputData);
		sut = new K_means_Strategy(2, vectorList);
		long seed = 49;
		sut.firstClustering(seed);
		sut.clustering();
		ClusterList clusterList = sut.getClusterList();
		assertThat(clusterList.getList().get(0).getVectorList().get(0).getData().get("X"), is(6));
		assertThat(clusterList.getList().get(0).getVectorList().get(0).getData().get("Y"), is(6));
		assertThat(clusterList.getList().get(0).getVectorList().get(1).getData().get("X"), is(6));
		assertThat(clusterList.getList().get(0).getVectorList().get(1).getData().get("Y"), is(8));
		assertThat(clusterList.getList().get(0).getVectorList().get(2).getData().get("X"), is(9));
		assertThat(clusterList.getList().get(0).getVectorList().get(2).getData().get("Y"), is(10));
		assertThat(clusterList.getList().get(1).getVectorList().get(0).getData().get("X"), is(6));
		assertThat(clusterList.getList().get(1).getVectorList().get(0).getData().get("Y"), is(1));
		assertThat(clusterList.getList().get(1).getVectorList().get(1).getData().get("X"), is(2));
		assertThat(clusterList.getList().get(1).getVectorList().get(1).getData().get("Y"), is(2));
		assertThat(clusterList.getList().get(1).getVectorList().get(2).getData().get("X"), is(3));
		assertThat(clusterList.getList().get(1).getVectorList().get(2).getData().get("Y"), is(4));
		MathContext mc = new MathContext(3, RoundingMode.HALF_UP);
		assertThat(clusterList.getList().get(0).getCentralPoint().get("X"), is(new BigDecimal(4.25)));
		assertThat(clusterList.getList().get(0).getCentralPoint().get("Y"), is(new BigDecimal(5.0001, mc)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("X"), is(new BigDecimal(7.501, mc)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("Y"), is(new BigDecimal(5.501, mc)));
	}
	@Test
	public void testさらにもう一度クラスタリングした時に前の各クラスタの中心点の動きが閾値以下ならクラスタリング終了() {
		ArrayList<String> inputData = new ArrayList<String>();
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//1
			inputData.add("0 0 0 0 0 0 1 0 0 0 0");//2
			inputData.add("0 0 1 0 0 0 0 0 0 0 0");//3
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//4
			inputData.add("0 0 0 1 0 0 0 0 0 0 0");//5
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//6
			inputData.add("0 0 0 0 0 0 1 0 0 0 0");//7
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//8
			inputData.add("0 0 0 0 0 0 1 0 0 0 0");//9
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//10
			inputData.add("0 0 0 0 0 0 0 0 0 1 0");//11
		VectorList vectorList = new VectorList(inputData);
		sut = new K_means_Strategy(2, vectorList);
		long seed = 49;
		sut.firstClustering(seed);
		sut.clustering();
		sut.setCentralPoint();
		while(sut.validateThreshold()){
			sut.setCentralPoint();
			sut.clustering();  
		}
		ClusterList clusterList = sut.getClusterList();
		assertThat(clusterList.getList().get(0).getVectorList().get(0).getData().get("X"), is(6));
		assertThat(clusterList.getList().get(0).getVectorList().get(0).getData().get("Y"), is(6));
		assertThat(clusterList.getList().get(0).getVectorList().get(1).getData().get("X"), is(6));
		assertThat(clusterList.getList().get(0).getVectorList().get(1).getData().get("Y"), is(8));
		assertThat(clusterList.getList().get(0).getVectorList().get(2).getData().get("X"), is(9));
		assertThat(clusterList.getList().get(0).getVectorList().get(2).getData().get("Y"), is(10));
		assertThat(clusterList.getList().get(1).getVectorList().get(0).getData().get("X"), is(6));
		assertThat(clusterList.getList().get(1).getVectorList().get(0).getData().get("Y"), is(1));
		assertThat(clusterList.getList().get(1).getVectorList().get(1).getData().get("X"), is(2));
		assertThat(clusterList.getList().get(1).getVectorList().get(1).getData().get("Y"), is(2));
		assertThat(clusterList.getList().get(1).getVectorList().get(2).getData().get("X"), is(3));
		assertThat(clusterList.getList().get(1).getVectorList().get(2).getData().get("Y"), is(4));
		MathContext mc = new MathContext(3, RoundingMode.HALF_UP);
		assertThat(clusterList.getList().get(0).getCentralPoint().get("X"), is(new BigDecimal(7.001, mc)));
		assertThat(clusterList.getList().get(0).getCentralPoint().get("Y"), is(new BigDecimal(8.001, mc)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("X"), is(new BigDecimal(3.6701, mc)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("Y"), is(new BigDecimal(2.3301, mc)));
	}
	@Test
	public void testクラスタ数を増やしてさらにもう一度クラスタリングした時に前の各クラスタの中心点の動きが閾値以下ならクラスタリング終了() {
		ArrayList<String> inputData = new ArrayList<String>();
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//1
			inputData.add("0 0 0 0 0 0 1 0 0 0 0");//2
			inputData.add("0 0 1 0 0 0 0 0 0 0 0");//3
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//4
			inputData.add("0 0 0 1 0 0 0 0 0 0 0");//5
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//6
			inputData.add("0 0 0 0 0 0 1 0 0 0 0");//7
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//8
			inputData.add("0 0 0 0 0 0 1 0 0 0 0");//9
			inputData.add("0 0 0 0 0 0 0 0 0 0 0");//10
			inputData.add("0 0 0 0 0 0 0 0 0 1 0");//11
		VectorList vectorList = new VectorList(inputData);
		sut = new K_means_Strategy(3, vectorList);
		long seed = 49;
		Random ran = new Random();
		label : do{
			sut.firstClustering(seed);
			if(sut.isEmpty()){seed = ran.nextLong(); continue label;}
			sut.clustering();
			if(sut.isEmpty()){seed = ran.nextLong(); continue label;}
			while(sut.validateThreshold()){
				sut.setCentralPoint();
				if(sut.isEmpty()){seed = ran.nextLong(); continue label;}
				sut.clustering();  
				if(sut.isEmpty()){seed = ran.nextLong(); continue label;}
				sut.setCentralPoint();
				if(sut.isEmpty()){seed = ran.nextLong(); continue label;}
			}
		}while(sut.isEmpty());
		ClusterList clusterList = sut.getClusterList();
		assertThat(clusterList.getList().get(0).getVectorList().get(0).getData().get("X"), is(6));
		assertThat(clusterList.getList().get(0).getVectorList().get(0).getData().get("Y"), is(6));
		assertThat(clusterList.getList().get(1).getVectorList().get(0).getData().get("X"), is(6));
		assertThat(clusterList.getList().get(1).getVectorList().get(0).getData().get("Y"), is(6));
		assertThat(clusterList.getList().get(2).getVectorList().get(0).getData().get("X"), is(9));
		assertThat(clusterList.getList().get(2).getVectorList().get(0).getData().get("Y"), is(10));
		assertThat(clusterList.getList().get(2).getVectorList().get(1).getData().get("X"), is(2));
		assertThat(clusterList.getList().get(2).getVectorList().get(1).getData().get("Y"), is(2));
		assertThat(clusterList.getList().get(2).getVectorList().get(2).getData().get("X"), is(2));
		assertThat(clusterList.getList().get(2).getVectorList().get(2).getData().get("Y"), is(2));
		assertThat(clusterList.getList().get(2).getVectorList().get(3).getData().get("X"), is(3));
		assertThat(clusterList.getList().get(2).getVectorList().get(3).getData().get("Y"), is(4));
		MathContext mc = new MathContext(3, RoundingMode.HALF_UP);
		assertThat(clusterList.getList().get(0).getCentralPoint().get("X"), is(new BigDecimal(7.001, mc)));
		assertThat(clusterList.getList().get(0).getCentralPoint().get("Y"), is(new BigDecimal(8.001, mc)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("X"), is(new BigDecimal(3.6701, mc)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("Y"), is(new BigDecimal(2.3301, mc)));
	}
}
//(2, 2)
//(3, 4)
//(6, 1)
//
//(6, 6)
//(6, 8)
//(9, 10)
