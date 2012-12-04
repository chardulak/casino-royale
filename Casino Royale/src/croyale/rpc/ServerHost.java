package croyale.rpc;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

import croyale.db.Database;
import croyale.security.CRCipher;
import croyale.security.keyagreement.DHKeyAgreement;
import croyale.util.ToHexString;

@SuppressWarnings("serial")
public class ServerHost extends UnicastRemoteObject implements ServerHostInterface
{
	private static final int USER_DNE = 0;
	private static final int USER_LOGGED_IN = -1;
	
	private Database db;
	private SecretKey secret_key;
	private Vector<Integer> active_ids = new Vector<Integer>();
	
	public ServerHost(Database db) throws RemoteException
	{
		super(1099);
		this.db = db;
		active_ids.clear();
	}
	
	// Uses the public key from the client to generate a shared secret key
	public byte[] doKeyAgree(byte[] client_key) throws RemoteException {
		System.out.println("Calling doKeyAgree method");
		DHKeyAgreement key_agree = new DHKeyAgreement();
		byte[] public_key = key_agree.getPublicKeyEncoded();
		key_agree.generateSecretKey(client_key);
		secret_key = key_agree.getSecretKey();
		
		System.out.println("Client public key: " + ToHexString.toHexString(client_key));
		System.out.println("Server public key: " + ToHexString.toHexString(public_key));
		
		System.out.println("Secret key: " + ToHexString.toHexString(secret_key.getEncoded()));
		
		return public_key;
	}
	
