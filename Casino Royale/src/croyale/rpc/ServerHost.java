package croyale.rpc;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;

import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

import croyale.Database;
import croyale.security.CRCipher;
import croyale.security.keyagreement.DHKeyAgreement;

@SuppressWarnings("serial")
public class ServerHost extends UnicastRemoteObject implements ServerHostInterface
{
	private Database db;
	private SecretKey secret_key;
	
	public ServerHost(Database db) throws RemoteException
	{
		super(1099);
		this.db = db;
	}
	
	// Uses the public key from the client to generate a shared secret key
	public byte[] doKeyAgree(byte[] client_key) throws RemoteException {
		System.out.println("Calling doKeyAgree method");
		DHKeyAgreement key_agree = new DHKeyAgreement();
		byte[] public_key = key_agree.getPublicKeyEncoded();
		key_agree.generateSecretKey(client_key);
		secret_key = key_agree.getSecretKey();
		
		System.out.println("Client public key: " + toHexString(client_key));
		System.out.println("Server public key: " + toHexString(public_key));
		
		System.out.println("Secret key: " + toHexString(secret_key.getEncoded()));
		
		return public_key;
	}
	
	public double getUserBalance(SealedObject sealed_user_id) throws RemoteException
	{
		System.out.println("Calling getUserBalance method");
		try {
			int user_id = (int)CRCipher.decrypt(secret_key, sealed_user_id);
			
			System.out.println("\tUser id: " + user_id);
			
			return db.getBalance(user_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int checkPlayer(SealedObject sealed_user_id, SealedObject sealed_password)throws RemoteException
	{
		System.out.println("Calling check player method");
		try {
			System.out.println("Secret key: " + toHexString(secret_key.getEncoded()));
			String user_id = (String)CRCipher.decrypt(secret_key, sealed_user_id);
			String password = (String)CRCipher.decrypt(secret_key, sealed_password);
			
			System.out.println("\tUser id: " + user_id + " Password: " + password);
			
			return db.checkPlayer(user_id, password);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	//TODO return something and set to -1 if error?
	public void setBalance(SealedObject sealed_user_id, SealedObject sealed_balance) throws RemoteException
	{
		System.out.println("Calling setBalance method");
		try {
			int user_id = (int)CRCipher.decrypt(secret_key, sealed_user_id);
			String balance = (String)CRCipher.decrypt(secret_key, sealed_balance);
			
			System.out.println("\tUser id: " + user_id + " Balance: " + balance);
			
			db.setBalance(user_id, balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO return something and set to -1 if error?
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
			System.out.println("\tUser id: " + user_id);
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
	
	public ResultSet getPlayer(SealedObject sealed_id) throws RemoteException
	{
		System.out.println("Calling getPlayer method");
		try {
			int id = (int)CRCipher.decrypt(secret_key, sealed_id);
			
			System.out.println("\tID: " + id);
			
			return db.getPlayer(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Converts a byte to hex digit and writes to the supplied buffer
	private static void byte2hex(byte b, StringBuffer buf) {
		char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		int high = ((b & 0xf0) >> 4);
		int low = (b & 0x0f);
		buf.append(hexChars[high]);
		buf.append(hexChars[low]);
	}
	
	// Converts a byte array to hex string
	private static String toHexString(byte[] block) {
		StringBuffer buf = new StringBuffer();
		int len = block.length;
		
		for (int i = 0; i < len; i++) {
			byte2hex(block[i], buf);
			if (i < len-1) {
				buf.append(":");
			}
		} 
		return buf.toString();
	}
}
