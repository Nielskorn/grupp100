package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DBController {
	private static Connection connectToDB(String url,String username, String password) throws SQLException
	{
		return DriverManager.getConnection(url,username,password);
	}
	
	
	public static ArrayList<CPUUsage> getCPUUsage(String sqlRequest) throws SQLException
	{
		ArrayList<CPUUsage> answer = new ArrayList<>();
		Connection c = connectToDB(DatabaseProperties.URL(),DatabaseProperties.USERNAME(),DatabaseProperties.PASSWORD());
		Statement s = c.createStatement();
		s.execute(sqlRequest);
		ResultSet resultSet = s.getResultSet();
		while(resultSet.next())
		{ 
			CPUUsage resultObject = new CPUUsage(resultSet.getTimestamp("date"),resultSet.getInt("usage"));
			answer.add(resultObject);
		}
		c.close();
		return answer;
	}
	
	public static ArrayList<RAMUsage> getRAMUsage(String sqlRequest) throws SQLException
	{
		ArrayList<RAMUsage> answer = new ArrayList<>();
		Connection c = connectToDB(DatabaseProperties.URL(),DatabaseProperties.USERNAME(),DatabaseProperties.PASSWORD());
		Statement s = c.createStatement();
		s.execute(sqlRequest);
		ResultSet resultSet = s.getResultSet();
		while(resultSet.next())
		{ 
			RAMUsage resultObject = new RAMUsage(resultSet.getTimestamp("date"),resultSet.getDouble("usage"));
			answer.add(resultObject);
		}
		c.close();
		return answer;
	}
	
	public static ArrayList<String> getTimeInString(String sqlRequest) throws SQLException
	{
		ArrayList<String> answer = new ArrayList<>();
		Connection c = connectToDB(DatabaseProperties.URL(),DatabaseProperties.USERNAME(),DatabaseProperties.PASSWORD());
		Statement s = c.createStatement();
		s.execute(sqlRequest);
		ResultSet resultSet = s.getResultSet();
		while(resultSet.next())
		{ 
			answer.add(resultSet.getString("timeInString"));
		}
		c.close();
		return answer;
	}
	
	public static int getInt(String sqlRequest) throws SQLException
	{
		int answer = Integer.MIN_VALUE;
		Connection c = connectToDB(DatabaseProperties.URL(),DatabaseProperties.USERNAME(),DatabaseProperties.PASSWORD());
		Statement s = c.createStatement();
		s.execute(sqlRequest);
		ResultSet resultSet = s.getResultSet();
		while(resultSet.next())
		{
			
			answer = resultSet.getInt(1);
		}
		c.close();
		return answer;
	}
	
	public static void getVoid(String sqlRequest) throws SQLException
	{
		Connection c = connectToDB(DatabaseProperties.URL(),DatabaseProperties.USERNAME(),DatabaseProperties.PASSWORD());
		Statement s = c.createStatement();
		s.execute(sqlRequest);
		c.close();
	}
}
