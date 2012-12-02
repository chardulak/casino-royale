package croyale.rpc;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

import javax.crypto.SealedObject;

public interface ServerHostInterface extends Remote
{
	public byte[] doKeyAgree(byte[] public_key) throws RemoteException;
	
	public double getUserBalance(SealedObject sealed_user_id) throws RemoteException;
	public int checkPlayer(SealedObject sealed_user_id, SealedObject sealed_password) throws RemoteException;
	public void setBalance(SealedObject sealed_user_id, SealedObject sealed_balance) throws RemoteException;
	public void setPlayer(SealedObject sealed_id, SealedObject sealed_firstname, SealedObject sealed_lastname, SealedObject sealed_user_id, SealedObject sealed_password, SealedObject sealed_address, SealedObject sealed_phone, SealedObject sealed_email, SealedObject sealed_balance) throws RemoteException;
	public ResultSet getPlayer(SealedObject sealed_id) throws RemoteException;
}