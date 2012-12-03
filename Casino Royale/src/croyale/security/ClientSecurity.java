package croyale.security;

import java.rmi.RemoteException;

import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

import croyale.Player;
import croyale.rpc.ServerHostInterface;
import croyale.security.digest.SHA256Digest;
import croyale.security.keyagreement.DHKeyAgreement;
import croyale.util.ToHexString;

public class ClientSecurity
{
	private ServerHostInterface shi;
	private SecretKey secret_key;
	
	public ClientSecurity(ServerHostInterface shi)
	{
		this.shi = shi;
	}
	
	public void doKeyAgree() throws RemoteException
	{
		System.out.println("Calling doKeyAgree method");
		
		DHKeyAgreement key_agree = new DHKeyAgreement();
		byte[] server_key = shi.doKeyAgree(key_agree.getPublicKeyEncoded());
		key_agree.generateSecretKey(server_key);
		secret_key = key_agree.getSecretKey();
		
		System.out.println("Client public key: " + ToHexString.toHexString(key_agree.getPublicKeyEncoded()));
		System.out.println("Server public key: " + ToHexString.toHexString(server_key));
		
		System.out.println("Secret key: " + ToHexString.toHexString(secret_key.getEncoded()));
	}
	
	public double getUserBalance(int user_id) throws RemoteException
	{
		try {
			SealedObject sealed_user_id = CRCipher.encrypt(secret_key, user_id);
			return (double)CRCipher.decrypt(secret_key, shi.getUserBalance(sealed_user_id));
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int checkPlayer(String user_id, String password) throws RemoteException
	{
		System.out.println("Calling checkPlayer method");
		try {
			System.out.println("Secret key: " + ToHexString.toHexString(secret_key.getEncoded()));
			SealedObject sealed_user_id = CRCipher.encrypt(secret_key, user_id);
			SealedObject sealed_password = CRCipher.encrypt(secret_key, ToHexString.toHexString(SHA256Digest.digest(password)));
			return (int)CRCipher.decrypt(secret_key, shi.checkPlayer(sealed_user_id, sealed_password));
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public void setBalance(int user_id, String balance) throws RemoteException
	{
		try {
			SealedObject sealed_user_id = CRCipher.encrypt(secret_key, user_id);
			SealedObject sealed_balance = CRCipher.encrypt(secret_key, balance);
			shi.setBalance(sealed_user_id, sealed_balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPlayer(int id, String firstname, String lastname, String user_id, String password, String address, String phone, String email, String balance) throws RemoteException
	{
		try {
			SealedObject sealed_id = CRCipher.encrypt(secret_key, id);
			SealedObject sealed_firstname = CRCipher.encrypt(secret_key, firstname);
			SealedObject sealed_lastname = CRCipher.encrypt(secret_key, lastname);
			SealedObject sealed_user_id = CRCipher.encrypt(secret_key, user_id);
			SealedObject sealed_password = CRCipher.encrypt(secret_key, ToHexString.toHexString(SHA256Digest.digest(password)));
			SealedObject sealed_address = CRCipher.encrypt(secret_key, address);
			SealedObject sealed_phone = CRCipher.encrypt(secret_key, phone);;
			SealedObject sealed_email = CRCipher.encrypt(secret_key, email);
			SealedObject sealed_balance = CRCipher.encrypt(secret_key, balance);
			shi.setPlayer(sealed_id, sealed_firstname, sealed_lastname, sealed_user_id, sealed_password, sealed_address, sealed_phone, sealed_email, sealed_balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Player getPlayer(int id) throws RemoteException
	{
		try {
			SealedObject sealed_id = CRCipher.encrypt(secret_key, id);
			return (Player)CRCipher.decrypt(secret_key, shi.getPlayer(sealed_id));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}