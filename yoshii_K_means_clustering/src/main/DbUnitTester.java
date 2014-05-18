package main;

import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.AbstractDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.postgresql.*;

public abstract class DbUnitTester extends AbstractDatabaseTester{
	private final String connectionUrl;
	private final String username;
	private final String password;
	
	public DbUnitTester(String driverClass, String connectionUrl){
		this(driverClass, connectionUrl, null, null);
	}
	
	public DbUnitTester(String driverClass, String connectionUrl, String username, String password){
		this(driverClass, connectionUrl, username, password, null);
	}
	
	public DbUnitTester(String driverClass, String connectionUrl, String username, String password, String schema){
		super(schema);
		this.connectionUrl = connectionUrl;
		this.username = username;
		this.password = password;
		assertNotNullNorEmpty("driverClass", driverClass);
		try{
			//JDBCドライバのロード
			Class.forName(driverClass);
		}catch(ClassNotFoundException e){
			throw new AssertionError(e);
		}	
	}
//オーバーライド
	public IDatabaseConnection getConnection() throws Exception{
		Connection conn = null;
		if(username == null && password == null){
			conn = DriverManager.getConnection(connectionUrl);
		}else {
			conn = DriverManager.getConnection(connectionUrl, username, password);
		}
		DatabaseConnection dbConnection = new DatabaseConnection(conn, getSchema());
		DatabaseConfig config = dbConnection.getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());
		return dbConnection;
	}
	protected void executeQuery(String sql)throws Exception{
		Connection conn = getConnection().getConnection();
		conn.createStatement().execute(sql);
		conn.commit();
		conn.close();
	}
	protected void before()throws Exception{
		
	}
	protected void after()throws Exception{
		
	}
	abstract protected IDataSet createDataSet()throws Exception;
	
}
