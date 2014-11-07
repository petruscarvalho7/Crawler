package br.com.crawler.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class DatabaseDAO {
 
	private static final String POSTGRESQL_LOCALHOST = "jdbc:postgresql://localhost:5432/crawler";
	private static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
	public Connection conn = null;
 
	public DatabaseDAO() {
		try {
			Class.forName(POSTGRESQL_DRIVER);
			this.conn = DriverManager.getConnection(POSTGRESQL_LOCALHOST, "postgres", "root");
			System.out.println("Conectado ao DB");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
 
	public ResultSet runSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);
	}
 
	public boolean runSql2(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.execute(sql);
	}
 
	@Override
	protected void finalize() throws Throwable {
		if (conn != null || !conn.isClosed()) {
			conn.close();
		}
	}
}