package myApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// interact with database
public enum PlayerDAO {
	instance;

	String name = null;

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;

	PlayerGUI gui = new PlayerGUI();

	public void db_init() {
		try {
			// driver
			Class.forName("org.hsqldb.jdbcDriver");

			// connector
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "SA", "Passw0rd");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void db_close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createPlayer(Player p) {
		db_init();

		try {

			// statement
			statement = connection.createStatement();

			// resultSet
			statement.executeUpdate("INSERT INTO players VALUES(" + p.getPlayer_id() + ", '" + p.getName() + "', " + p.getAge()
					+ ", '" + p.getGender() + "', '" + p.getNationality() + "',' " + p.getClub() + "', "
					+ p.getAppearances() + ", " + p.getGoals() + ", " + p.getAssists() + ");");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db_close();
		}
	}

	public void deletePlayerbyID(int id) {
		db_init();

		try {

			// statement
			statement = connection.createStatement();

			// resultSet
			statement.executeUpdate("delete from players where player_id = " + id + ";");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db_close();
		}
	}

	public String viewAllPlayers() {
		StringBuilder sb = new StringBuilder();

		db_init();

		try {

			// statement
			statement = connection.createStatement();

			// resultSet
			resultSet = statement.executeQuery("select * from players;");

			while (resultSet.next()) {
				sb.append(resultSet.getString("name")).append("\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db_close();
		}
		return sb.toString();
	}

	public String viewPlayerByID(int id) {
		StringBuilder sb = new StringBuilder();

		db_init();

		try {
			// statement
			statement = connection.createStatement();

			// resultSet
			resultSet = statement.executeQuery("select * from players where player_id = " + id + ";");

			while (resultSet.next()) {
				sb.append(resultSet.getString("name")).append("\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db_close();
		}
		return sb.toString();
	}
}
