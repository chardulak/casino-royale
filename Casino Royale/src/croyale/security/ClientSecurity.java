package croyale.security;

import java.rmi.RemoteException;
import java.sql.ResultSet;

import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

import croyale.rpc.ServerHostInterface;
import croyale.security.keyagreement.DHKeyAgreement;

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
		DHKeyAgreement key_agree = new DHKeyAgreement();
		byte[] server_key = shi.doKeyAgree(key_agree.getPublicKeyEncoded());
		key_agree.generateSecretKey(server_key);
		secret_key = key_agree.getSecretKey();
	}
	
	public double getUserBalance(int user_id) throws RemoteException
	{
		try {
			SealedObject sealed_user_id = CRCipher.encrypt(secret_key, user_id);
			return shi.getUserBalance(sealed_user_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int checkPlayer(String user_id, String password) throws RemoteException
	{
		try {
			SealedObject sealed_user_id = CRCipher.encrypt(secret_key, user_id);
			SealedObject sealed_password = CRCipher.encrypt(secret_key, password);
			return shi.checkPlayer(sealed_user_id, sealed_password);
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
			SealedObject sealed_password = CRCipher.encrypt(secret_key, password);
			SealedObject sealed_address = CRCipher.encrypt(secret_key, address);
			SealedObject sealed_phone = CRCipher.encrypt(secret_key, phone);;
			SealedObject sealed_email = CRCipher.encrypt(secret_key, email);
			SealedObject sealed_balance = CRCipher.encrypt(secret_key, balance);
			shi.setPlayer(sealed_id, sealed_firstname, sealed_lastname, sealed_user_id, sealed_password, sealed_address, sealed_phone, sealed_email, sealed_balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getPlayer(int id) throws RemoteException
	{
		try {
			SealedObject sealed_id = CRCipher.encrypt(secret_key, id);
			return shi.getPlayer(sealed_id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}