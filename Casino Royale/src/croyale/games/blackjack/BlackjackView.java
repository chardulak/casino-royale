package croyale.games.blackjack;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import croyale.gameutil.Hand;
import croyale.util.ImagePanel;

public class BlackjackView{

	//private static Play currentPlay; // User's response, "Hit" or "Stand".
	private JPanel m_enterBetL = new JPanel();
	private JTextField m_betTf = new JTextField(5);
	private JButton m_betBtn = new JButton("Bet");
	private JPanel m_enterPlayL = new JPanel();
	private JTextField m_currentPlayTf = new JTextField(10);
	private JButton m_playBtn = new JButton("Play");
	private JPanel m_balanceL = new JPanel();
	private JTextField m_balanceTf = new JTextField(20);
	private JTextArea m_outputTa = new JTextArea(40, 40);
	private JLabel backgroundPane;
	private JPanel contentPane;

	protected JPanel winLoseBar;
	protected JButton hitButton, standButton, newGameButton;
	protected JTextArea betField, balanceField;
	protected JLayeredPane yourCards, dealerCards;
	
	
	BlackjackView(JPanel gamePane){
	 contentPane = gamePane;
	 drawGameScreen();
	}

	public void drawGameScreen(){

		// Initialize containers
		contentPane.removeAll();
		contentPane.setBackground(new Color(0,176,80));
		JLabel mainPane = new ImagePanel(new ImageIcon("src/croyale/resources/blackjack.jpg").getImage());
		mainPane.setLayout(new BoxLayout(mainPane,BoxLayout.Y_AXIS));
		mainPane.setOpaque(false);
		
		// Create Win Lose notification strip
		winLoseBar = new JPanel();
		winLoseBar.setLayout(new BoxLayout(winLoseBar,BoxLayout.X_AXIS));
		winLoseBar.setOpaque(false);
		
		JLabel gameResultDisplay = new ImagePanel(new ImageIcon("src/croyale/resources/blankwinlose.png").getImage());
		
		winLoseBar.add(Box.createVerticalStrut(100));
		winLoseBar.add(Box.createHorizontalStrut(277));
		winLoseBar.add(gameResultDisplay);
		winLoseBar.add(Box.createHorizontalStrut(277));
		
		// Create New Game button
		JPanel newGameBar = new JPanel();
		newGameBar.setLayout(new BoxLayout(newGameBar,BoxLayout.X_AXIS));
		newGameBar.setOpaque(false);
		
		JPanel newGameButtonContainer = new JPanel();
		newGameButtonContainer.setOpaque(false);
		newGameButton = new JButton("New Game?");
		newGameButton.setVisible(false);
		newGameButtonContainer.add(newGameButton);
		
		newGameBar.add(Box.createVerticalStrut(50));
		newGameBar.add(Box.createHorizontalStrut(237));
		newGameBar.add(newGameButtonContainer);
		newGameBar.add(Box.createHorizontalStrut(317));
		
		// Create Card area
		JPanel cardBar = new JPanel();
		cardBar.setLayout(new BoxLayout(cardBar,BoxLayout.X_AXIS));
		cardBar.setOpaque(false);
		
		yourCards = new JLayeredPane();
		dealerCards = new JLayeredPane();
		
		cardBar.add(Box.createHorizontalStrut(15));
		//cardBar.add(Box.createHorizontalGlue());
		cardBar.add(yourCards);
		cardBar.add(Box.createHorizontalStrut(400));
		cardBar.add(Box.createVerticalStrut(96));
		cardBar.add(dealerCards);
		//cardBar.add(Box.createHorizontalGlue());
		cardBar.add(Box.createHorizontalStrut(15));
		
		
		// Create Labels for card area
		JPanel cardLabelBar = new JPanel();
		cardLabelBar.setLayout(new BoxLayout(cardLabelBar,BoxLayout.X_AXIS));
		cardLabelBar.setOpaque(false);
		
		JLabel yourCardsLabel = new JLabel("Your Cards:");
		JLabel dealerCardsLabel = new JLabel("Dealer's Cards:");
		yourCardsLabel.setOpaque(true);
		dealerCardsLabel.setOpaque(true);
		yourCardsLabel.setFont(new Font(yourCardsLabel.getFont().getName(),Font.BOLD,20));
		dealerCardsLabel.setFont(yourCardsLabel.getFont());
		
		cardLabelBar.add(Box.createVerticalStrut(21));
		cardLabelBar.add(Box.createHorizontalStrut(10));
		cardLabelBar.add(yourCardsLabel);
		cardLabelBar.add(Box.createHorizontalStrut(570));
		cardLabelBar.add(dealerCardsLabel);
		cardLabelBar.add(Box.createHorizontalStrut(10));
		
		
		// Create Action buttons
		JPanel actionBar = new JPanel();
		actionBar.setLayout(new BoxLayout(actionBar,BoxLayout.X_AXIS));
		actionBar.setOpaque(false);
		
		hitButton = new JButton("Hit!");
		hitButton.setMaximumSize(new Dimension(120,60));
		standButton = new JButton("Stand!");
		standButton.setMaximumSize(new Dimension(120,60));
		
		actionBar.add(Box.createVerticalStrut(30));
		actionBar.add(Box.createHorizontalStrut(225));
		actionBar.add(hitButton);
		actionBar.add(Box.createHorizontalStrut(15));
		actionBar.add(standButton);
		actionBar.add(Box.createHorizontalStrut(350));
		
		
		// Create bet box
		JPanel betBar = new JPanel();
		betBar.setLayout(new BoxLayout(betBar,BoxLayout.X_AXIS));
		betBar.setOpaque(false);
		
		JLabel betLabel = new JLabel("Bet:");
		betField = new JTextArea("10");
		
		betBar.add(Box.createVerticalStrut(20));
		betBar.add(Box.createHorizontalStrut(390));
		betBar.add(betLabel);
		betBar.add(Box.createHorizontalStrut(15));
		betBar.add(betField);
		betBar.add(Box.createHorizontalStrut(375));
		
		
		// Create balance display
		JPanel balanceBar = new JPanel();
		balanceBar.setLayout(new BoxLayout(balanceBar,BoxLayout.X_AXIS));
		balanceBar.setOpaque(false);
		
		JLabel balanceLabel = new JLabel("Balance:");
		balanceField = new JTextArea("10");
		
		balanceBar.add(Box.createVerticalStrut(20));
		balanceBar.add(Box.createHorizontalStrut(390));
		balanceBar.add(balanceLabel);
		balanceBar.add(Box.createHorizontalStrut(15));
		balanceBar.add(balanceField);
		balanceBar.add(Box.createHorizontalStrut(375));
		
		
		// Run out main vertical layout
		mainPane.add(Box.createVerticalStrut(160));
		mainPane.add(winLoseBar);
		mainPane.add(newGameBar);
		mainPane.add(Box.createVerticalStrut(90));
		mainPane.add(cardBar);
		mainPane.add(Box.createVerticalStrut(10));
		mainPane.add(cardLabelBar);
		mainPane.add(Box.createVerticalStrut(10));
		mainPane.add(actionBar);
		mainPane.add(Box.createVerticalStrut(10));
		mainPane.add(betBar);
		mainPane.add(Box.createVerticalStrut(10));
		mainPane.add(balanceBar);	
		mainPane.add(Box.createVerticalStrut(85));
		
		// Set background image as panel
		//JLabel backgroundPane = new ImagePanel(new ImageIcon("src/croyale/resources/BlackjackTable.png").getImage());
		//backgroundPane = new ImagePanel(new ImageIcon("src/croyale/resources/blackjack.jpg").getImage());
		//backgroundPane.setOpaque(false);

		//Layout the components.
		//JPanel formContainer = new JPanel();
		//formContainer.setBounds(300, 200, 400, 260);
		//JButton m_hitBtn = new JButton(new ImageIcon("src/croyale/resources/HIT poker chip.png"));
		//JButton standButton= new JButton(new ImageIcon("src/croyale/resources/HIT poker chip.png"));
		//m_hitBtn.setPreferredSize(new Dimension(20,20));
		/*
		m_enterBetL.setLocation(10, 100);
		m_enterBetL.setLayout(new BoxLayout(m_enterBetL, BoxLayout.X_AXIS));
		//m_enterBetL.setPreferredSize(new Dimension(100,5));
		JLabel m_enterBetLText = new JLabel("Enter amount of Bet:");
		m_enterBetL.add(m_enterBetLText);
		m_enterBetL.setMaximumSize(m_enterBetL.getPreferredSize());
		mainPane.add(this.m_enterBetL);
		mainPane.add(Box.createRigidArea(new Dimension(5,0)));
		m_betTf.setMaximumSize(m_betTf.getPreferredSize());
		mainPane.add(this.m_betTf);
		mainPane.add(Box.createRigidArea(new Dimension(5,0)));

		
		mainPane.add(this.m_betBtn);
		m_enterPlayL.setLayout(new BoxLayout(m_enterPlayL, BoxLayout.X_AXIS));
		m_enterPlayL.setPreferredSize(new Dimension(100,20));
		JLabel m_enterPlayLText = new JLabel("Enter play (Hit or Stand)");
		m_enterPlayL.add(m_enterPlayLText);
		mainPane.add(this.m_enterPlayL);
		mainPane.add(Box.createRigidArea(new Dimension(5,0)));
		m_currentPlayTf.setMaximumSize(m_currentPlayTf.getPreferredSize());
		mainPane.add(this.m_currentPlayTf);
		mainPane.add(this.m_playBtn);
		
		m_balanceL.setLocation(10, 100);
		m_balanceL.setLayout(new BoxLayout(m_balanceL, BoxLayout.Y_AXIS));
		m_balanceL.setPreferredSize(new Dimension(100,20));
		JLabel m_balanceLText = new JLabel("balance:");
		m_balanceL.add(m_balanceLText);
		mainPane.add(this.m_balanceL);
		mainPane.add(Box.createRigidArea(new Dimension(5,0)));
		m_balanceTf.setLayout(new BoxLayout(m_balanceTf, BoxLayout.X_AXIS));
		m_balanceTf.setMaximumSize(m_balanceTf.getPreferredSize());
		mainPane.add(this.m_balanceTf);
		
		mainPane.add(this.m_outputTa);
		
		*/
	//	mainPane.setPreferredSize(new Dimension(800,1000));
		//mainPane.add(formContainer,1);
		//mainPane.add(backgroundPane,2);
		contentPane.add(mainPane);
		//backgroundPane.setBounds(50,0,600,800);
		//mainPane.setBounds(50,100,600,800);
		//contentPane.setBounds(0,0,600,800);
		contentPane.revalidate();
	}

