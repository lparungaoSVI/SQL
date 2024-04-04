package com.svi.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.svi.config.Config;
import com.svi.model.SaveRequest;

public class RecordsImpl implements RecordsDAO {

//	private static String connectionUrl = Config.DB_URL.getValue();
//	private static String username = Config.USER.getValue();
//	private static String password = Config.PASS.getValue();
//	private static String driver = Config.DRIVER.getValue();

	private static String connectionUrl = "jdbc:mysql://localhost:3306?useSSL=false";
	private static String username = "root";
	private static String password = "Kimjisoo03_";
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static Connection conn;

	public RecordsImpl() {
		openDBConnection();
	}

	@Override
	public void saveRecords(SaveRequest request) {
		try {
			String query = "INSERT INTO tictactoe_db.records (gameKey, gameUID, playerID, location, symbol, dateSave) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, request.getGameKey());
			preparedStatement.setString(2, request.getGameUID());
			preparedStatement.setString(3, request.getPlayerID());
			preparedStatement.setString(4, request.getLocation());
			preparedStatement.setString(5, request.getSymbol());
			preparedStatement.setString(6, request.getDateSave());

			int i = preparedStatement.executeUpdate();
			System.out.println(i + " records inserted");

		} catch (SQLException e) {
			e.printStackTrace(); // Handle exceptions appropriately
		} finally {
			closeDBConnection();
		}
	}

	@Override
	public List<String> getGameRecords(String key) {
		List<String> gameUIDs = new ArrayList<>();
		try {
			String query = "SELECT gameUID FROM tictactoe_db.records WHERE playerID = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, key); // Set the playerID
			try (ResultSet resultSet = statement.executeQuery()) {
				// Iterate over the results and add gameUIDs to the list
				while (resultSet.next()) {
					String gameUID = resultSet.getString("gameUID");
					if (!gameUIDs.contains(gameUID)) {
						gameUIDs.add(gameUID);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle exceptions appropriately
		} finally {
			closeDBConnection(); // Assuming this method is defined to close the database connection
		}
		return gameUIDs;
	}

	@Override
	public List<String> getGameDetails(String key) {
		List<String> gameData = new ArrayList<>();
		try {
			String query = "SELECT * FROM tictactoe_db.records WHERE gameUID = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, key); // Set the gameKey

			try (ResultSet resultSet = statement.executeQuery()) {
				// Iterate over the results
				while (resultSet.next()) {
					// Extract data from the result set row
					String gameKey = resultSet.getString("gameKey");
					String gameUID = resultSet.getString("gameUID");
					String playerID = resultSet.getString("playerID");
					String location = resultSet.getString("location");
					String symbol = resultSet.getString("symbol");
					String dateSave = resultSet.getString("dateSave");

					// Format the data as a string and add it to the list
					String rowData = String.format("%s, %s, %s, %s, %s, %s", gameKey, gameUID, playerID, symbol,
							location, dateSave);
					gameData.add(rowData);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle exceptions appropriately
		} finally {
			closeDBConnection(); // Assuming this method is defined to close the database connection
		}
		return gameData;
	}

	private static void openDBConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(connectionUrl, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void closeDBConnection() {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
