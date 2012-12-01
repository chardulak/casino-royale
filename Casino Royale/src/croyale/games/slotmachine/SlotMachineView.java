package croyale.games.slotmachine;


import javax.swing.*;
import java.awt.*;


public class SlotMachineView extends JPanel
{
	JButton button1;
	
	public SlotMachineView(JPanel panel)
	{
		System.out.println("Create a button");
		
		panel.removeAll();
		button1 = new JButton("Hello Casino");
		panel.add(button1);
		panel.revalidate();
		panel.repaint();
				
		System.out.println("Panel should be updated");
	
	}
}