	/*public Play getCurrentPlay(){
     	  ParseString parse = new ParseString();
  		  try {
  			try {
  				parse.parseString(this.m_currentPlayTf.getText());
  			} catch (NoSuchMethodException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			} catch (SecurityException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			} catch (IllegalArgumentException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			} catch (InvocationTargetException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  		} catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (InstantiationException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (IllegalAccessException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
            
    	return currentPlay;
    }*/
    public void setMoney(String newMoney){
    	balanceField.setText(newMoney);
    }
    public int getBet(){
    	return Integer.parseInt(betField.getText());
    }
    public void addPlayListener(ActionListener pal){
    	hitButton.addActionListener(pal);
    	standButton.addActionListener(pal);
    	newGameButton.addActionListener(pal);
    }
    public void addBetListener(ActionListener bal){
    	//this.m_betBtn.addActionListener(bal);
    }
    
    public void displayCards(Hand[] hands){
    	for (int i=0;i<hands[0].getCardCount();i++){
    		JLabel currentCard = hands[0].getCard(i).getImage();
    		yourCards.add(currentCard,30-i);
    		currentCard.setBounds(50-(i*15),0,72,96);
    		
    	}

    	for (int j=0;j<hands[1].getCardCount();j++){
    		JLabel currentCard = hands[1].getCard(j).getImage();
    		dealerCards.add(currentCard,30-j);
    		currentCard.setBounds(50-j*15,0,72,96);
    	}
    }
    
