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
		DHKeyAgreement key_agree = new DHKeyAgreement();
		byte[] public_key = key_agree.getPublicKeyEncoded();
		key_agree.generateSecretKey(public_key);
		secret_key = key_agree.getSecretKey();
		return public_key;
	}
	
	public double getUserBalance(SealedObject sealed_user_id) throws RemoteException
	{
		try {
			int user_id = (int)CRCipher.decrypt(secret_key, sealed_user_id);
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
			String user_id = (String)CRCipher.decrypt(secret_key, sealed_user_id);
			String password = (String)CRCipher.decrypt(secret_key, sealed_password);
			return db.checkPlayer(user_id, password);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	//TODO return something and set to -1 if error?
	public void setBalance(SealedObject sealed_user_id, SealedObject sealed_balance) throws RemoteException
	{
		try {
			int user_id = (int)CRCipher.decrypt(secret_key, sealed_user_id);
			String balance = (String)CRCipher.decrypt(secret_key, sealed_balance);
			db.setBalance(user_id, balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO return something and set to -1 if error?
	public void setPlayer(SealedObject sealed_id, SealedObject sealed_firstname, SealedObject sealed_lastname, SealedObject sealed_user_id, SealedObject sealed_password, SealedObject sealed_address, SealedObject sealed_phone, SealedObject sealed_email, SealedObject sealed_balance) throws RemoteException
	{
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
			db.setPlayer(id, firstname, lastname, user_id, password, address, phone, email, balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getPlayer(SealedObject sealed_id) throws RemoteException
	{
		try {
			int id = (int)CRCipher.decrypt(secret_key, sealed_id);
			return db.getPlayer(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
