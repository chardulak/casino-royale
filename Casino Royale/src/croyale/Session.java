package croyale;

import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import croyale.screen.password.PasswordWindow;
import croyale.screen.registration.RegistrationWindow;
import croyale.security.ClientSecurity;
import croyale.util.FormatUtility;
import croyale.util.Player;

public class Session {

	public ClientSecurity cs;
	public int UserID;
	public String userName;
	public String userIDString;
	private String userBalance;
	private double balance;
	
	private Vector<JComponent> balanceFields = new Vector<JComponent>();
	
	public Session(ClientSecurity c){
		cs = c;
	}
	
	public boolean login(String usr, String pwd) throws RemoteException{
		userIDString = usr;
		UserID = cs.checkPlayer(usr, pwd);
		if(UserID > 0){
			int login_status = cs.login(UserID);
			
			if (login_status == Constants.USER_LOGGED_IN){
				JOptionPane.showMessageDialog(null,"User is already logged in on another machine.\n" +
						"Please log out at your other location first,\n" +
						"then login again.",
						"Login Error", 
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else{
				Player player = cs.getPlayer(UserID);
				userName = player.getFirstName() + " " + player.getLastName();
				balance = player.getBalance();
				userBalance = FormatUtility.formatCurrency(balance) ;
				return true;
			}
		}
		else if (UserID == Constants.USER_DNE){

			JOptionPane.showMessageDialog(null,"Password incorrect, or \n" +
					"User does not exist.\n" +
					"Please register first!",
					"Login Error", 
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else{
			JOptionPane.showMessageDialog(null,"Invalid Username/Password.\n" +
					"Please try again, or if you are a new user,\n" +
					"please register first!",
					"Login Error", 
					JOptionPane.ERROR_MESSAGE);
			UserID = 0;
			return false;
		}
	}
	
	public void logout(){
		try {
			cs.setBalance(UserID, Double.toString(balance));
			cs.logout(UserID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		UserID = 0;
		userName = "";
		userBalance = "0";
		balance = 0;
	}
	
	public void openRegistrationWindow(){
		RegistrationWindow rw = new RegistrationWindow(cs);
		rw.setVisible(true);
	}
	
	public void updateUserInfo(){
		RegistrationWindow rw = new RegistrationWindow(UserID, cs, this);
		rw.setVisible(true);
	}
	
	public void changePassword(){
		PasswordWindow pw = new PasswordWindow(this);
		pw.setVisible(true);
	}
	
	public String getUserName(){
		return userName;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public void updateBalance(double bal){
		balance = bal;
		userBalance = FormatUtility.formatCurrency(balance);
		updateBalanceFields();
	}
	
	public void close(){
		try {
			cs.setBalance(UserID, Double.toString(balance));
			cs.logout(UserID);
			cs.deleteSession();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void addBalanceField(JLabel field){
		balanceFields.add(field);
	}

	
	public void addBalanceField(JTextArea field){
		balanceFields.add(field);
	}
	
	
	private void updateBalanceFields(){
		JComponent temp;
		for(int i=0; i<balanceFields.size();i++){
			temp = balanceFields.get(i);
			if (temp instanceof JLabel)
				((JLabel)temp).setText(userBalance);
			if (temp instanceof JTextArea)
				((JTextArea)temp).setText(userBalance);
		}
	}
}
