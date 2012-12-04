package croyale;

import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import croyale.security.ClientSecurity;

public class Session {

	private ClientSecurity cs;
	private int UserID;
	private String userName;
	private String userBalance;
	private double balance;
	
	private Vector<JComponent> balanceFields = new Vector<JComponent>();
	
	public Session(ClientSecurity c){
		cs = c;
	}
	
	public boolean login(String usr, String pwd) throws RemoteException{
		UserID = cs.checkPlayer(usr, pwd);
		if(UserID > 0){
			Player player = cs.getPlayer(UserID);
			userName = player.getFirstName() + " " + player.getLastName();
			balance = player.getBalance();
			userBalance = FormatUtility.formatCurrency(balance) ;
			return true;
		}
		else{
			UserID = 0;
			return false;
		}
	}
	
	public void logout(){
		close();
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
		RegistrationWindow rw = new RegistrationWindow(UserID, cs);
		rw.setVisible(true);
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
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
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
