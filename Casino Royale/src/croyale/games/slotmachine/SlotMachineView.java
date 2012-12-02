package croyale.games.slotmachine;


import javax.swing.*;

import croyale.util.ImagePanel;

import java.awt.*;


public class SlotMachineView extends JPanel
{
	//JButton button1;
	private JPanel contentPane;
	
	private JPanel topPane;
	private JPanel reelSpinners;
//	private JPanel controls;
	
	private JButton leverButton;
	
	private JButton reel1;
	private JButton reel2;
	private JButton reel3;

//	private BufferedImage buttonIcon;

//	protected JLayeredPane ree11;
//	protected JLayeredPane reel2;
//	protected JLayeredPane reel3;
	
	public SlotMachineView(JPanel gamePane)
	{
		 contentPane = gamePane;
		 drawGameScreen();
	}
	
	public void drawGameScreen()
	{

		// Initialize containers
		contentPane.removeAll();
		contentPane.setBackground(new Color(0,176,80));
		JLabel mainPane = new ImagePanel(new ImageIcon("src/croyale/resources/slotmachine.jpg").getImage());
		mainPane.setLayout(new BoxLayout(mainPane,BoxLayout.Y_AXIS));
		mainPane.setOpaque(false);
				
		// Create Reel Spinners Panel
		topPane = new JPanel();
		topPane.setLayout(new BoxLayout(reelSpinners,BoxLayout.X_AXIS));
		topPane.setOpaque(false);

		reelSpinners = new JPanel();
		reelSpinners.setLayout(new BoxLayout(reelSpinners,BoxLayout.X_AXIS));
		reelSpinners.setOpaque(false);
		reel1 = new JButton("Reel1");
		reel1.setAlignmentX(Component.LEFT_ALIGNMENT);
		reel2 = new JButton("Reel2");
		reel2.setAlignmentX(Component.CENTER_ALIGNMENT);
		reel3 = new JButton("Reel3");
		reel3.setAlignmentX(Component.RIGHT_ALIGNMENT);

		reelSpinners.add(reel1);
		reelSpinners.add(Box.createRigidArea(new Dimension(20,20)));
		reelSpinners.add(reel2);
		reelSpinners.add(Box.createRigidArea(new Dimension(20,20)));
		reelSpinners.add(reel3);

		mainPane.add(reelSpinners);

		contentPane.add(mainPane);
		contentPane.revalidate();

	}

}