	public SealedObject getUserBalance(SealedObject sealed_id) throws RemoteException
	{
		System.out.println("Calling getUserBalance method");
		try {
			int id = (int)CRCipher.decrypt(secret_key, sealed_id);
			
			System.out.println("\tID: " + id);
			
			return CRCipher.encrypt(secret_key, db.getBalance(id));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public SealedObject checkPlayer(SealedObject sealed_user_id, SealedObject sealed_password)throws RemoteException
	{
		System.out.println("Calling check player method");
		try {
			int ret;
			
			String user_id = (String)CRCipher.decrypt(secret_key, sealed_user_id);
			String password = (String)CRCipher.decrypt(secret_key, sealed_password);
			
			System.out.println("\tID: " + user_id + " Password: " + password);
			
			int id = db.checkPlayer(user_id, password);
			
			// If user exists
			if( id > 0 )
			{
				// If user already logged in
				if( active_ids.contains(new Integer(id)) )
				{
					ret = USER_LOGGED_IN;
				}
				else
				{
					ret = id;
				}
			}
			else
			{
				ret = USER_DNE;
			}
			
			return CRCipher.encrypt(secret_key, ret);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void setBalance(SealedObject sealed_id, SealedObject sealed_balance) throws RemoteException
	{
		System.out.println("Calling setBalance method");
		try {
			int id = (int)CRCipher.decrypt(secret_key, sealed_id);
			String balance = (String)CRCipher.decrypt(secret_key, sealed_balance);
			
			System.out.println("\tID: " + id + " Balance: " + balance);
			
			db.setBalance(id, balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPlayer(SealedObject sealed_id, SealedObject sealed_firstname, SealedObject sealed_lastname, SealedObject sealed_user_id, SealedObject sealed_password, SealedObject sealed_address, SealedObject sealed_phone, SealedObject sealed_email, SealedObject sealed_balance) throws RemoteException
	{
		System.out.println("Caling setPlayer method");
		try {
			int id = (int)CRCipher.decrypt(secret_key, sealed_id);
			String firstname = (String)CRCipher.decrypt(secret_key, sealed_firstname);
			String lastname = (String)CRCipher.decrypt(secret_key, sealed_lastname);
			String user_id = (String)CRCipher.decrypt(secret_key, sealed_user_id);
			String password = (String)CRCipher.decrypt(secret_key, sealed_password);
			String address = (String)CRCipher.decrypt(secret_key, sealed_address);
			String phone = (String)CRCipher.decrypt(secret_key, sealed_phone);
			String email = (String)CRCipher.decrypt(secret_key, sealed_email);
			String balance = (String)CRCipher.decrypt(secret_key, sealed_balance);
			
			System.out.println("\tID: " + id);
			System.out.println("\tFirstname: " + firstname);
			System.out.println("\tLastname: " + lastname);
			System.out.println("\tID: " + user_id);
			System.out.println("\tPassword: " + password);
			System.out.println("\tAddress: " + address);
			System.out.println("\tPhone: " + phone);
			System.out.println("\tEmail: " + email);
			System.out.println("\tBalance: " + balance);
			
			db.setPlayer(id, firstname, lastname, user_id, password, address, phone, email, balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SealedObject getPlayer(SealedObject sealed_id) throws RemoteException
	{
		System.out.println("Calling getPlayer method");
		try {
			int id = (int)CRCipher.decrypt(secret_key, sealed_id);
			
			System.out.println("\tID: " + id);
			
			return CRCipher.encrypt(secret_key, db.getPlayer(id));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void setAddress(SealedObject sealed_id, SealedObject sealed_address) throws RemoteException
	{
		System.out.println("Calling setAddress method");
		try {
			int id = (int)CRCipher.decrypt(secret_key, sealed_id);
			String address = (String)CRCipher.decrypt(secret_key, sealed_address);
			
			System.out.println("\tID: " + id + " Address: " + address);
			
			db.setAddress(id, address);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setEmail(SealedObject sealed_id, SealedObject sealed_email) throws RemoteException
	{
		System.out.println("Calling setEmail method");
		try {
			int id = (int)CRCipher.decrypt(secret_key, sealed_id);
			String email = (String)CRCipher.decrypt(secret_key, sealed_email);
			
			System.out.println("\tID: " + id + " Email: " + email);
			
			db.setEmail(id, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setFirstName(SealedObject sealed_id, SealedObject sealed_firstname) throws RemoteException
	{
		System.out.println("Calling setFirstName method");
		try {
			int id = (int)CRCipher.decrypt(secret_key, sealed_id);
			String firstname = (String)CRCipher.decrypt(secret_key, sealed_firstname);
			
			System.out.println("\tID: " + id + " Firstname: " + firstname);
			
			db.setFirstName(id, firstname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setLastName(SealedObject sealed_id, SealedObject sealed_lastname) throws RemoteException
	{
		System.out.println("Calling setLastName method");
		try {
			int id = (int)CRCipher.decrypt(secret_key, sealed_id);
			String lastname = (String)CRCipher.decrypt(secret_key, sealed_lastname);
			
			System.out.println("\tID: " + id + " Lastname: " + lastname);
			
			db.setLastName(id, lastname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPassword(SealedObject sealed_id, SealedObject sealed_password) throws RemoteException
	{
		System.out.println("Calling setPassword method");
		try {
			int id = (int)CRCipher.decrypt(secret_key, sealed_id);
			String password = (String)CRCipher.decrypt(secret_key, sealed_password);
			
			System.out.println("\tID: " + id + " Password: " + password);
			
			db.setPassword(id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPhone(SealedObject sealed_id, SealedObject sealed_phone) throws RemoteException
	{
		System.out.println("Calling setPhone method");
		try {
			int id = (int)CRCipher.decrypt(secret_key, sealed_id);
			String phone = (String)CRCipher.decrypt(secret_key, sealed_phone);
			
			System.out.println("\tID: " + id + " Phone: " + phone);
			
			db.setPhone(id, phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setUserID(SealedObject sealed_id, SealedObject sealed_user_id) throws RemoteException
	{
		System.out.println("Calling setUserID method");
		try {
			int id = (int)CRCipher.decrypt(secret_key, sealed_id);
			String userid = (String)CRCipher.decrypt(secret_key, sealed_user_id);
			
			System.out.println("\tID: " + id + " UserID: " + userid);
			
			db.setUserID(id, userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
