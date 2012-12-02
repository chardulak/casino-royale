package croyale;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Serializable{
	private String firstname;
	private String lastname;
	private int user_id;
	private String pwd;
	private double balance;
	private String address;
	private String phone;
	private String email;
	
	public Player(String firstname, String lastname, int user_id, String pwd, double balance, String address, String phone, String email)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.user_id = user_id;
		this.pwd = pwd;
		this.balance = balance;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	public String getFirstName(){
		return firstname;
	}
	public String getLastName(){
		return lastname;
	}
	public int getUserID(){
		return user_id;
	}
	public String getPassword(){
		return pwd;
	}
	public double getBalance(){
		return balance;
	}
	public String getAddress(){
		return address;
	}
	public String getPhone(){
		return phone;
	}
	public String getEmail(){
		return email;
	}
}

