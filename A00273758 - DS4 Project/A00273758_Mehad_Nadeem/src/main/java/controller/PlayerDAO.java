package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Player;

public enum PlayerDAO
{
	instance;
	
	StringBuilder stringBuilder = new StringBuilder();
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	// open & close db connection
	public void initDB()
	{
		try {
			//driver
			Class.forName("org.hsqldb.jdbcDriver");
		
			//connector
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "SA", "Passw0rd");
			
			//statement
			statement = connection.createStatement();
			
			System.out.println("DB Connection Started...");
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// crud methods
	public void createPlayer(Player p)
	{
		
	}
	
	public void getAllPlayers()
	{
		
	}
	
	public void getPlayerByID(int id)
	{
		
	}
	
	public void updatePlayer()
	{
		
	}
	
	public void deletePlayer()
	{
		
	}
	
	// test method to get all stuff for debugging
	public String testDB_getAllPlayers()
	{
		initDB();
		
		try {
			resultSet = statement.executeQuery("select * from players;");
			
			while(resultSet.next())
			{
/*				int id = resultSet.getInt("id");
				int age = resultSet.getInt("age"); 
				int appearances = resultSet.getInt("appearances");
				int goals = resultSet.getInt("goals");
				int assists = resultSet.getInt("assists");
				String name = resultSet.getString("name");
				String nationality = resultSet.getString("nationality");
				String club = resultSet.getString("club");
				char gender = resultSet.getString("gender").charAt(0);*/

				stringBuilder
				.append(resultSet.getInt("id")).append(", ")
				.append(resultSet.getString("name")).append(", ")
				.append(resultSet.getInt("age")).append(", ")
				.append(resultSet.getString("gender").charAt(0)).append(", ")
				.append(resultSet.getString("nationality")).append(", ")
				.append(resultSet.getString("club")).append(", ")
				.append(resultSet.getInt("appearances")).append(", ")
				.append(resultSet.getInt("goals")).append(", ")
				.append(resultSet.getInt("assists")).append(",\n");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("testing db connection...");
		
		return stringBuilder.toString();
	}
}
