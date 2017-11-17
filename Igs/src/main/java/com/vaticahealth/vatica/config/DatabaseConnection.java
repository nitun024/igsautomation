package com.vaticahealth.vatica.config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Connection.*;

public class DatabaseConnection {

	public static Connection connectDB() {

		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			DriverManager.setLoginTimeout(40);
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:3390;databasename=staging_cas", "devmobile",
					"ZesC8X88xB8TICm$");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
}