    public void drawWin(){
    	winLoseBar.removeAll();

		JLabel gameResultDisplay = new ImagePanel(new ImageIcon("src/croyale/resources/win.png").getImage());
		
		winLoseBar.add(Box.createVerticalStrut(100));
		winLoseBar.add(Box.createHorizontalStrut(277));
		winLoseBar.add(gameResultDisplay);
		winLoseBar.add(Box.createHorizontalStrut(277));
		
		newGameButton.setVisible(true);
		winLoseBar.revalidate();
    }
    
    public void drawLose(){
    	winLoseBar.removeAll();

		JLabel gameResultDisplay = new ImagePanel(new ImageIcon("src/croyale/resources/lose.png").getImage());
		
		winLoseBar.add(Box.createVerticalStrut(100));
		winLoseBar.add(Box.createHorizontalStrut(277));
		winLoseBar.add(gameResultDisplay);
		winLoseBar.add(Box.createHorizontalStrut(277));

		newGameButton.setVisible(true);
		winLoseBar.revalidate();
    	
    }
    
    public void reset(){
    	winLoseBar.removeAll();
    	yourCards.removeAll();
    	dealerCards.removeAll();
    	
		JLabel gameResultDisplay = new ImagePanel(new ImageIcon("src/croyale/resources/blankwinlose.png").getImage());
		
		winLoseBar.add(Box.createVerticalStrut(100));
		winLoseBar.add(Box.createHorizontalStrut(277));
		winLoseBar.add(gameResultDisplay);
		winLoseBar.add(Box.createHorizontalStrut(277));
		
		newGameButton.setVisible(false);
		winLoseBar.revalidate();
    	
    }
}
	
