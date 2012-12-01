package croyale.rpc;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;

import croyale.Database;

@SuppressWarnings("serial")
public class ServerHost extends UnicastRemoteObject implements ServerHostInterface
{
	private Database db;
	
	public ServerHost(Database db) throws RemoteException
	{
		super(1099);
		this.db = db;
	}
	
	public double getUserBalance(int user_id) throws RemoteException
	{
		try {
			return db.getBalance(user_id);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int checkPlayer(String user_id, String password)throws RemoteException
	{
		System.out.println("Calling check player method");
		try {
			return db.checkPlayer(user_id, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	//TODO return something and set to -1 if error?
	public void setBalance(int user_id, String balance) throws RemoteException
	{
		try {
			db.setBalance(user_id, balance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//TODO return something and set to -1 if error?
	public void setPlayer(int id, String firstname, String lastname, String user_id, String password, String address, String phone, String email, String balance) throws RemoteException
	{
		try {
			db.setPlayer(id, firstname, lastname, user_id, password, address, phone, email, balance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getPlayer(int id) throws RemoteException
	{
		try {
			return db.getPlayer(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}