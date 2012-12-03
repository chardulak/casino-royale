package croyale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import croyale.security.ClientSecurity;

public class MainScreenController {

	private MainScreenModel model;
	private MainScreenView view;
	
	public MainScreenController(MainScreenModel m, MainScreenView v){
		model = m;
		view = v;
	}
	
	public void setClientSecurity(ClientSecurity cs){
		model.setClientSecurity(cs);
	}
	
	public void start(){
		view.drawLoginScreen();
		view.addLoginListener(new LoginListener());
		view.addRegistrationListener(new RegistrationListener());
	}
	
	private class LoginListener implements ActionListener{
			
		public void actionPerformed(ActionEvent e) {
			try{
				if (model.login(view.UserIDBox.getText(), view.PasswordBox.getText())){
					view.drawGameScreen();
					view.addLogoutListener(new LogoutListener());
					view.addUpdateListener(new UpdateListener());
					view.addBlackJackListener(new BlackJackListener());
					view.addSlotMachineListener(new SlotMachineListener());
					
					view.setName(model.getName());
					view.setBalance(model.getBalance());
				}
				else
					view.alertInvalidLogin();
			}catch(Exception ee){
				
			}
		}
	}
	
	private class LogoutListener implements ActionListener{
			
		public void actionPerformed(ActionEvent e) {
			model.logout();
			view.drawLoginScreen();
			view.addLoginListener(new LoginListener());
			view.addRegistrationListener(new RegistrationListener());
			
		}
	}
	
	private class RegistrationListener implements ActionListener{
			
		public void actionPerformed(ActionEvent e) {
			model.register();
			
		}
	}
	
	private class UpdateListener implements ActionListener{
			
		public void actionPerformed(ActionEvent e) {
			model.update();
			
		}
	}
	
	private class BlackJackListener implements ActionListener{
			
		public void actionPerformed(ActionEvent e) {
			model.createBlackJackGame(view.gameContainer);
			
		}
	}
	
	private class SlotMachineListener implements ActionListener{
			
		public void actionPerformed(ActionEvent e) {
			model.createSlotMachineGame(view.gameContainer);
			
		}
	}


	
}
