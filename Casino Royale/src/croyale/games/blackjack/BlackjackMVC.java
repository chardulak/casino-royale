package croyale.games.blackjack;

import javax.swing.JPanel;

import croyale.Session;

public class BlackjackMVC {
	public BlackjackMVC(JPanel panel, Session session){
		BlackjackModel model = new BlackjackModel(session);
		BlackjackView view = new BlackjackView(panel);
		BlackjackController controller = new BlackjackController(model,view);
		controller.setSession(session);
	}
}
