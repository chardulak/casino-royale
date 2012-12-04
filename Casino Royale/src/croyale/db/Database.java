package croyale.db;

import java.sql.*;

import croyale.util.Player;

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
	public Player getPlayer(int _id)throws SQLException{
		CallableStatement cmst = conn.prepareCall("{call getPlayer(?)}");
		cmst.setInt(1,_id);
		cmst.execute();
		
		
		ResultSet rs = cmst.getResultSet();
		Player player;
		if (rs.next()){
			player = new Player(Integer.parseInt(rs.getString(1).trim()), rs.getString(2).trim(), rs.getString(3).trim(), rs.getString(4).trim(), rs.getString(5).trim(), Double.parseDouble(rs.getString(6).trim()), rs.getString(7).trim(), rs.getString(8).trim(), rs.getString(9).trim());
		}
		else {
			player = new Player(0 ,"FName", "LName", "aaa", "pwd", 1234567890, "1234 Some Road", "uhuhilikeit", "name@domain");
		}
		return player;
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
	public double getBalance(int _id)throws SQLException{
		java.sql.ResultSet rs = null;
		double balance;
		CallableStatement cmst = conn.prepareCall("{call getBalance(?)}");
		cmst.setInt(1,_id);
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
	public void setBalance(int _id,String _balance)throws SQLException{
		CallableStatement cmst = conn.prepareCall("{call setBalance(?,?)}");
		cmst.setInt(1,_id);
		cmst.setString(2,_balance);
		cmst.execute();
	}
	public void setAddress(int _id,String _address)throws SQLException{
		CallableStatement cmst = conn.prepareCall("{call setAddress(?,?)}");
		cmst.setInt(1,_id);
		cmst.setString(2,_address);
		cmst.execute();
	}
	public void setEmail(int _id,String _email)throws SQLException{
		CallableStatement cmst = conn.prepareCall("{call setEmail(?,?)}");
		cmst.setInt(1,_id);
		cmst.setString(2,_email);
		cmst.execute();
	}
	public void setFirstName(int _id,String _firstname)throws SQLException{
		CallableStatement cmst = conn.prepareCall("{call setFirstName(?,?)}");
		cmst.setInt(1,_id);
		cmst.setString(2,_firstname);
		cmst.execute();
	}
	public void setLastName(int _id,String _lastname)throws SQLException{
		CallableStatement cmst = conn.prepareCall("{call setLastName(?,?)}");
		cmst.setInt(1,_id);
		cmst.setString(2,_lastname);
		cmst.execute();
	}
	public void setPassword(int _id,String _password)throws SQLException{
		CallableStatement cmst = conn.prepareCall("{call setPassword(?,?)}");
		cmst.setInt(1,_id);
		cmst.setString(2,_password);
		cmst.execute();
	}
	public void setPhone(int _id,String _phone)throws SQLException{
		CallableStatement cmst = conn.prepareCall("{call setPhone(?,?)}");
		cmst.setInt(1,_id);
		cmst.setString(2,_phone);
		cmst.execute();
	}
	public void setUserID(int _id,String _userid)throws SQLException{
		CallableStatement cmst = conn.prepareCall("{call setUserID(?,?)}");
		cmst.setInt(1,_id);
		cmst.setString(2,_userid);
		cmst.execute();
	}
}

