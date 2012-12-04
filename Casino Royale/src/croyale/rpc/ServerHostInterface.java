package croyale.rpc;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.crypto.SealedObject;

public interface ServerHostInterface extends Remote
{
	public int createSession() throws RemoteException; // returns session id
	public byte[] doKeyAgree(int session_id, byte[] public_key) throws RemoteException; // returns public key of server or null if no session associated with session_id
	
	public SealedObject checkPlayer(int session_id, SealedObject sealed_userid, SealedObject sealed_password) throws RemoteException;
	
	public SealedObject getPlayer(int session_id, SealedObject sealed_id) throws RemoteException;
	public SealedObject getUserBalance(int session_id, SealedObject sealed_id) throws RemoteException;
	
	public void setAddress(int session_id, SealedObject sealed_id, SealedObject sealed_address) throws RemoteException;
	public void setBalance(int session_id, SealedObject sealed_id, SealedObject sealed_balance) throws RemoteException;
	public void setEmail(int session_id, SealedObject sealed_id, SealedObject sealed_email) throws RemoteException;
	public void setFirstName(int session_id, SealedObject sealed_id, SealedObject sealed_firstname) throws RemoteException;
	public void setLastName(int session_id, SealedObject sealed_id, SealedObject sealed_lastname) throws RemoteException;
	public void setPassword(int session_id, SealedObject sealed_id, SealedObject sealed_password) throws RemoteException;
	public void setPhone(int session_id, SealedObject sealed_id, SealedObject sealed_phone) throws RemoteException;
	public void setPlayer(int session_id, SealedObject sealed_id, SealedObject sealed_firstname, SealedObject sealed_lastname, SealedObject sealed_user_id, SealedObject sealed_password, SealedObject sealed_address, SealedObject sealed_phone, SealedObject sealed_email, SealedObject sealed_balance) throws RemoteException;
	public void setUserID(int session_id, SealedObject sealed_id, SealedObject sealed_user_id) throws RemoteException;
	
	public void deleteSession(int session_id) throws RemoteException;
}
