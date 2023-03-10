package player_rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public enum PlayerDAO {

	instance;

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	PlayerView view = new PlayerView();

	// ----------DB CONNECTIONS----------\\
	public void startDBConnection() {
		try {
			// driver
			Class.forName("org.hsqldb.jdbcDriver");

			// connector
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "SA", "Passw0rd");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void endDBConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ----------CREATE TABLE (FOR VIEW)----------\\
	public void fetchTable(DefaultTableModel model)
	{
		startDBConnection();

		try {
			// statement
			statement = connection.createStatement();
			
			// resultSet
			resultSet = statement.executeQuery("select * from players;");
			while (resultSet.next()) {
			    int id = resultSet.getInt("player_id");
			    String name = resultSet.getString("name");
			    int age = resultSet.getInt("age");
			    String gender = resultSet.getString("gender");
			    String nationality = resultSet.getString("nationality");
			    String club = resultSet.getString("club");
			    int app = resultSet.getInt("appearances");
			    int goals = resultSet.getInt("goals");
			    int assists = resultSet.getInt("assists");
			    Object[] row = {id, name, age, gender, nationality, club, app, goals, assists};
			    model.addRow(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			endDBConnection();
		}
	}

	// ----------CREATE TABLE (FOR VIEW)----------\\
	public void createTableFromDB(DefaultTableModel model) {
		// fetch data from database and create a table from it
		startDBConnection();

		try {
			statement = connection.createStatement();

			resultSet = statement.executeQuery(
					"select player_id, name, age, gender, nationality, club, appearances, goals, assists from players;");

			while (resultSet.next()) {
				int id = resultSet.getInt("player_id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String nationality = resultSet.getString("nationality");
				String club = resultSet.getString("club");
				int app = resultSet.getInt("appearances");
				int goals = resultSet.getInt("goals");
				int assists = resultSet.getInt("assists");
				Object[] row = { id, name, age, gender, nationality, club, app, goals, assists };
				model.addRow(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			endDBConnection();
		}
	}

	// ----------VIEW FUNCTIONALITY----------\\

	public List<Player> viewAllPlayers()
	{
		List<Player> players = new ArrayList<Player>();
		Player p = null;
		
		startDBConnection();
		String query = "select player_id, name, age, gender, nationality, club, appearances, goals, assists from players;";
		try {
			statement = connection.createStatement();

			resultSet = statement.executeQuery(query);

//			int sqlStatus = statement.executeUpdate(query);
//			
//			if(sqlStatus == 0)
//				System.out.println("Query was successfull");
//			else
//				System.out.println("Query was not successfull");
			
			while (resultSet.next()) {
				p = new Player();
				p.setPlayer_id(resultSet.getInt("player_id"));
				p.setName(resultSet.getString("name"));
				p.setAge(resultSet.getInt("age"));
				p.setGender(resultSet.getString("gender").charAt(0));
				p.setNationality(resultSet.getString("nationality"));
				p.setClub(resultSet.getString("club"));
				p.setAppearances(resultSet.getInt("appearances"));
				p.setGoals(resultSet.getInt("goals"));
				p.setAssists(resultSet.getInt("assists"));
				
				players.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			endDBConnection();
		}

		return players;
	}
	
	// These view functions will be used to display information in a text area
	// rather than in table
//	public String viewAllPlayers() {
//		StringBuilder viewAllString = new StringBuilder();
//
//		startDBConnection();
//		
//		String query = "select player_id, name, age, gender, nationality, club, appearances, goals, assists from players;";
//		try {
//			statement = connection.createStatement();
//
//			resultSet = statement.executeQuery(query);
//
////			int sqlStatus = statement.executeUpdate(query);
////			
////			if(sqlStatus == 0)
////				System.out.println("Query was successfull");
////			else
////				System.out.println("Query was not successfull");
//			
//			while (resultSet.next()) {
//				viewAllString.append(resultSet.getString("name")).append("\n");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			endDBConnection();
//		}
//		return viewAllString.toString();
//	}

	public Player viewPlayerByID(int id) {
		Player p = new Player();
		
		startDBConnection();
		String query = "select player_id, name, age, gender, nationality, club, appearances, goals, assists from players where player_id = "+id+";";
		try {
			statement = connection.createStatement();

			resultSet = statement.executeQuery(query);

//			int sqlStatus = statement.executeUpdate(query);
//			
//			if(sqlStatus == 0)
//				System.out.println("Query was successfull");
//			else
//				System.out.println("Query was not successfull");
			
			while (resultSet.next()) {
				p.setPlayer_id(resultSet.getInt("player_id"));
				p.setName(resultSet.getString("name"));
				p.setAge(resultSet.getInt("age"));
				p.setGender(resultSet.getString("gender").charAt(0));
				p.setNationality(resultSet.getString("nationality"));
				p.setClub(resultSet.getString("club"));
				p.setAppearances(resultSet.getInt("appearances"));
				p.setGoals(resultSet.getInt("goals"));
				p.setAssists(resultSet.getInt("assists"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			endDBConnection();
		}

		return p;
	}

	// ----------DELETE FUNCTIONALITY----------\\
	public void deletePlayerbyID(int id) {
		startDBConnection();

		try {
			statement = connection.createStatement();

			statement.executeUpdate("delete from players where player_id = " + id + ";");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			endDBConnection();
		}
	}

	// ----------CREATE FUNCTIONALITY----------\\
	public void createPlayer(Player p) {
		startDBConnection();

		try {
			statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO players VALUES(" + null + ", '" + p.getName() + "', " + p.getAge()
					+ ", '" + p.getGender() + "', '" + p.getNationality() + "',' " + p.getClub() + "', "
					+ p.getAppearances() + ", " + p.getGoals() + ", " + p.getAssists() + ");");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			endDBConnection();
		}
	}

	// ----------UPDATE FUNCTIONALITY----------\\

}
