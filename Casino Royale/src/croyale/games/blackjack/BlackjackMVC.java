package croyale.games.blackjack;

import javax.swing.JPanel;

public class BlackjackMVC {
	public BlackjackMVC(JPanel panel){
		BlackjackModel model = new BlackjackModel();
		BlackjackView view = new BlackjackView(panel);
		BlackjackController controller = new BlackjackController(model,view);
	
	}
}
