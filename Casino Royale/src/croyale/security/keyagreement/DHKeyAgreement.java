package croyale.security.keyagreement;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.IvParameterSpec;

import croyale.util.ToHexString;

public class DHKeyAgreement
{
	// The 1024 bit Diffie-Hellman modulus values
	private static final byte skip1024ModulusBytes[] = {
		(byte)0xF4, (byte)0x88, (byte)0xFD, (byte)0x58,
		(byte)0x4E, (byte)0x49, (byte)0xDB, (byte)0xCD,
		(byte)0x20, (byte)0xB4, (byte)0x9D, (byte)0xE4,
		(byte)0x91, (byte)0x07, (byte)0x36, (byte)0x6B,
		(byte)0x33, (byte)0x6C, (byte)0x38, (byte)0x0D,
		(byte)0x45, (byte)0x1D, (byte)0x0F, (byte)0x7C,
		(byte)0x88, (byte)0xB3, (byte)0x1C, (byte)0x7C,
		(byte)0x5B, (byte)0x2D, (byte)0x8E, (byte)0xF6,
		(byte)0xF3, (byte)0xC9, (byte)0x23, (byte)0xC0,
		(byte)0x43, (byte)0xF0, (byte)0xA5, (byte)0x5B,
		(byte)0x18, (byte)0x8D, (byte)0x8E, (byte)0xBB,
		(byte)0x55, (byte)0x8C, (byte)0xB8, (byte)0x5D,
		(byte)0x38, (byte)0xD3, (byte)0x34, (byte)0xFD,
		(byte)0x7C, (byte)0x17, (byte)0x57, (byte)0x43,
		(byte)0xA3, (byte)0x1D, (byte)0x18, (byte)0x6C,
		(byte)0xDE, (byte)0x33, (byte)0x21, (byte)0x2C,
		(byte)0xB5, (byte)0x2A, (byte)0xFF, (byte)0x3C,
		(byte)0xE1, (byte)0xB1, (byte)0x29, (byte)0x40,
		(byte)0x18, (byte)0x11, (byte)0x8D, (byte)0x7C,
		(byte)0x84, (byte)0xA7, (byte)0x0A, (byte)0x72,
		(byte)0xD6, (byte)0x86, (byte)0xC4, (byte)0x03,
		(byte)0x19, (byte)0xC8, (byte)0x07, (byte)0x29,
		(byte)0x7A, (byte)0xCA, (byte)0x95, (byte)0x0C,
		(byte)0xD9, (byte)0x96, (byte)0x9F, (byte)0xAB,
		(byte)0xD0, (byte)0x0A, (byte)0x50, (byte)0x9B,
		(byte)0x02, (byte)0x46, (byte)0xD3, (byte)0x08,
		(byte)0x3D, (byte)0x66, (byte)0xA4, (byte)0x5D,
		(byte)0x41, (byte)0x9F, (byte)0x9C, (byte)0x7C,
		(byte)0xBD, (byte)0x89, (byte)0x4B, (byte)0x22,
		(byte)0x19, (byte)0x26, (byte)0xBA, (byte)0xAB,
		(byte)0xA2, (byte)0x5E, (byte)0xC3, (byte)0x55,
		(byte)0xE9, (byte)0x2F, (byte)0x78, (byte)0xC7
	};
	
	// The 1024 bit modulus
	private static final BigInteger dh1024Modulus = new BigInteger(1, skip1024ModulusBytes);
	
	// The base used with the 1024 bit modulus
	private static final BigInteger dh1024Base = BigInteger.valueOf(2);
	
	private DHParameterSpec dhParamSpec;
	private KeyPair keyPair;
	private KeyAgreement keyAgreement;
	private SecretKey secretKey;
	
	public static void main(String[] args)
	{
		DHKeyAgreement serverKeyAgree = new DHKeyAgreement();
		byte[] serverPubKeyEnc = serverKeyAgree.getPublicKeyEncoded();
		
		DHKeyAgreement clientKeyAgree = new DHKeyAgreement();
		byte[] clientPubKeyEnc = clientKeyAgree.getPublicKeyEncoded();
		
		serverKeyAgree.generateSecretKey(clientPubKeyEnc);
		clientKeyAgree.generateSecretKey(serverPubKeyEnc);
		
		System.out.println("Server secret key: " + ToHexString.toHexString(serverKeyAgree.getSecretKey().getEncoded()));
		System.out.println("Client secret key: " + ToHexString.toHexString(clientKeyAgree.getSecretKey().getEncoded()));
		
		
		// Example encryption using byte array
		try {
			byte[] iv = new byte[16];
			new Random().nextBytes(iv);
			IvParameterSpec ips = new IvParameterSpec(iv);
			
			Cipher server_enc_cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			server_enc_cipher.init(Cipher.ENCRYPT_MODE, serverKeyAgree.getSecretKey(), ips);
			
			Cipher client_dec_cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			client_dec_cipher.init(Cipher.DECRYPT_MODE, serverKeyAgree.getSecretKey(), ips); // must use same IV, so will need to transmit it
			
			byte[] plaintext = "blahblalk;2@@#$!!".getBytes();
			byte[] ciphertext = server_enc_cipher.doFinal(plaintext);
			byte[] decrypted = client_dec_cipher.doFinal(ciphertext);
			
			System.out.println("Original text:  " + ToHexString.toHexString(plaintext));
			System.out.println("Decrypted text: " + ToHexString.toHexString(decrypted));
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Example encryption using SealedObject
		try {
			byte[] iv = new byte[16];
			new Random().nextBytes(iv);
			IvParameterSpec ips = new IvParameterSpec(iv);
			
			Cipher server_enc_cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			server_enc_cipher.init(Cipher.ENCRYPT_MODE, serverKeyAgree.getSecretKey(), ips);
			
			String plaintext = "blahblalk;2@@#$!!";
			SealedObject ciphertext = new SealedObject(plaintext, server_enc_cipher);
			String decrypted = (String)ciphertext.getObject(clientKeyAgree.getSecretKey());
			
			System.out.println("Original text:  " + plaintext);
			System.out.println("Decrypted text: " + decrypted);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DHKeyAgreement()
	{
			dhParamSpec = new DHParameterSpec(dh1024Modulus, dh1024Base);
			this.generateKeyPair();
			this.createKeyAgreement();
	}
	
	private void generateKeyPair()
	{
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DH");
			keyPairGen.initialize(dhParamSpec);
			keyPair = keyPairGen.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	private void createKeyAgreement()
	{
		try {
			keyAgreement = KeyAgreement.getInstance("DH");
			keyAgreement.init(keyPair.getPrivate());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public byte[] getPublicKeyEncoded()
	{
		return keyPair.getPublic().getEncoded();
	}
	
	public byte[] getPrivateKeyEncoded()
	{
		return keyPair.getPrivate().getEncoded();
	}
	
	// Call this method after getting the encoded public key from the other party
	public void generateSecretKey(byte[] externalPubKeyEnc)
	{
		try {
			// Decrypt the public key from the other party
			KeyFactory keyFac = KeyFactory.getInstance("DH");
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(externalPubKeyEnc);
			PublicKey publicKey = keyFac.generatePublic(x509KeySpec);
			
			// Do the first and only phase on the KeyAgreement object
			keyAgreement.doPhase(publicKey, true);
			
			// Generate secret
			secretKey = keyAgreement.generateSecret("AES");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SecretKey getSecretKey()
	{
		return secretKey;
	}
}