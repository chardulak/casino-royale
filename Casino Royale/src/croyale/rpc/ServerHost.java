package croyale.rpc;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Vector;

import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

import croyale.Constants;
import croyale.db.Database;
import croyale.security.keyagreement.DHKeyAgreement;
import croyale.server.ClientSession;
import croyale.util.ToHexString;

@SuppressWarnings("serial")
public class ServerHost extends UnicastRemoteObject implements ServerHostInterface, Constants
{
	private static final int MAX_SESSION_COUNT = 100000;
	
	private Database db;
	private int session_count;
	private Hashtable<Integer, ClientSession> active_sessions = new Hashtable<Integer, ClientSession>();
	private Vector<Integer> active_ids = new Vector<Integer>();
	
	public ServerHost(Database db) throws RemoteException
	{
		super(1099);
		this.db = db;
		session_count = 0;
		active_sessions.clear();
		active_ids.clear();
	}
	
	public int createSession() throws RemoteException
	{
		System.out.println("Calling createSession method");
		try {
			int ret = session_count;
			active_sessions.put(session_count, new ClientSession(db));
			System.out.println("\tSession created with id " + session_count);
			session_count++;
			if( session_count > MAX_SESSION_COUNT )
			{
				session_count = 0;
			}
			return ret;
		} catch(Exception e){
			e.printStackTrace();
			return SESSION_CREATE_FAIL;
		}
	}
	
	public byte[] doKeyAgree(int session_id, byte[] client_key) throws RemoteException
	{
		System.out.println("Calling doKeyAgree method for session " + session_id);
		try{
			DHKeyAgreement key_agree = new DHKeyAgreement();
			byte[] public_key = key_agree.getPublicKeyEncoded();
			key_agree.generateSecretKey(client_key);
			SecretKey secret_key = key_agree.getSecretKey();
			
			System.out.println("Client public key: " + ToHexString.toHexString(client_key));
			System.out.println("Server public key: " + ToHexString.toHexString(public_key));
			
			System.out.println("Secret key: " + ToHexString.toHexString(secret_key.getEncoded()));
			
			ClientSession session = active_sessions.get(new Integer(session_id));
			
			if( session != null )
			{
				session.setSecretKey(this, secret_key);
				return public_key;
			}
			else
			{
				return null;
			}
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public SealedObject checkPlayer(int session_id, SealedObject sealed_user_id, SealedObject sealed_password) throws RemoteException
	{
		System.out.println("Calling check player method for session " + session_id);
		try {
			return active_sessions.get(new Integer(session_id)).checkPlayer(sealed_user_id, sealed_password);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	// used by ClientSession
	// returns USER_LOGGED_IN if id is already in active_ids
	// otherwise returns ID_AVAILABLE
	public int addActiveID(int id)
	{
		if( active_ids.contains(id) )
		{
			return USER_LOGGED_IN;
		}
		else
		{
			active_ids.add(id);
			return ID_AVAILABLE;
		}
	}
	
	public SealedObject getPlayer(int session_id, SealedObject sealed_id) throws RemoteException
	{
		System.out.println("Calling getPlayer method for session " + session_id);
		try {
			return active_sessions.get(new Integer(session_id)).getPlayer(sealed_id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public SealedObject getUserBalance(int session_id, SealedObject sealed_id) throws RemoteException
	{
		System.out.println("Calling getUserBalance method for session " + session_id);
		try {
			return active_sessions.get(new Integer(session_id)).getUserBalance(sealed_id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void setAddress(int session_id, SealedObject sealed_id, SealedObject sealed_address) throws RemoteException
	{
		System.out.println("Calling setAddress method for session " + session_id);
		try {
			active_sessions.get(new Integer(session_id)).setAddress(sealed_id, sealed_address);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setBalance(int session_id, SealedObject sealed_id, SealedObject sealed_balance) throws RemoteException
	{
		System.out.println("Calling setBalance method for session " + session_id);
		try {
			active_sessions.get(new Integer(session_id)).setBalance(sealed_id, sealed_balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setEmail(int session_id, SealedObject sealed_id, SealedObject sealed_email) throws RemoteException
	{
		System.out.println("Calling setEmail method for session " + session_id);
		try {
			active_sessions.get(new Integer(session_id)).setEmail(sealed_id, sealed_email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setFirstName(int session_id, SealedObject sealed_id, SealedObject sealed_firstname) throws RemoteException
	{
		System.out.println("Calling setFirstName method for session " + session_id);
		try {
			active_sessions.get(new Integer(session_id)).setFirstName(sealed_id, sealed_firstname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setLastName(int session_id, SealedObject sealed_id, SealedObject sealed_lastname) throws RemoteException
	{
		System.out.println("Calling setLastName method for session " + session_id);
		try {
			active_sessions.get(new Integer(session_id)).setLastName(sealed_id, sealed_lastname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPassword(int session_id, SealedObject sealed_id, SealedObject sealed_password) throws RemoteException
	{
		System.out.println("Calling setPassword method for session " + session_id);
		try {
			active_sessions.get(new Integer(session_id)).setPassword(sealed_id, sealed_password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPhone(int session_id, SealedObject sealed_id, SealedObject sealed_phone) throws RemoteException
	{
		System.out.println("Calling setPhone method for session " + session_id);
		try {
			active_sessions.get(new Integer(session_id)).setPhone(sealed_id, sealed_phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPlayer(int session_id, SealedObject sealed_id, SealedObject sealed_firstname, SealedObject sealed_lastname, SealedObject sealed_user_id, SealedObject sealed_password, SealedObject sealed_address, SealedObject sealed_phone, SealedObject sealed_email, SealedObject sealed_balance) throws RemoteException
	{
		System.out.println("Caling setPlayer method for session " + session_id);
		try {
			active_sessions.get(new Integer(session_id)).setPlayer(sealed_id, sealed_firstname, sealed_lastname, sealed_user_id, sealed_password, sealed_address, sealed_phone, sealed_email, sealed_balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setUserID(int session_id, SealedObject sealed_id, SealedObject sealed_user_id) throws RemoteException
	{
		System.out.println("Calling setUserID method for session " + session_id);
		try {
			active_sessions.get(new Integer(session_id)).setUserID(sealed_id, sealed_user_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void logout(int session_id, SealedObject sealed_id)
	{
		System.out.println("Calling logout method for session " + session_id);
		try {
			active_sessions.get(new Integer(session_id)).logout(sealed_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeActiveID(int id)
	{
		active_ids.remove(id);
	}
	
	public void deleteSession(int session_id) throws RemoteException
	{
		System.out.println("Calling deleteSession method for session " + session_id);
		try {
			active_sessions.remove(new Integer(session_id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
