package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	public void connection() {
    	// Verbindungszeichenfolge erstellen
        String url = "jdbc:mysql://192.168.178.47:3306/sys";
        String user = "root";
        String password = "boulderdash-mysql";

        // Verbindung herstellen
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Verbindung zur Datenbank hergestellt.");

            // Hier kannst du SQL-Abfragen ausführen
            // Zum Beispiel:
            // Statement statement = connection.createStatement();
            // ResultSet resultSet = statement.executeQuery("SELECT * FROM deineTabelle");
            String sql = "SELECT * FROM users";

            // Ein Statement-Objekt erstellen
            Statement statement = connection.createStatement();

            // Die Abfrage ausführen
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                int userid = resultSet.getInt("userid");
                String username = resultSet.getString("username");
                String mail = resultSet.getString("mail");
                // Weitere Spalten entsprechend deiner Tabelle

                System.out.println("ID: " + userid + ", Username: " + username + ", Email: " + mail);
                // Weitere Spalten ausgeben, falls vorhanden
            }

        } catch (SQLException e) {
            System.err.println("Fehler beim Verbinden zur Datenbank: " + e.getMessage());
        }
    }
}
