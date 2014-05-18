package main;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import com.up.top.DbUnitTester;

public class DBUnitTesterPostgresql extends DbUnitTester{
	private final String fixture;
	public DBUnitTesterPostgresql(String fixture){
		super(null, null, null, null);
		this.fixture = fixture;
	}
	protected void before() throws Exception{
		executeQuery("DROP TABLE IF EXISTS customers");
		executeQuery("CRATE");
	}

	protected IDataSet createDataSet()throws Exception{
		return new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream(fixture));
	}
}