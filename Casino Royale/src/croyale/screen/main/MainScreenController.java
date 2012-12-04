package croyale.screen.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import croyale.Session;

public class MainScreenController {

	private MainScreenModel model;
	private MainScreenView view;
	private Session session;
	
	public MainScreenController(MainScreenModel m, MainScreenView v){
		model = m;
		view = v;
		view.drawLoginScreen();
		view.addLoginListener(new LoginListener());
		view.addRegistrationListener(new RegistrationListener());
		view.addComponentListener(new CloseListener());
	}
	
	public void setSession(Session s){
		session = s;
	}
	
	private class CloseListener extends ComponentAdapter{
		@Override
		public void componentHidden(ComponentEvent e) {
            session.close();
            view.dispose();
        }
	}
	
	private class LoginListener implements ActionListener{
			
		public void actionPerformed(ActionEvent e) {
			try{
				if (model.login(view.UserIDBox.getText(), view.PasswordBox.getText())){
					view.drawGameScreen();
					view.addLogoutListener(new LogoutListener());
					view.addUpdateListener(new UpdateListener());
					view.addChangePasswordListener(new ChangePasswordListener());
					view.addBlackJackListener(new BlackJackListener());
					view.addSlotMachineListener(new SlotMachineListener());
					
					view.setName(model.getName());
					view.setBalance(model.getBalance());
					session.addBalanceField(view.balanceText);
				}
				else{
					//view.alertInvalidLogin();
				}
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
	
	private class ChangePasswordListener implements ActionListener{
			
		public void actionPerformed(ActionEvent e) {
			model.changePassword();
			
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
