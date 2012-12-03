package croyale.games.slotmachine;


import javax.swing.*;

import croyale.util.ImagePanel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

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
	
	protected JTextArea balanceBox, betBox, winBox;
	protected JPanel resultBar;

    JLabel imageLabel1 = new JLabel();
    JLabel imageLabel2 = new JLabel();
    JLabel imageLabel3 = new JLabel();
    JLabel imageLabel4 = new JLabel();

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
		//ImageIcon ii4 = new ImageIcon("src/croyale/resources/reels/lever1.png");

		imageLabel1.setIcon(ii1);
        imageLabel2.setIcon(ii2);
        imageLabel3.setIcon(ii3);
        
        //leverButton.setBackground(Color.black);
        //leverButton.setIcon(new ImageIcon("src/croyale/resources/reels/lever1.png"));
        
        //new ImageIcon(img)
        
        reelSpinners.add(imageLabel1, java.awt.BorderLayout.CENTER);
		reelSpinners.add(Box.createRigidArea(new Dimension(15,15)));
        reelSpinners.add(imageLabel2, java.awt.BorderLayout.CENTER);
		reelSpinners.add(Box.createRigidArea(new Dimension(15,15)));
        reelSpinners.add(imageLabel3, java.awt.BorderLayout.CENTER);
        
        JPanel displayBar = new JPanel();
        displayBar.setLayout(new BoxLayout(displayBar,BoxLayout.X_AXIS));
        displayBar.setOpaque(false);
        
        Font myFont = new Font("Serif",Font.BOLD,50);
        
        balanceBox = new JTextArea();
        balanceBox.setBackground(Color.BLACK);
        balanceBox.setFont(myFont);
        balanceBox.setForeground(Color.RED);
        balanceBox.setOpaque(true);
        balanceBox.setSize(100,30);
        balanceBox.setMaximumSize(new Dimension(250,60));
        betBox = new JTextArea("10");
        betBox.setBackground(Color.BLACK);
        betBox.setFont(myFont);
        betBox.setForeground(Color.RED);
        betBox.setOpaque(true);
        betBox.setSize(50,30);
        betBox.setMaximumSize(new Dimension(90,60));
        winBox = new JTextArea();
        winBox.setBackground(Color.BLACK);
        winBox.setFont(myFont);
        winBox.setForeground(Color.RED);
        winBox.setOpaque(true);
        winBox.setSize(50,30);
        winBox.setMaximumSize(new Dimension(160,60));
        
        displayBar.add(Box.createHorizontalStrut(40));
        displayBar.add(balanceBox);
        displayBar.add(Box.createHorizontalStrut(15));
        displayBar.add(betBox);
        displayBar.add(Box.createHorizontalStrut(25));
        displayBar.add(winBox);
        displayBar.add(Box.createHorizontalStrut(40));

        leverButton = new JButton("Spin Wheel");
        
        resultBar = new JPanel();
        resultBar.setLayout(new BoxLayout(resultBar,BoxLayout.X_AXIS));
        resultBar.setOpaque(false);
        
        JLabel resultDisplay = new JLabel();

        resultBar.add(Box.createHorizontalStrut(10));
        resultBar.add(resultDisplay);
        resultBar.add(Box.createHorizontalStrut(50));
        
        mainPane.add(Box.createVerticalStrut(225));
        mainPane.add(reelSpinners);
        mainPane.add(Box.createVerticalStrut(10));
        mainPane.add(displayBar);
        mainPane.add(Box.createVerticalStrut(40));
 
        mainPane.add(leverButton);

        //mainPane.add(imageLabel4);
        
        mainPane.add(Box.createVerticalStrut(10));
        mainPane.add(resultBar);
        mainPane.add(Box.createVerticalStrut(200));

		contentPane.add(mainPane);
		contentPane.revalidate();
		contentPane.repaint();
		contentPane.validate();

	}
	
	void setSpinners(int[] a){
		reelSpinners.removeAll();
		
		System.out.print(a[0]+", ");
		System.out.print(a[1]+", ");
		System.out.println(a[2]);
		
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
	
	public void drawLose(double balance){
		winBox.setText("0");
		setBalance(balance);
		
		resultBar.removeAll();
        
        JLabel gameResultDisplay = new ImagePanel(new ImageIcon("src/croyale/resources/lose.png").getImage());
        
        resultBar.add(Box.createHorizontalStrut(10));
        resultBar.add(gameResultDisplay);
        resultBar.add(Box.createHorizontalStrut(50));
        
        resultBar.revalidate();
        resultBar.repaint();
	}
	
	public void drawWin(int winnings, double balance){
		winBox.setText(String.valueOf(winnings));
		setBalance(balance);
		
		resultBar.removeAll();
        
        JLabel gameResultDisplay = new ImagePanel(new ImageIcon("src/croyale/resources/win.png").getImage());
        
        resultBar.add(Box.createHorizontalStrut(10));
        resultBar.add(gameResultDisplay);
        resultBar.add(Box.createHorizontalStrut(50));
        
        resultBar.revalidate();
        resultBar.repaint();
		
	}
	
	public void setBalance(double balance){
		balanceBox.setText(String.valueOf(balance));
	}
	
	public void setWinnings(int winnings){
		
	}

	public void addSpinListener(ActionListener nal) {

    	leverButton.addActionListener(nal);
    	//imageLabel4.addActionListener(nal);

	}

	public void addBetListener(KeyListener nkl) {

    	betBox.addKeyListener(nkl);

	}

}
