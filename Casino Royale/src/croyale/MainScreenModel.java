package croyale;

import java.rmi.RemoteException;

import javax.swing.JPanel;

import croyale.games.blackjack.BlackjackMVC;
import croyale.games.slotmachine.SlotMachineMVC;
import croyale.security.ClientSecurity;

public class MainScreenModel {

	private ClientSecurity cs;
	private int UserID;
	
	public MainScreenModel(){
		
	}
	
	public void setClientSecurity(ClientSecurity cs){
		this.cs = cs;
	}
	
	public boolean login(String usr, String pwd) throws RemoteException{
		
		UserID = cs.checkPlayer(usr, pwd);
		if(UserID > 0){
			return true;
		}
		else{
			UserID = 0;
			return false;
		}
		
	}
	
	public void register(){
		RegistrationWindow rw = new RegistrationWindow(cs);
		rw.setVisible(true);
	}
	
	public void update(){
		RegistrationWindow rw = new RegistrationWindow(UserID, cs);
		rw.setVisible(true);
	}
	
	public void logout(){
		UserID = 0;
	}
	
	public void createSlotMachineGame(JPanel gameContainer){
		try {
			new SlotMachineMVC(gameContainer, cs, UserID);
		} 
		catch (Exception ee) {
			System.out.println("Could not create slot machine");
			ee.printStackTrace();
		}
	}
	
	public void createBlackJackGame(JPanel gameContainer){
		try {
			new BlackjackMVC(gameContainer);
		} 
		catch (Exception ee) {
			System.out.println("Could not create slot machine");
			ee.printStackTrace();
		}
	}
}