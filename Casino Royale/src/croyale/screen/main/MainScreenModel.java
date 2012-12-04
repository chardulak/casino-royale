package croyale.screen.main;

import java.rmi.RemoteException;

import javax.swing.JPanel;

import croyale.Session;
import croyale.games.blackjack.BlackjackMVC;
import croyale.games.slotmachine.SlotMachineMVC;
import croyale.util.FormatUtility;

public class MainScreenModel {

	private Session session;
	
	public MainScreenModel(Session s){
		session = s;
	}
	
	public boolean login(String usr, String pwd) throws RemoteException{
		return session.login(usr, pwd);
	}
	
	public void register(){
		session.openRegistrationWindow();
	}
	
	public void update(){
		session.updateUserInfo();
	}
	
	public void changePassword(){
		session.changePassword();
	}
	
	public void logout(){
		session.logout();
	}
	
	public void createSlotMachineGame(JPanel gameContainer){
		try {
			new SlotMachineMVC(gameContainer, session);
		} 
		catch (Exception ee) {
			System.out.println("Could not create slot machine");
			ee.printStackTrace();
		}
	}
	
	public void createBlackJackGame(JPanel gameContainer){
		try {
			new BlackjackMVC(gameContainer, session);
		} 
		catch (Exception ee) {
			System.out.println("Could not create slot machine");
			ee.printStackTrace();
		}
	}
	public String getName(){
		return session.getUserName();
	}
	public String getBalance(){
		return FormatUtility.formatCurrency(session.getBalance());
	}
}
