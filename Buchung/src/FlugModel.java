import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

public class FlugModel {
	private Connection conn = null;// JDBC fuer das verbinden mit der mySQL-Datenbank
	private boolean connected = false;
	
	private ArrayList<String[]> flugList = new ArrayList<String[]>(); //ArrayList mit den Fluegen
	private HashMap<String,String> land = new HashMap<String,String>(); //HashMap für Land + Landcode z.b AT = Oesterreich
	private HashMap<String,String> flugOrt = new HashMap<String,String>(); //HashMap für Flughafen + Landcode
	
	private boolean checked = false;//false weil am der Flug nicht voll ist.
	public FlugModel(String username,String password,String host,int port) {
		dbCon(username,password,host,port);//Aufruf des Connectors, versuche Verbindung auf zu bauen falls Eingaben korrekt sind
	}
	
	public HashMap<String,String> getLand() throws SQLException {

		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = conn.createStatement();
			myRs= myStmt.executeQuery("select * from countries");

			while (myRs.next()) {
				land.put(myRs.getString("name"),myRs.getString("code"));
			}
			return land;
		}
		finally{
		close(myStmt, myRs);
		}
	}
	public HashMap<String,String> findFlughafen(String code) throws SQLException {

		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = conn.createStatement();
			myRs= myStmt.executeQuery("select * from airports where country =\"" + code + "\"");
			while (myRs.next())
				flugOrt.put(myRs.getString("name"),myRs.getString("airportcode"));
			return flugOrt;
		}
		finally{
		close(myStmt,myRs);
		}
	}
	public void selectFlug(String aircode1, String aircode2) throws SQLException {
		Statement myStmt = null;
		ResultSet myRs = null;
		String select = "SELECT * FROM flights WHERE departure_airport = \"" + aircode1
		        + "\" AND destination_airport = \"" + aircode2 + "\"";
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery(select);
			while(myRs.next()) {
				 
			    String[] arr = { myRs.getString("airline"), myRs.getString("flightnr"),
			    				myRs.getString("departure_time"), myRs.getString("destination_time") };
			    flugList.add(arr);	
			}
		}
		finally {
			close(myStmt,myRs);
		}
	}
	public void passagier(String first,String last,String airline,String flightnr,int rownr,char seat) throws SQLException {
		Statement myStmt = null;
		ResultSet myRs = null;
		PreparedStatement prepared = null;
		String maxID = "Select max(id) on passengers";
		int newID = 0;
		try {
			
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery(maxID);
			if(myRs.next())
				newID = Integer.parseInt(myRs.getString("id"));
			String insert ="insert into passengers(id,firstname,lastname,airline,flightnr,rownr,seatposition)"
					+ "values(?,?,?,?,?,?,?)";
			prepared = conn.prepareStatement(insert);
			prepared.setInt(1, newID+1);
			prepared.setString(2,first);
			prepared.setString(3,last);
			prepared.setString(4,airline);
			prepared.setString(5,flightnr);
			prepared.setInt(6,rownr);
			prepared.setString(7,""+seat);//toString shortcut
			prepared.executeQuery();
		}finally {
			close(myStmt,myRs);
			prepared.close();
		}
		
	}
	public boolean flugCheck(String flightnr,String rownr,String seat) throws SQLException {
		Statement myStmt = null;
		ResultSet myRs = null;
		String select = "select * from passengers where flightnr =\"" +flightnr +"\" and rownr=\"" + rownr +
				"\" and seatposition=\"" + seat +"\"";
		try {
			myStmt = conn.createStatement();
			myRs = myStmt.executeQuery(select);
			if(myRs.next()) 
				checked = true;
			return checked;
		}finally {
			close(myStmt,myRs);
		}
		
	}
	public void dbCon(String username, String password, String host, int port) {
		if(leer(username,password,host,port)) {
			JOptionPane.showMessageDialog(null,"Ein oder mehrere Felder sind leer. \n Der Input ist vielleicht falsch?");
		}else {	
		try {
			// create a mysql database connection
			String url = "jdbc:mysql://" + host + ":" + port + "/";
			String dbName = "flightdata";
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, username, password);
			System.out.println("Connected to the database");
			connected = true;
//			Statement myStat = conn.createStatement();
//			ResultSet myRs = myStat.executeQuery("Select * from airlines");
//			while(myRs.next())
//				System.out.println(myRs.getString("id")+ ","+myRs.getString("name"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Verbindung fehlgeschlagen. "
					+ "\nUeberpruefen sie Ihre Eingabedaten.");
			System.err.println("Exception!");
			System.err.println(e.getMessage());
			connected = false;
		}
		
		}
		
	}
	public static boolean leer(String user,String pw, String host,int port) {
		return (user.isEmpty() || pw.isEmpty() || host.isEmpty() || port == 0);
	}
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	public boolean getConnected() {
		// TODO Auto-generated method stub
		return connected;
	}
	public HashMap getWerte(String g) {
		if(g == "land")
			return land;
		if(g == "flug")
			return flugOrt;
		return null;
	}
}
