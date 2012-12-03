package croyale;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;

import croyale.games.blackjack.BlackjackMVC;
import croyale.games.slotmachine.SlotMachineMVC;
import croyale.security.ClientSecurity;
import croyale.util.ImageButton;
import croyale.util.ImagePanel;

public class LoginWindow extends JFrame implements ActionListener{
	private static final long serialVersionUID = 2931038748092092694L;

	private static LoginWindow frame;
	private JPanel gameContainer;
	
	private ClientSecurity cs;
	private int UserID;
	
	private JButton OkButton;
	private JButton RegisterButton;
	
	private JTextField UserIDBox;
	private JTextField PasswordBox;

	// Constructors
	public LoginWindow(ClientSecurity cs) {
		this.cs = cs;
		setScreen();
	}
	public LoginWindow() {
		setScreen();
	}
	
	// Main entry point
	public static void init(final ClientSecurity cs) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginWindow(cs);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								
					int windowWidth = 1280;
					int windowHeight = 750;
					frame.setBounds(50, 100, windowWidth, windowHeight);
					frame.setResizable(false);
								
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Main entry point if bypassing database
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginWindow();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								
					int windowWidth = 1280;
					int windowHeight = 750;
					frame.setBounds(50, 100, windowWidth, windowHeight);
					frame.setResizable(false);
								
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Draw Login Window
	private void setScreen(){
		
		JLayeredPane loginPane = new JLayeredPane();
		
		JLabel backgroundPane = new ImagePanel(new ImageIcon("src/croyale/resources/casinobackground1.jpeg").getImage());
		backgroundPane.setOpaque(false);
		
		JPanel registerForm = new JPanel();
		registerForm.setLayout(new BoxLayout(registerForm,BoxLayout.Y_AXIS));
		registerForm.setOpaque(false);
		registerForm.setBounds(300, 200, 400, 200);
		registerForm.add(Box.createVerticalStrut(50));
		
		// Label for Name
		JPanel nameLabel = new JPanel();
		nameLabel.setLayout(new BoxLayout(nameLabel, BoxLayout.X_AXIS));
		nameLabel.setPreferredSize(new Dimension(100,20));
		JLabel nameLabelText = new JLabel("Name:");
		nameLabel.add(nameLabelText);
		nameLabel.add(Box.createHorizontalStrut(3));
		
		// Name Text Field Input area
		UserIDBox = new JTextField(20);
		UserIDBox.setText("");
		nameLabel.add(UserIDBox);
		registerForm.add(nameLabel);
		registerForm.add(Box.createVerticalStrut(20));
		
		// Label for User Password
		JPanel userPWLabel = new JPanel();
		userPWLabel.setLayout(new BoxLayout(userPWLabel, BoxLayout.X_AXIS));
		userPWLabel.setPreferredSize(new Dimension(100,20));
		JLabel userPWLabelText = new JLabel("Password:");
		userPWLabel.add(userPWLabelText);
		userPWLabel.add(Box.createHorizontalStrut(3));
				
		// UserPW Text Field Input area
		PasswordBox = new JTextField(20);
		PasswordBox.setText("");
		userPWLabel.add(PasswordBox);
		registerForm.add(userPWLabel);
		registerForm.add(Box.createVerticalStrut(20));
		
		// Add Login button
		OkButton = new JButton("Ok");
		OkButton.addActionListener(this);
		OkButton.setPreferredSize(new Dimension(150,25));
		registerForm.add(OkButton);
		registerForm.add(Box.createVerticalStrut(20));
		
		// Add Register button
		RegisterButton = new JButton("Register...");
		RegisterButton.addActionListener(this);
		RegisterButton.setPreferredSize(new Dimension(150,25));
		registerForm.add(RegisterButton);
		//registerForm.add(Box.createVerticalStrut(20));
		loginPane.add(registerForm,1);
		registerForm.setBounds(500,200,300,200);
		loginPane.add(backgroundPane,2);

		this.setContentPane(loginPane);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == OkButton){
			try{
				UserID = cs.checkPlayer(UserIDBox.getText(), PasswordBox.getText());
				if(UserID > 0){
					setGameScreen();
				}else{
					JOptionPane.showMessageDialog(null,"Invalid Username/Password.\n" +
							"Please try again, or if you are a new user,\n" +
							"please register first!",
							"Login Error", 
							JOptionPane.ERROR_MESSAGE);
					UserID=0;
				}
			}
			catch(Exception e1){
				System.out.println(e1.toString());
			}
		}
		else{
			RegistrationWindow rw = new RegistrationWindow(cs);
			rw.setVisible(true);
		}
	}
	
	// Draw main screen
	private void setGameScreen(){

		// Set background image
		JLabel contentPane = new ImagePanel(new ImageIcon("src/croyale/resources/casinobackground3.jpeg").getImage());
		
		// Initialize main layout
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.X_AXIS));
		JPanel leftPane = new JPanel();
		gameContainer = new JPanel();
		JPanel rightPane = new JPanel();
		
		// Set up Left Pane (player information)
		leftPane.setLayout(new BoxLayout(leftPane, BoxLayout.Y_AXIS));
		leftPane.setOpaque(false);
		
		JPanel leftPaneBottom = new JPanel();
		leftPaneBottom.setLayout(new BoxLayout(leftPaneBottom, BoxLayout.X_AXIS));
		leftPaneBottom.setOpaque(false);
		
		JPanel accountInfoBox = new JPanel();
		accountInfoBox.setLayout(new BoxLayout(accountInfoBox, BoxLayout.Y_AXIS));
		accountInfoBox.setBackground(new Color(0,176,80));
		accountInfoBox.setMaximumSize(new Dimension(190,150));
		accountInfoBox.setOpaque(true);
		
		JLabel accountInfoBoxTitle = new JLabel("Account Info");
		accountInfoBox.add(accountInfoBoxTitle);
		JLabel nameText = new JLabel("Name:");
		accountInfoBox.add(nameText);
		JLabel balanceText = new JLabel("Balance:");
		accountInfoBox.add(balanceText);
		accountInfoBox.add(Box.createVerticalGlue());
		
		JPanel updateBox = new JPanel();
		updateBox.setLayout(new BoxLayout(updateBox,BoxLayout.X_AXIS));
		updateBox.setOpaque(false);
		JButton updateButton = new JButton("Update Info...");
		updateBox.add(Box.createHorizontalStrut(20));
		updateBox.add(updateButton);
		accountInfoBox.add(updateBox);
		accountInfoBox.add(Box.createVerticalStrut(20));
		
		JPanel logoutBox = new JPanel();
		logoutBox.setLayout(new BoxLayout(logoutBox,BoxLayout.X_AXIS));
		logoutBox.setOpaque(false);
		JButton logoutButton = new JButton("Log Off");
		logoutBox.add(Box.createHorizontalStrut(20));
		logoutBox.add(logoutButton);
		accountInfoBox.add(logoutBox);
		accountInfoBox.add(Box.createVerticalStrut(20));

		leftPaneBottom.add(Box.createHorizontalStrut(10));
		leftPaneBottom.add(accountInfoBox);
		leftPaneBottom.add(Box.createHorizontalStrut(20));

		leftPane.add(Box.createVerticalStrut(450));
		leftPane.add(Box.createHorizontalStrut(212));
		leftPane.add(leftPaneBottom);
		leftPane.add(Box.createVerticalStrut(10));
		
		// Set up middle Pane (game Container)
		gameContainer.setLayout(new BorderLayout());
		gameContainer.setOpaque(true);
		
		// Set up Right Pane (game selection)
		rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));
		rightPane.setOpaque(false);
		
		JButton blackjackButton = new ImageButton(new ImageIcon("src/croyale/resources/blackjackbutton.jpg").getImage());
		JButton slotmachineButton = new ImageButton(new ImageIcon("src/croyale/resources/slotmachinebutton.jpg").getImage());
		
		rightPane.add(Box.createHorizontalStrut(43));
		rightPane.add(Box.createVerticalStrut(15));
		rightPane.add(Box.createVerticalGlue());
		rightPane.add(blackjackButton);
		rightPane.add(Box.createVerticalStrut(15));
		rightPane.add(slotmachineButton);
		rightPane.add(Box.createVerticalStrut(600));
				
		contentPane.add(leftPane);
		contentPane.add(gameContainer);
		contentPane.add(rightPane);
		contentPane.add(Box.createHorizontalStrut(15));
		
		// Add actionListeners to buttons
		blackjackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("Height is: " + frame.gameContainer.getHeight() );
					System.out.println("Width is:" + frame.gameContainer.getWidth());
					new BlackjackMVC(frame.gameContainer);
				} 
				catch (Exception ee) {
					System.out.println("Could not create blackjack");
					ee.printStackTrace();
				}
			}
		 });
		slotmachineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new SlotMachineMVC(frame.gameContainer, frame.cs, frame.UserID);
				} 
				catch (Exception ee) {
					System.out.println("Could not create slot machine");
					ee.printStackTrace();
				}
			}
		 });
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setScreen();
				} 
				catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		 });
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RegistrationWindow rw = new RegistrationWindow(frame.UserID,frame.cs);
					rw.setVisible(true);
				} 
				catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		 });
		
		frame.setContentPane(contentPane);

		frame.setVisible(true);
	}
}