package croyale.rpc;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.crypto.SealedObject;

public interface ServerHostInterface extends Remote
{
	public byte[] doKeyAgree(byte[] public_key) throws RemoteException;
	public SealedObject getUserBalance(SealedObject sealed_id) throws RemoteException;
	public SealedObject checkPlayer(SealedObject sealed_user_id, SealedObject sealed_password) throws RemoteException;
	public void setBalance(SealedObject sealed_id, SealedObject sealed_balance) throws RemoteException;
	public void setPlayer(SealedObject sealed_id, SealedObject sealed_firstname, SealedObject sealed_lastname, SealedObject sealed_user_id, SealedObject sealed_password, SealedObject sealed_address, SealedObject sealed_phone, SealedObject sealed_email, SealedObject sealed_balance) throws RemoteException;
	public SealedObject getPlayer(SealedObject sealed_id) throws RemoteException;
	
	public void setAddress(SealedObject sealed_id, SealedObject sealed_address) throws RemoteException;
	public void setEmail(SealedObject sealed_id, SealedObject sealed_email) throws RemoteException;
	public void setFirstName(SealedObject sealed_id, SealedObject sealed_firstname) throws RemoteException;
	public void setLastName(SealedObject sealed_id, SealedObject sealed_lastname) throws RemoteException;
	public void setPassword(SealedObject sealed_id, SealedObject sealed_password) throws RemoteException;
	public void setPhone(SealedObject sealed_id, SealedObject sealed_phone) throws RemoteException;
	public void setUserID(SealedObject sealed_id, SealedObject sealed_user_id) throws RemoteException;
}