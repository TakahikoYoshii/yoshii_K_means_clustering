package main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DBTest {
	DbUnitTester tester = new DBUnitTesterPostgresql("fixture.xml");
	
	@Before
	public void setUp() throws Exception{
		tester.before();
		tester.createDataSet();
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
