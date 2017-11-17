package com.igs.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vaticahealth.vatica.elements.Queries;

public class DatabaseConnect {

	public ArrayList<String> getResultfromDB(String Query, String Column) {

		ArrayList<String> lst = new ArrayList<String>();
		Connection conn = Queries.connectDB();
		Statement sta;
		try {
			sta = conn.createStatement();

			String Sql = Query;
			String col = Column;
			ResultSet rs = sta.executeQuery(Sql);
			while (rs.next()) {
				lst.add("");
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lst;

	}

}
