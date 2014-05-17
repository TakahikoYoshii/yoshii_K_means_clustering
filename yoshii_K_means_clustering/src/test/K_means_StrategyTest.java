package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import main.Cluster;
import main.ClusterList;
import main.K_means_Strategy;
import main.VectorList;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;


public class K_means_StrategyTest {
	K_means_Strategy stu ;
	ArrayList<String> inputData = new ArrayList<String>();
	VectorList vectorList = new VectorList(inputData);
	int numberOfCluster = 0;
	@Test
	public void testクラスタする数とVector型リストのデータをもらってインスタンス化できる() {
		stu = new K_means_Strategy(numberOfCluster, vectorList);
		assertThat(stu, instanceOf(K_means_Strategy.class));
	}
	@Test
	public void testクラスタする数が２のとき２個のClusterクラスのインスタンスを生成してClusterListクラスに登録する() {
		stu = new K_means_Strategy(2, vectorList);
		ClusterList clusterList = stu.getClusterList();
		assertThat(clusterList.getList().get(0), instanceOf(Cluster.class));
		assertThat(clusterList.getList().get(1), instanceOf(Cluster.class));
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
		stu = new K_means_Strategy(2, vectorList);
		long seed = 31;
		stu.firstClustering(seed);
		ClusterList clusterList = stu.getClusterList();
		assertThat(clusterList.getList().get(0).getVectorList().get(0).getData().get("X"), is(3));
		assertThat(clusterList.getList().get(0).getVectorList().get(0).getData().get("Y"), is(2));
		assertThat(clusterList.getList().get(0).getVectorList().get(1).getData().get("X"), is(2));
		assertThat(clusterList.getList().get(0).getVectorList().get(1).getData().get("Y"), is(3));
		assertThat(clusterList.getList().get(1).getVectorList().get(0).getData().get("X"), is(1));
		assertThat(clusterList.getList().get(1).getVectorList().get(0).getData().get("Y"), is(1));
		assertThat(clusterList.getList().get(1).getVectorList().get(1).getData().get("X"), is(1));
		assertThat(clusterList.getList().get(1).getVectorList().get(1).getData().get("Y"), is(4));
		assertThat(clusterList.getList().get(0).getCentralPoint().get("X"), is(new BigDecimal(2.5)));
		assertThat(clusterList.getList().get(0).getCentralPoint().get("Y"), is(new BigDecimal(2.5)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("X"), is(new BigDecimal(1.0)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("Y"), is(new BigDecimal(2.5)));
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
		stu = new K_means_Strategy(2, vectorList);
		long seed = 49;
		stu.firstClustering(seed);
		ClusterList clusterList = stu.getClusterList();
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
		assertThat(clusterList.getList().get(0).getCentralPoint().get("X"), is(new BigDecimal(4.25)));
		assertThat(clusterList.getList().get(0).getCentralPoint().get("Y"), is(new BigDecimal(5.0)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("X"), is(new BigDecimal(7.5)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("Y"), is(new BigDecimal(5.5)));
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
		stu = new K_means_Strategy(2, vectorList);
		long seed = 49;
		stu.firstClustering(seed);
		stu.clustering();
		ClusterList clusterList = stu.getClusterList();
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
		assertThat(clusterList.getList().get(0).getCentralPoint().get("X"), is(new BigDecimal(4.25)));
		assertThat(clusterList.getList().get(0).getCentralPoint().get("Y"), is(new BigDecimal(5.0)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("X"), is(new BigDecimal(7.5)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("Y"), is(new BigDecimal(5.5)));
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
		stu = new K_means_Strategy(2, vectorList);
		long seed = 49;
		stu.firstClustering(seed);
		stu.clustering();
		stu.setCentralPoint();
		stu.clustering();  
		ClusterList clusterList = stu.getClusterList();
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
		assertThat(clusterList.getList().get(0).getCentralPoint().get("X"), is(new BigDecimal(4.25)));
		assertThat(clusterList.getList().get(0).getCentralPoint().get("Y"), is(new BigDecimal(5.0)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("X"), is(new BigDecimal(7.5)));
		assertThat(clusterList.getList().get(1).getCentralPoint().get("Y"), is(new BigDecimal(5.5)));
	}
}
//(2, 2)
//(3, 4)
//(6, 1)
//
//(6, 6)
//(6, 8)
//(9, 10)
