package life.dashyeah.CloudIntelligence.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private static Connection conn = null;
	
	private static Boolean connectDB() {
		try {
			//load driver
			Class.forName("com.mysql.jdbc.Driver");
			
		    conn = DriverManager.getConnection("jdbc:mysql://localhost/cloudintelligence", "root", "2269");
		    System.out.println("[MSG] DB connection got.");
		    
		    return true;
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static Connection getConn() {
		if(conn == null) connectDB();
		
		return conn;
	}
	
	public static void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
