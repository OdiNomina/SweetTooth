package com.github.sweettooth.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.github.sweettooth.shared.api.logging.Loggable;

public class ExampleAccessJDBC implements Loggable {
	private final Logger logger;
	private final String jdbcURL;
	
	public ExampleAccessJDBC() {
		logger = Logger.getLogger(ExampleAccessJDBC.class.getName());
		jdbcURL = "jdbc:h2:file:~/.SweetTooth/h2/SweetToothDB";
	}
	
	// Ist die Datenbank als solche noch nicht angelegt, legt H2 sie automatisch an.
	public void testAccessDB() {
		try( Connection con = DriverManager.getConnection( jdbcURL, "ST", "")) {
			Statement stmt = con.createStatement();
			
			if( !con.getMetaData().getTables(null, null, "TEXTOUTPUT", null).next() ) {
				String[] sqlStmts = {
					"CREATE TABLE TEXTOUTPUT (" // H2 speichert intern alle Tabellennamen in Großbuchstaben
						+ "ID INTEGER NOT NULL PRIMARY KEY,"
						+ "DESCRIPTION VARCHAR(255),"
						+ "ENGLISH VARCHAR(255),"
						+ "GERMAN VARCHAR(255))",
					"INSERT INTO textoutput VALUES("
						+ "0,"
						+ "'LabelOutput',"
						+ "'Test: Output in English',"
						+ "'Test: Ausgabe auf Deutsch')"
				};
				for(String sql : sqlStmts) {
					stmt.executeUpdate(sql);
				}
				info("H2 Datenbank: Testtabelle und Testdaten neu angelegt");
			}
			
			ResultSet rs = stmt.executeQuery( "SELECT * FROM textoutput" );
			while ( rs.next() )
				System.out.printf("Testoutput: %d, %s, %s, %s", rs.getInt(1) ,rs.getString(2),rs.getString(3), rs.getString(4));
		}
		catch( SQLException ex ) {
			error("An error occurs when accessing H2-DB.",ex);
		}
	}
	
	@Override
	public Logger getLogger() {
		return logger;
	}
}
