package croyale;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Serializable{
	private int userID;
	private String firstname;
	private String lastname;
	private String user_name;
	private String pwd;
	private double balance;
	private String address;
	private String phone;
	private String email;
	
	public Player(int id,String firstname, String lastname, String user_id, String pwd, double balance, String address, String phone, String email)
	{
		this.userID = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.user_name= user_id;
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
		return userID;
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
	public String getUserName(){
		return user_name;
	}
}

