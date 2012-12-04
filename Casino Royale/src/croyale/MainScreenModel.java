package croyale;

import java.rmi.RemoteException;

import javax.swing.JPanel;

import croyale.games.blackjack.BlackjackMVC;
import croyale.games.slotmachine.SlotMachineMVC;

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
			new BlackjackMVC(gameContainer);
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
