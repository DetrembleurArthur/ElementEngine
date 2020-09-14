package game.mysql;

import com.mysql.cj.log.Log;
import game.jgengine.debug.Logs;

import java.sql.*;

public class Mysql
{
	private static Connection connection;

	public static boolean connect(String ip, int port, String database, String timezone, String username, String password)
	{
		String url = "jdbc:mysql://"+ ip + ":" + port + "/" + database + "?serverTimezone=" + timezone;
		try
		{
			Mysql.connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean connect(String database, String password)
	{
		return connect("localhost", 3306, database, "UTC", "root", password);
	}

	public static boolean connected()
	{
		return connection != null;
	}

	public static void disconnect()
	{
		try
		{
			connection.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static ResultSet execute(String query)
	{
		ResultSet resultSet = null;
		Statement statement = null;
		try
		{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}


		return resultSet;
	}

	public static Connection getConnection()
	{
		return connection;
	}
}
