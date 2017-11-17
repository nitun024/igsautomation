package com.vaticahealth.vatica.elements;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Queries {

	public static final String MasterICDId_MAHcc_Null_RxHcc_Null = "select top 1 Icd.mastericdid FROM MasterICd ICd Left OUter JOin  MasterIcdToMaHcc Ma ON Ma.MastericdId = Icd.MasterIcdID LEFT OUTER JOIN MasterHcc MaHcc ON MaHcc.MasterHccId = Ma.MasterHccId LEFT OUTER JOIN MasterIcdToRxHcc Rx ON Rx.MasterICdId = Icd.MasterIcdId LEFT OUTER JOIN MasterHcc RxHcc ON RxHcc.MasterHccId = Rx.MasterHccId where ICd.isicd10=1 and MaHcc.HccNumber is null and RxHcc.HccNumber is null";
	public static final String MasterICDId_MAHcc_NotNull_RxHcc_Notnull = "select top 1 Icd.mastericdid FROM MasterICd ICd Left OUter JOin  MasterIcdToMaHcc Ma ON Ma.MastericdId = Icd.MasterIcdID LEFT OUTER JOIN MasterHcc MaHcc ON MaHcc.MasterHccId = Ma.MasterHccId LEFT OUTER JOIN MasterIcdToRxHcc Rx ON Rx.MasterICdId = Icd.MasterIcdId LEFT OUTER JOIN MasterHcc RxHcc ON RxHcc.MasterHccId = Rx.MasterHccId where ICd.isicd10=1 and MaHcc.HccNumber <> 1 and RxHcc.HccNumber <> 1";
	public static final String MasterICDId_MAHcc_Null_RxHcc_Notnull = "select top 1 Icd.mastericdid FROM MasterICd ICd Left OUter JOin  MasterIcdToMaHcc Ma ON Ma.MastericdId = Icd.MasterIcdID LEFT OUTER JOIN MasterHcc MaHcc ON MaHcc.MasterHccId = Ma.MasterHccId LEFT OUTER JOIN MasterIcdToRxHcc Rx ON Rx.MasterICdId = Icd.MasterIcdId LEFT OUTER JOIN MasterHcc RxHcc ON RxHcc.MasterHccId = Rx.MasterHccId where ICd.isicd10=1 and MaHcc.HccNumber is null and RxHcc.HccNumber <> 1";
	public static final String MasterICDId_MAHcc_NotNull_RxHcc_null = "select top 1 Icd.mastericdid FROM MasterICd ICd Left OUter JOin  MasterIcdToMaHcc Ma ON Ma.MastericdId = Icd.MasterIcdID LEFT OUTER JOIN MasterHcc MaHcc ON MaHcc.MasterHccId = Ma.MasterHccId LEFT OUTER JOIN MasterIcdToRxHcc Rx ON Rx.MasterICdId = Icd.MasterIcdId LEFT OUTER JOIN MasterHcc RxHcc ON RxHcc.MasterHccId = Rx.MasterHccId where ICd.isicd10=1 and MaHcc.HccNumber <> 1 and RxHcc.HccNumber is null";

	public static final String Roster_MasterICDId_MAHcc_Null_RxHcc_Null = "select top 1 Icd.mastericdid FROM MasterICd ICd Left OUter JOin  MasterIcdToMaHcc Ma ON Ma.MastericdId = Icd.MasterIcdID LEFT OUTER JOIN MasterHcc MaHcc ON MaHcc.MasterHccId = Ma.MasterHccId LEFT OUTER JOIN MasterIcdToRxHcc Rx ON Rx.MasterICdId = Icd.MasterIcdId LEFT OUTER JOIN MasterHcc RxHcc ON RxHcc.MasterHccId = Rx.MasterHccId where ICd.isicd10=1 and MaHcc.HccNumber is null and RxHcc.HccNumber is null  order by mastericdid desc";
	public static final String Roster_MasterICDId_MAHcc_NotNull_RxHcc_Notnull = "select top 1 Icd.mastericdid FROM MasterICd ICd Left OUter JOin  MasterIcdToMaHcc Ma ON Ma.MastericdId = Icd.MasterIcdID LEFT OUTER JOIN MasterHcc MaHcc ON MaHcc.MasterHccId = Ma.MasterHccId LEFT OUTER JOIN MasterIcdToRxHcc Rx ON Rx.MasterICdId = Icd.MasterIcdId LEFT OUTER JOIN MasterHcc RxHcc ON RxHcc.MasterHccId = Rx.MasterHccId where ICd.isicd10=1 and MaHcc.HccNumber <> 1 and RxHcc.HccNumber <> 1  order by mastericdid desc";
	public static final String Roster_MasterICDId_MAHcc_Null_RxHcc_Notnull = "select top 1 Icd.mastericdid FROM MasterICd ICd Left OUter JOin  MasterIcdToMaHcc Ma ON Ma.MastericdId = Icd.MasterIcdID LEFT OUTER JOIN MasterHcc MaHcc ON MaHcc.MasterHccId = Ma.MasterHccId LEFT OUTER JOIN MasterIcdToRxHcc Rx ON Rx.MasterICdId = Icd.MasterIcdId LEFT OUTER JOIN MasterHcc RxHcc ON RxHcc.MasterHccId = Rx.MasterHccId where ICd.isicd10=1 and MaHcc.HccNumber is null and RxHcc.HccNumber <> 1  order by mastericdid desc";
	public static final String Roster_MasterICDId_MAHcc_NotNull_RxHcc_null = "select top 1 Icd.mastericdid FROM MasterICd ICd Left OUter JOin  MasterIcdToMaHcc Ma ON Ma.MastericdId = Icd.MasterIcdID LEFT OUTER JOIN MasterHcc MaHcc ON MaHcc.MasterHccId = Ma.MasterHccId LEFT OUTER JOIN MasterIcdToRxHcc Rx ON Rx.MasterICdId = Icd.MasterIcdId LEFT OUTER JOIN MasterHcc RxHcc ON RxHcc.MasterHccId = Rx.MasterHccId where ICd.isicd10=1 and MaHcc.HccNumber <> 1 and RxHcc.HccNumber is null  order by mastericdid desc";

	public static Connection connectDB() {

		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			DriverManager.setLoginTimeout(40);
			conn = DriverManager.getConnection(
					"jdbc:sqlserver://vaticatest.cloudapp.net:1433;databasename=VaticaHealth_QA", "swsadmin",
					"SeniorWellnessSASpider1!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

}
