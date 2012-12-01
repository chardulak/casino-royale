package croyale;

import java.sql.*;

public class Database {
	Connection conn;
    
	public Database(){
	}
	public Database(String _connectString){
		try{
			connectDBase();
		}catch(Exception e){
			System.out.println("Error " + e.toString());
		}
	}
	public String connectDBase() throws ClassNotFoundException, SQLException{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
		conn = DriverManager.getConnection("jdbc:odbc:CN1","teamLogin","atl1929");
		return "Database Connected 11";
	}
	public int checkPlayer(String _userid,String _password)throws SQLException{
		java.sql.ResultSet rs = null;
		int id;
		CallableStatement cmst = conn.prepareCall("{call checkPlayer(?,?)}");
		cmst.setString(1,_userid);
		cmst.setString(2,_password);
		cmst.execute();
		
		rs = cmst.getResultSet();
		try{
			rs.next();
			id = rs.getInt(1);
		}catch (Exception e1){
			id=0;
		}
		return id;
	}
	public java.sql.ResultSet getPlayer(int _id)throws SQLException{
		CallableStatement cmst = conn.prepareCall("{call getPlayer(?)}");
		cmst.setInt(1,_id);
		cmst.execute();
		return cmst.getResultSet();
	}
	public void setPlayer(int _id,String _firstname,String _lastname,String _userid,String _password,String _address,String _phone,String _email,String _balance)throws SQLException{
		CallableStatement cmst = conn.prepareCall("{call setPlayer(?,?,?,?,?,?,?,?,?)}");
		cmst.setInt(1,_id);
		cmst.setString(2,_firstname);
		cmst.setString(3,_lastname);
		cmst.setString(4,_userid);
		cmst.setString(5,_password);
		cmst.setString(6,_address);
		cmst.setString(7,_phone);
		cmst.setString(8,_email);
		cmst.setString(9,_balance);
		cmst.execute();
	}
	public double getBalance(int _userid)throws SQLException{
		java.sql.ResultSet rs = null;
		double balance;
		CallableStatement cmst = conn.prepareCall("{call getBalance(?)}");
		cmst.setInt(1,_userid);
		cmst.execute();
		
		rs = cmst.getResultSet();
		try{
			rs.next();
			balance = rs.getInt(1);
		}catch (Exception e1){
			balance=0;
		}
		return balance;
	}
	public void setBalance(int _userid,String _balance)throws SQLException{
		CallableStatement cmst = conn.prepareCall("{call setBalance(?,?)}");
		cmst.setInt(1,_userid);
		cmst.setString(2,_balance);
		cmst.execute();
	}
}

