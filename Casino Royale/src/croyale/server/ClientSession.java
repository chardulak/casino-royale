package croyale.server;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

import croyale.db.Database;
import croyale.security.CRCipher;

public class ClientSession implements Constants
{
	private Database db;
	private SecretKey secret_key;
	
	public ClientSession(Database db)
	{
		this.db = db;
	}
	
	public void setSecretKey(SecretKey secret_key)
	{
		this.secret_key = secret_key;
	}
	
	public SealedObject checkPlayer(SealedObject sealed_user_id, SealedObject sealed_password) throws InvalidKeyException, IllegalBlockSizeException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, ClassNotFoundException
	{
		String user_id = (String)CRCipher.decrypt(secret_key, sealed_user_id);
		String password = (String)CRCipher.decrypt(secret_key, sealed_password);
		
		System.out.println("\tID: " + user_id + " Password: " + password);
		
		int ret;
		try {
			ret = db.checkPlayer(user_id, password);
		} catch (Exception e) {
			e.printStackTrace();
			ret = USER_DNE;
		}
		
		return CRCipher.encrypt(secret_key, ret);
	}
	
	public SealedObject getPlayer(SealedObject sealed_id) throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, IOException, IllegalBlockSizeException, InvalidAlgorithmParameterException, NoSuchPaddingException, SQLException
	{
		int id = (int)CRCipher.decrypt(secret_key, sealed_id);
		
		System.out.println("\tID: " + id);
		
		return CRCipher.encrypt(secret_key, db.getPlayer(id));
	}
	
	public SealedObject getUserBalance(SealedObject sealed_id) throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, IOException, IllegalBlockSizeException, InvalidAlgorithmParameterException, NoSuchPaddingException, SQLException
	{
		int id = (int)CRCipher.decrypt(secret_key, sealed_id);
		
		System.out.println("\tID: " + id);
		
		return CRCipher.encrypt(secret_key, db.getBalance(id));
	}
	
	public void setBalance(SealedObject sealed_id, SealedObject sealed_balance) throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, IOException, SQLException
	{
		int id = (int)CRCipher.decrypt(secret_key, sealed_id);
		String balance = (String)CRCipher.decrypt(secret_key, sealed_balance);
		
		System.out.println("\tID: " + id + " Balance: " + balance);
		
		db.setBalance(id, balance);
	}
	
	public void setAddress(SealedObject sealed_id, SealedObject sealed_address) throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, IOException, SQLException
	{
		int id = (int)CRCipher.decrypt(secret_key, sealed_id);
		String address = (String)CRCipher.decrypt(secret_key, sealed_address);
		
		System.out.println("\tID: " + id + " Address: " + address);
		
		db.setAddress(id, address);
	}
	
	public void setEmail(SealedObject sealed_id, SealedObject sealed_email) throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, IOException, SQLException
	{
		int id = (int)CRCipher.decrypt(secret_key, sealed_id);
		String email = (String)CRCipher.decrypt(secret_key, sealed_email);
		
		System.out.println("\tID: " + id + " Email: " + email);
		
		db.setEmail(id, email);
	}
	
	public void setFirstName(SealedObject sealed_id, SealedObject sealed_firstname) throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, IOException, SQLException
	{
		int id = (int)CRCipher.decrypt(secret_key, sealed_id);
		String firstname = (String)CRCipher.decrypt(secret_key, sealed_firstname);
		
		System.out.println("\tID: " + id + " Firstname: " + firstname);
		
		db.setFirstName(id, firstname);
	}
	
	public void setLastName(SealedObject sealed_id, SealedObject sealed_lastname) throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, IOException, SQLException
	{
		int id = (int)CRCipher.decrypt(secret_key, sealed_id);
		String lastname = (String)CRCipher.decrypt(secret_key, sealed_lastname);
		
		System.out.println("\tID: " + id + " Lastname: " + lastname);
		
		db.setLastName(id, lastname);
	}
	
	public void setPassword(SealedObject sealed_id, SealedObject sealed_password) throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, IOException, SQLException
	{
		int id = (int)CRCipher.decrypt(secret_key, sealed_id);
		String password = (String)CRCipher.decrypt(secret_key, sealed_password);
		
		System.out.println("\tID: " + id + " Password: " + password);
		
		db.setPassword(id, password);
	}
	
	public void setPhone(SealedObject sealed_id, SealedObject sealed_phone) throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, IOException, SQLException
	{
		int id = (int)CRCipher.decrypt(secret_key, sealed_id);
		String phone = (String)CRCipher.decrypt(secret_key, sealed_phone);
		
		System.out.println("\tID: " + id + " Phone: " + phone);
		
		db.setPhone(id, phone);
	}
	
	public void setPlayer(SealedObject sealed_id, SealedObject sealed_firstname, SealedObject sealed_lastname, SealedObject sealed_user_id, SealedObject sealed_password, SealedObject sealed_address, SealedObject sealed_phone, SealedObject sealed_email, SealedObject sealed_balance) throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, IOException, SQLException
	{
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
	}
	
	public void setUserID(SealedObject sealed_id, SealedObject sealed_user_id) throws InvalidKeyException, ClassNotFoundException, NoSuchAlgorithmException, IOException, SQLException
	{
		int id = (int)CRCipher.decrypt(secret_key, sealed_id);
		String userid = (String)CRCipher.decrypt(secret_key, sealed_user_id);
		
		System.out.println("\tID: " + id + " UserID: " + userid);
		
		db.setUserID(id, userid);
	}
}