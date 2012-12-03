package croyale.games.blackjack;

import javax.swing.JPanel;

public class BlackjackMVC {
	public BlackjackMVC(JPanel panel){
		BlackjackModel model = new BlackjackModel();
		BlackjackView view = new BlackjackView(panel);
		new BlackjackController(model,view);
	
	}
}
