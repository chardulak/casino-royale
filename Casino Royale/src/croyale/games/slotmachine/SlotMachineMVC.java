package croyale.games.slotmachine;

import javax.swing.JFrame;
import javax.swing.JPanel;

import croyale.security.ClientSecurity;

public class SlotMachineMVC
{
	
	public SlotMachineMVC(JPanel panel,ClientSecurity cs, int uid){

		SlotMachineModel model = new SlotMachineModel(cs, uid);
		SlotMachineView view = new SlotMachineView(panel);
		SlotMachineController controller = new SlotMachineController(model,view);

		view.setVisible(true);
	}
	
}
