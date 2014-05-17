package test;

import java.util.ArrayList;
import static org.junit.Assert.*;
import main.Cluster;
import main.ClusterList;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class ClusterListTest {
	ClusterList stu ;
	
	@Before
	public void setUp(){
		stu = new ClusterList();
	}
	
	@Test
	public void testClusterクラスをセットすると内部でリストに保持してリストを返してくれる() {
		Cluster c1 = new Cluster();
		Cluster c2 = new Cluster();
		stu.setCluster(c1);
		stu.setCluster(c2);
		ArrayList<Cluster> actual = stu.getList();
		assertThat(actual, hasItems(c1, c2));
	}

}
