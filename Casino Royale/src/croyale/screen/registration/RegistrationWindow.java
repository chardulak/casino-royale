package croyale.screen.registration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import croyale.Session;
import croyale.security.ClientSecurity;
import croyale.util.Player;

public class RegistrationWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = 7956599113963455798L;
	
	private Session session;
	private ClientSecurity cs;
	private int UserID=0;
	
	private JTextField UserIDBox,PasswordBox,FirstNameBox,LastNameBox,BalanceBox,AddressBox,PhoneBox,EmailBox;
	private JButton OkButton,CancelButton, OkUpButton;
	private JLabel UserIDBoxT;
	
	
	public RegistrationWindow(ClientSecurity cs) {
		this.cs= cs;
		UserID=0;
		setWindow();
	}
	
	public RegistrationWindow(int id, ClientSecurity cs, Session s) {
		session = s;
		this.cs = cs;
		UserID = id;
		if(UserID >0){
			setUpdateWindow();
			fillFields();
		}
		else {
			setWindow();
		}
	}
	
	private void setUpdateWindow(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 200, 500, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setUpdateFields();
	}
	
	private void setWindow(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 200, 500, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setFields();
	}
	
	private void setFields(){
		Container registrationPane = new JPanel();
		registrationPane.setBackground(new Color(0,176,80));
		
		JPanel registerForm = new JPanel();
		registerForm.setLayout(new BoxLayout(registerForm,BoxLayout.Y_AXIS));
		registerForm.setOpaque(false);
		registerForm.setBounds(50, 200, 400, 400);
		
		registerForm.add(Box.createVerticalStrut(50));
		
		// Label for UserID
		JPanel UserIDPanel = new JPanel();
		UserIDPanel.setLayout(new BoxLayout(UserIDPanel, BoxLayout.X_AXIS));
		UserIDPanel.setPreferredSize(new Dimension(100,20));
		JLabel nameLabelText = new JLabel("UserID:");
		UserIDPanel.add(nameLabelText);
		UserIDPanel.add(Box.createHorizontalStrut(3));
				
		// UserID Text Field Input area
		UserIDBox = new JTextField(20);
		UserIDBox.setText("");
		UserIDPanel.add(UserIDBox);
		registerForm.add(UserIDPanel);
		registerForm.add(Box.createVerticalStrut(20));
		
		// Label for Password
		JPanel PasswordPanel = new JPanel();
		PasswordPanel.setLayout(new BoxLayout(PasswordPanel, BoxLayout.X_AXIS));
		PasswordPanel.setPreferredSize(new Dimension(100,20));
		JLabel PasswordLabel = new JLabel("Password:");
		PasswordPanel.add(PasswordLabel);
		PasswordPanel.add(Box.createHorizontalStrut(3));
								
		// Password Text Field Input area
		PasswordBox = new JTextField(20);
		PasswordBox.setText("");
		PasswordPanel.add(PasswordBox);
		registerForm.add(PasswordPanel);
		registerForm.add(Box.createVerticalStrut(20));
				
		// Label for First Name
		JPanel FirstNamePanel = new JPanel();
		FirstNamePanel.setLayout(new BoxLayout(FirstNamePanel, BoxLayout.X_AXIS));
		FirstNamePanel.setPreferredSize(new Dimension(100,20));
		JLabel FirstNameLabel = new JLabel("First Name:");
		FirstNamePanel.add(FirstNameLabel);
		FirstNamePanel.add(Box.createHorizontalStrut(3));
						
		// First Name Text Field Input area
		FirstNameBox = new JTextField(20);
		FirstNameBox.setText("");
		FirstNamePanel.add(FirstNameBox);
		registerForm.add(FirstNamePanel);
		registerForm.add(Box.createVerticalStrut(20));
		
		// Label for Last Name
		JPanel LastNamePanel = new JPanel();
		LastNamePanel.setLayout(new BoxLayout(LastNamePanel, BoxLayout.X_AXIS));
		LastNamePanel.setPreferredSize(new Dimension(100,20));
		JLabel LastNameLabel = new JLabel("Last Name:");
		LastNamePanel.add(LastNameLabel);
		LastNamePanel.add(Box.createHorizontalStrut(3));
								
		// Last Name Text Field Input area
		LastNameBox = new JTextField(20);
		LastNameBox.setText("");
		LastNamePanel.add(LastNameBox);
		registerForm.add(LastNamePanel);
		registerForm.add(Box.createVerticalStrut(20));
		
		// Label for Balance
		JPanel BalancePanel = new JPanel();
		BalancePanel.setLayout(new BoxLayout(BalancePanel, BoxLayout.X_AXIS));
		BalancePanel.setPreferredSize(new Dimension(100,20));
		JLabel BalanceLabel = new JLabel("Balance:");
		BalancePanel.add(BalanceLabel);
		BalancePanel.add(Box.createHorizontalStrut(3));
										
		// Balance Text Field Input area
		BalanceBox = new JTextField(20);
		BalanceBox.setText("");
		BalancePanel.add(BalanceBox);
		registerForm.add(BalancePanel);
		registerForm.add(Box.createVerticalStrut(20));
		
		// Label for address
		JPanel AddressPanel = new JPanel();
		AddressPanel.setLayout(new BoxLayout(AddressPanel, BoxLayout.X_AXIS));
		AddressPanel.setPreferredSize(new Dimension(100,20));
		JLabel AddressLabel = new JLabel("Address:");
		AddressPanel.add(AddressLabel);
		AddressPanel.add(Box.createHorizontalStrut(3));
												
		// Address Text Field Input area
		AddressBox = new JTextField(20);
		AddressBox.setText("");
		AddressPanel.add(AddressBox);
		registerForm.add(AddressPanel);
		registerForm.add(Box.createVerticalStrut(20));
		
		// Label for Phone
		JPanel PhonePanel = new JPanel();
		PhonePanel.setLayout(new BoxLayout(PhonePanel, BoxLayout.X_AXIS));
		PhonePanel.setPreferredSize(new Dimension(100,20));
		JLabel PhoneLabel = new JLabel("Phone:");
		PhonePanel.add(PhoneLabel);
		PhonePanel.add(Box.createHorizontalStrut(3));
														
		// Phone Text Field Input area
		PhoneBox = new JTextField(20);
		PhoneBox.setText("");
		PhonePanel.add(PhoneBox);
		registerForm.add(PhonePanel);
		registerForm.add(Box.createVerticalStrut(20));
		
		// Label for Email
		JPanel EmailPanel = new JPanel();
		EmailPanel.setLayout(new BoxLayout(EmailPanel, BoxLayout.X_AXIS));
		EmailPanel.setPreferredSize(new Dimension(100,20));
		JLabel EmailLabel = new JLabel("Email:");
		EmailPanel.add(EmailLabel);
		EmailPanel.add(Box.createHorizontalStrut(3));
																
		// Email Text Field Input area
		EmailBox = new JTextField(20);
		EmailBox.setText("");
		EmailPanel.add(EmailBox);
		registerForm.add(EmailPanel);
		registerForm.add(Box.createVerticalStrut(20));		
		
		OkButton = new JButton("Ok");
		OkButton.addActionListener(this);
		OkButton.setPreferredSize(new Dimension(150,25));
		registerForm.add(OkButton);
		registerForm.add(Box.createVerticalStrut(20));
		
		CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(this);
		CancelButton.setPreferredSize(new Dimension(150,25));
		registerForm.add(CancelButton);
		registerForm.add(Box.createVerticalStrut(20));
		
		registrationPane.add(registerForm);
		this.add(registrationPane);
	}
	
	private void setUpdateFields(){
		Container registrationPane = new JPanel();
		registrationPane.setBackground(new Color(0,176,80));
		
		JPanel registerForm = new JPanel();
		registerForm.setLayout(new BoxLayout(registerForm,BoxLayout.Y_AXIS));
		registerForm.setOpaque(false);
		registerForm.setBounds(50, 200, 400, 400);
		
		registerForm.add(Box.createVerticalStrut(50));
		
		// Label for UserID
		JPanel UserIDPanel = new JPanel();
		UserIDPanel.setLayout(new BoxLayout(UserIDPanel, BoxLayout.X_AXIS));
		UserIDPanel.setPreferredSize(new Dimension(100,20));
		JLabel nameLabelText = new JLabel("UserID:");
		UserIDPanel.add(nameLabelText);
		UserIDPanel.add(Box.createHorizontalStrut(3));
				
		// UserID Text Field Input area
		UserIDBoxT = new JLabel();
		UserIDBoxT.setText("");
		UserIDPanel.add(UserIDBoxT);
		registerForm.add(UserIDPanel);
		registerForm.add(Box.createVerticalStrut(20));
				
		// Label for First Name
		JPanel FirstNamePanel = new JPanel();
		FirstNamePanel.setLayout(new BoxLayout(FirstNamePanel, BoxLayout.X_AXIS));
		FirstNamePanel.setPreferredSize(new Dimension(100,20));
		JLabel FirstNameLabel = new JLabel("First Name:");
		FirstNamePanel.add(FirstNameLabel);
		FirstNamePanel.add(Box.createHorizontalStrut(3));
						
		// First Name Text Field Input area
		FirstNameBox = new JTextField(20);
		FirstNameBox.setText("");
		FirstNamePanel.add(FirstNameBox);
		registerForm.add(FirstNamePanel);
		registerForm.add(Box.createVerticalStrut(20));
		
		// Label for Last Name
		JPanel LastNamePanel = new JPanel();
		LastNamePanel.setLayout(new BoxLayout(LastNamePanel, BoxLayout.X_AXIS));
		LastNamePanel.setPreferredSize(new Dimension(100,20));
		JLabel LastNameLabel = new JLabel("Last Name:");
		LastNamePanel.add(LastNameLabel);
		LastNamePanel.add(Box.createHorizontalStrut(3));
								
		// Last Name Text Field Input area
		LastNameBox = new JTextField(20);
		LastNameBox.setText("");
		LastNamePanel.add(LastNameBox);
		registerForm.add(LastNamePanel);
		registerForm.add(Box.createVerticalStrut(20));
		
		// Label for Balance
		JPanel BalancePanel = new JPanel();
		BalancePanel.setLayout(new BoxLayout(BalancePanel, BoxLayout.X_AXIS));
		BalancePanel.setPreferredSize(new Dimension(100,20));
		JLabel BalanceLabel = new JLabel("Balance:");
		BalancePanel.add(BalanceLabel);
		BalancePanel.add(Box.createHorizontalStrut(3));
										
		// Balance Text Field Input area
		BalanceBox = new JTextField(20);
		BalanceBox.setText("");
		BalancePanel.add(BalanceBox);
		registerForm.add(BalancePanel);
		registerForm.add(Box.createVerticalStrut(20));
		
		// Label for address
		JPanel AddressPanel = new JPanel();
		AddressPanel.setLayout(new BoxLayout(AddressPanel, BoxLayout.X_AXIS));
		AddressPanel.setPreferredSize(new Dimension(100,20));
		JLabel AddressLabel = new JLabel("Address:");
		AddressPanel.add(AddressLabel);
		AddressPanel.add(Box.createHorizontalStrut(3));
												
		// Address Text Field Input area
		AddressBox = new JTextField(20);
		AddressBox.setText("");
		AddressPanel.add(AddressBox);
		registerForm.add(AddressPanel);
		registerForm.add(Box.createVerticalStrut(20));
		
		// Label for Phone
		JPanel PhonePanel = new JPanel();
		PhonePanel.setLayout(new BoxLayout(PhonePanel, BoxLayout.X_AXIS));
		PhonePanel.setPreferredSize(new Dimension(100,20));
		JLabel PhoneLabel = new JLabel("Phone:");
		PhonePanel.add(PhoneLabel);
		PhonePanel.add(Box.createHorizontalStrut(3));
														
		// Phone Text Field Input area
		PhoneBox = new JTextField(20);
		PhoneBox.setText("");
		PhonePanel.add(PhoneBox);
		registerForm.add(PhonePanel);
		registerForm.add(Box.createVerticalStrut(20));
		
		// Label for Email
		JPanel EmailPanel = new JPanel();
		EmailPanel.setLayout(new BoxLayout(EmailPanel, BoxLayout.X_AXIS));
		EmailPanel.setPreferredSize(new Dimension(100,20));
		JLabel EmailLabel = new JLabel("Email:");
		EmailPanel.add(EmailLabel);
		EmailPanel.add(Box.createHorizontalStrut(3));
																
		// Email Text Field Input area
		EmailBox = new JTextField(20);
		EmailBox.setText("");
		EmailPanel.add(EmailBox);
		registerForm.add(EmailPanel);
		registerForm.add(Box.createVerticalStrut(20));		
		
		OkUpButton = new JButton("Ok");
		OkUpButton.addActionListener(this);
		OkUpButton.setPreferredSize(new Dimension(150,25));
		registerForm.add(OkUpButton);
		registerForm.add(Box.createVerticalStrut(20));
		
		CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(this);
		CancelButton.setPreferredSize(new Dimension(150,25));
		registerForm.add(CancelButton);
		registerForm.add(Box.createVerticalStrut(20));
		
		registrationPane.add(registerForm);
		this.add(registrationPane);
	}
	
	private boolean checkFields(){
		boolean flg = true;
		if(UserIDBox.getText().trim().length() == 0){
			flg = false;
		}
		if(PasswordBox.getText().trim().length() == 0){
			flg = false;
		}
		if(FirstNameBox.getText().trim().length() == 0){
			flg = false;
		}
		if(LastNameBox.getText().trim().length() == 0){
			flg = false;
		}
		if(BalanceBox.getText().trim().length() == 0){
			flg = false;
		}
		if(AddressBox.getText().trim().length() == 0){
			flg = false;
		}
		if(EmailBox.getText().trim().length() == 0){
			flg = false;
		}
		return flg;
	}
	
	private void fillFields(){
		
		try{
			Player player = cs.getPlayer(UserID);
			FirstNameBox.setText(player.getFirstName());
			LastNameBox.setText(player.getLastName());
			UserIDBoxT.setText(player.getUserName());
			//PasswordBox.setText(player.getPassword());
			BalanceBox.setText(Double.toString(player.getBalance()));
			AddressBox.setText(player.getAddress());
			PhoneBox.setText(player.getPhone());
			EmailBox.setText(player.getEmail());
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"You are not Registered.","Error Message", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.toString());
		}
	}
	
	private void updatePlayer(){
		String msg="Your changes have been saved";
		try{
			cs.setPlayer(UserID,FirstNameBox.getText(),LastNameBox.getText(),UserIDBox.getText(),PasswordBox.getText(),AddressBox.getText(),PhoneBox.getText(), EmailBox.getText(), BalanceBox.getText());
			if(UserID == 0){
				UserID = cs.checkPlayer(UserIDBox.getText(),PasswordBox.getText());
				msg="Your account has been set up. Please login to play the games";
			}
			JOptionPane.showMessageDialog(null,msg,"Casino", JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"You are not Reigistred."+ LastNameBox.getText(),"Error Message", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.toString());
		}
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == OkButton){
			
			if(checkFields() == true){
				updatePlayer();
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(null,"You must fill in all fields.","Casino", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		else if (e.getSource() == OkUpButton){

			try{
				if(FirstNameBox.getText().trim().length() != 0){
					cs.setFirstName(UserID, FirstNameBox.getText());
				}
				if(LastNameBox.getText().trim().length() != 0){
					cs.setLastName(UserID, LastNameBox.getText());
				}
				if(BalanceBox.getText().trim().length() != 0){
					cs.setBalance(UserID, BalanceBox.getText());
					session.updateBalance(Double.parseDouble(BalanceBox.getText()));
				}
				if(AddressBox.getText().trim().length() != 0){
					cs.setAddress(UserID,AddressBox.getText());
				}
				if(EmailBox.getText().trim().length() != 0){
					cs.setEmail(UserID, EmailBox.getText());
				}
				if(PhoneBox.getText().trim().length() != 0){
					cs.setPhone(UserID, PhoneBox.getText());
				}
			}
			catch (Exception ee){
				JOptionPane.showMessageDialog(null,"You are not Reigistred."+ LastNameBox.getText(),"Error Message", JOptionPane.ERROR_MESSAGE);
				System.out.println(e.toString());
			}
			this.dispose();
		}
		else{
			this.dispose();
		}
		
	}
}

