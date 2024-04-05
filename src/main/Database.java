package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private Connection connection;
	private String usernameDB;
	private String ingameUser;
	private String mailDB;
	private String passwordDB;
	
	public void connect(String sql) {
        String url = "jdbc:mysql://192.168.178.47:3306/boulderdash-db";
        String user = "root";
        String password = "admin";

        // Verbindung herstellen
        try {
        	connection = DriverManager.getConnection(url, user, password);
            System.out.println("Verbindung zur Datenbank hergestellt.");

            // Hier kannst du SQL-Abfragen ausführen
            // Statement statement = connection.createStatement();
            // ResultSet resultSet = statement.executeQuery("SELECT * FROM deineTabelle");

            // Ein Statement-Objekt erstellen
            Statement statement = connection.createStatement();

            // Die Abfrage ausführen
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                int userid = resultSet.getInt("userid");
                usernameDB = resultSet.getString("username");
                mailDB = resultSet.getString("mail");
                passwordDB = resultSet.getString("pw");
                // Weitere Spalten entsprechend deiner Tabelle

                //System.out.println("ID: " + userid + ", Username: " + usernameDB + ", Email: " + mailDB + " Password: " + passwordDB);
            }

        } catch (SQLException e) {
            System.err.println("Fehler beim Verbinden zur Datenbank: " + e.getMessage());
        }
    }
	
	public void disconnect() {
		try {
	        if (connection != null) {
	        	connection.close();
	            System.out.println("Verbindung zur Datenbank getrennt.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Fehler beim Trennen der Verbindung zur Datenbank: " + e.getMessage());
	    }
	}
	
	public boolean ueberpruefe(String nameOrMail, String password) {
		String SQL = "select * from users where username = " + '"' + nameOrMail + '"' + "OR mail = " +'"'+ nameOrMail +'"'+ " AND pw = " +'"'+ password+'"';
		connect(SQL);
		if(usernameDB != null || mailDB != null || passwordDB != null) {
			if(usernameDB.equals(nameOrMail) && passwordDB.equals(password)|| mailDB.equals(nameOrMail) && passwordDB.equals(password)) {
				System.out.println("Eingabe stimmt überein.");
				disconnect();
				ingameUser = usernameDB;
				return true;
			}
		}
		System.out.println("Eingabe Stimmt nicht überein");
		disconnect();
		return false;
	}
	
	public boolean registry(String name, String password) {
	    String valuesCheck = "SELECT * FROM users WHERE username = ? OR mail = ?";
	    
	    // Verbindung zur Datenbank herstellen
	    connect(valuesCheck);

	    try {
	        // Überprüfen, ob der Benutzer bereits existiert
	        PreparedStatement checkStatement = connection.prepareStatement(valuesCheck);
	        checkStatement.setString(1, name);
	        checkStatement.setString(2, name); // Assuming name is used for both username and mail
	        ResultSet resultSet = checkStatement.executeQuery();
	        
	        if (resultSet.next()) {
	            System.out.println("Der Benutzer existiert bereits!");
	            return false;
	        }

	        // Benutzer registrieren
	        String SQL = "INSERT INTO users (username, pw) VALUES (?, ?)";
	        PreparedStatement statement = connection.prepareStatement(SQL);
	        statement.setString(1, name);
	        statement.setString(2, password);
	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Der Benutzer wurde erfolgreich angelegt.");
	            return true;
	        } else {
	            System.out.println("Fehler beim Anlegen des Benutzers.");
	            return false;
	        }
	    } catch (SQLException e) {
	        System.err.println("Fehler beim Ausführen der Datenbankabfrage: " + e.getMessage());
	        return false;
	    } finally {
	        // Verbindung trennen, unabhängig vom Erfolg oder Misserfolg der Operation
	        disconnect();
	    }
	}

	
	public void test() {
		String SQL = "INSERT INTO users (username, pw) values ('k', 'k');";
		connect(SQL);
	}
		
	public String getIngameUser() {
		return ingameUser;
	}
}
