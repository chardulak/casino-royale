package croyale.security.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Digest
{
	private static MessageDigest md;
	
	static
	{
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	// Digest a byte array
	public static byte[] digest(byte[] data)
	{
		md.update(data);
		byte[] digest = md.digest();
		md.reset();
		return digest;
	}
	
	// Digest a String
	public static byte[] digest(String data)
	{
		md.update(data.getBytes());
		byte[] digest = md.digest();
		md.reset();
		return digest;
	}
}