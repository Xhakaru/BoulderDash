package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private Connection connection;
	private String usernameDB;
	private String mailDB;
	private String passwordDB;
	
	public void connect(String sql) {
    	// Verbindungszeichenfolge erstellen
        String url = "jdbc:mysql://192.168.178.47:3306/boulderdash-db";
        String user = "root";
        String password = "admin";

        // Verbindung herstellen
        try {
        	connection = DriverManager.getConnection(url, user, password);
            System.out.println("Verbindung zur Datenbank hergestellt.");

            // Hier kannst du SQL-Abfragen ausführen
            // Zum Beispiel:
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
                // Weitere Spalten ausgeben, falls vorhanden
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
				return true;
			}
		}
		System.out.println("Eingabe Stimmt nicht überein");
		disconnect();
		return false;
	}
	
	public boolean registry(String name, String mail, String password) {
		String SQL = "INSERT INTO users (username, mail, pw) values (" + name + ", " + mail + ", " + password;
		String valuesCheck = "select * from users where username = " + '"' + name + '"' + "OR mail = " +'"'+ mail +'"'+ " AND pw = " +'"'+ password +'"';
		connect(valuesCheck);
		if(usernameDB != null || mailDB != null || passwordDB != null) {
			connect(SQL);
			System.out.println("Der Benutzer wurde erfolgreich angelegt.");
			disconnect();
			return true;
		}
		System.out.println("Der Benutzer existiert bereits!");						//idk if this works xD
		disconnect();
		return false;
	}
}
