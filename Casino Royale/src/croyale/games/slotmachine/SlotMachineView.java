package croyale.games.slotmachine;


import javax.swing.*;

import croyale.util.ImagePanel;

import java.awt.*;
import java.awt.event.ActionListener;

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

    JLabel imageLabel1 = new JLabel();
    JLabel imageLabel2 = new JLabel();
    JLabel imageLabel3 = new JLabel();

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

		ImageIcon ii1 = new ImageIcon("src/croyale/resources/reels/1.png");
		ImageIcon ii2 = new ImageIcon("src/croyale/resources/reels/2.png");
		ImageIcon ii3 = new ImageIcon("src/croyale/resources/reels/3.png");

		imageLabel1.setIcon(ii1);
        imageLabel2.setIcon(ii2);
        imageLabel3.setIcon(ii3);

        reelSpinners.add(imageLabel1, java.awt.BorderLayout.CENTER);
		reelSpinners.add(Box.createRigidArea(new Dimension(15,15)));
        reelSpinners.add(imageLabel2, java.awt.BorderLayout.CENTER);
		reelSpinners.add(Box.createRigidArea(new Dimension(15,15)));
        reelSpinners.add(imageLabel3, java.awt.BorderLayout.CENTER);
        
        leverButton = new JButton("Spin Wheel");

        mainPane.add(Box.createVerticalStrut(225));
        mainPane.add(reelSpinners);
        mainPane.add(Box.createVerticalStrut(50));
        mainPane.add(leverButton);

		contentPane.add(mainPane);
		contentPane.revalidate();
		contentPane.repaint();

	}
	
	void setSpinners(int[] a){
		reelSpinners.removeAll();
		
		ImageIcon ii1 = new ImageIcon("src/croyale/resources/reels/" + a[0] + ".png");
		ImageIcon ii2 = new ImageIcon("src/croyale/resources/reels/" + a[1] + ".png");
		ImageIcon ii3 = new ImageIcon("src/croyale/resources/reels/" + a[2] + ".png");
		
		imageLabel1 = new JLabel();
		imageLabel2 = new JLabel();
		imageLabel3 = new JLabel();
		
		imageLabel1.setIcon(ii1);
        imageLabel2.setIcon(ii2);
        imageLabel3.setIcon(ii3);
		
		reelSpinners.add(imageLabel1, java.awt.BorderLayout.CENTER);
		reelSpinners.add(Box.createRigidArea(new Dimension(15,15)));
        reelSpinners.add(imageLabel2, java.awt.BorderLayout.CENTER);
		reelSpinners.add(Box.createRigidArea(new Dimension(15,15)));
        reelSpinners.add(imageLabel3, java.awt.BorderLayout.CENTER);
        
        reelSpinners.revalidate();
        reelSpinners.repaint();
		
	}
	
	void spinSpinners(){
		System.out.println("trying to spin");
		reelSpinners.removeAll();
		
		ImageIcon ii1 = new ImageIcon("src/croyale/resources/reels/spin.gif");
		ImageIcon ii2 = new ImageIcon("src/croyale/resources/reels/spin.gif");
		ImageIcon ii3 = new ImageIcon("src/croyale/resources/reels/spin.gif");
		
		imageLabel1 = new JLabel();
		imageLabel2 = new JLabel();
		imageLabel3 = new JLabel();
		
		imageLabel1.setIcon(ii1);
        imageLabel2.setIcon(ii2);
        imageLabel3.setIcon(ii3);
		
		reelSpinners.add(imageLabel1, java.awt.BorderLayout.CENTER);
		reelSpinners.add(Box.createRigidArea(new Dimension(15,15)));
        reelSpinners.add(imageLabel2, java.awt.BorderLayout.CENTER);
		reelSpinners.add(Box.createRigidArea(new Dimension(15,15)));
        reelSpinners.add(imageLabel3, java.awt.BorderLayout.CENTER);
        
        reelSpinners.revalidate();
        reelSpinners.repaint();
	}

	public void addSpinListener(ActionListener nal) {

    	leverButton.addActionListener(nal);

	}

}
