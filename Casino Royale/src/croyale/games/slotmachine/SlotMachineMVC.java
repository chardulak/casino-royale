package croyale.games.slotmachine;

import javax.swing.JFrame;
import javax.swing.JPanel;

import croyale.Session;
import croyale.security.ClientSecurity;

public class SlotMachineMVC
{
	
	public SlotMachineMVC(JPanel panel,Session session){

		SlotMachineModel model = new SlotMachineModel(session);
		SlotMachineView view = new SlotMachineView(panel);
		SlotMachineController controller = new SlotMachineController(model,view);
		controller.setSession(session);

		view.setVisible(true);
	}
	
}
