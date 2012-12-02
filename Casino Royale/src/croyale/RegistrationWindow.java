package croyale;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.BoxLayout;

import croyale.rpc.ServerHostInterface;
import croyale.security.ClientSecurity;

public class RegistrationWindow extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JPanel labelPanel,fieldsPanel;
	private String labels[] = {"UserID","Password","First Name","Last Name","Balance","Address","Phone","Email"};
	private JTextField UserIDBox,PaswordBox,FirstNameBox,LastNameBox,BalanceBox,AddressBox,PhoneBox,EmailBox;
	private int UserID=0;
	private JButton OkButton,CancelButton;
//	private Database dbf = new Database();
	private ClientSecurity cs;
	
	public RegistrationWindow() {
		UserID=0;
		//System.out.println("UserID aa " + UserID);
		System.out.println("B");
		setWindow();
		//fillFields();
	}
	public RegistrationWindow(int _id, ClientSecurity cs) {
		this.cs = cs;
		
		UserID = _id;
		System.out.println("A");
		System.out.println("UserID 11 " + UserID);
		setWindow();
		if(UserID >0){
			fillFields();
		}
	}
	private void setWindow(){
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 200, 500, 600);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setFields();
		
		System.out.println("UserID 22 " + UserID);
		//setButtonLayout();
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
		PaswordBox = new JTextField(20);
		PaswordBox.setText("");
		PasswordPanel.add(PaswordBox);
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
	private boolean checkFields(){
		boolean flg = true;
		if(UserIDBox.getText().trim().length() == 0){
			flg = false;
		}
		if(PaswordBox.getText().trim().length() == 0){
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
	//private void setButtonLayout(){
		//setLayout(new GridLayout(1,2));
		
		//OkButton = new JButton("Ok");
		//add(OkButton);
		//CancelButton = new JButton("Cancel");
		//add(CancelButton);
		//OkButton.addActionListener(this);
		//CancelButton.addActionListener(this);
	//}
	private void fillFields(){
		//Database dbf = new Database();
		java.sql.ResultSet rs = null;
		
		System.out.println("fillFields " + UserID);
		
		try{
//			System.out.println(dbf.connectDBase());
			try{
//				rs = dbf.getPlayer(UserID);
				rs = cs.getPlayer(UserID);
				rs.next();
				//FirstNameBox.setText(rs.getString(2).toString());
				FirstNameBox.setText(rs.getString(2).trim());
				LastNameBox.setText(rs.getString(3).trim());
				UserIDBox.setText(rs.getString(4).trim());
				PaswordBox.setText(rs.getString(5).trim());
				BalanceBox.setText(rs.getString(6).trim());
				AddressBox.setText(rs.getString(7).trim());
				PhoneBox.setText(rs.getString(8).trim());
				EmailBox.setText(rs.getString(9).trim());
				
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null,"You are not Registered.","Error Message", JOptionPane.ERROR_MESSAGE);
				System.out.println(e2.toString());
			}
			
		}catch(Exception e1){
			System.out.println(e1.toString());
		}
	}
	private void updatePlayer(){
		//Database dbf = new Database();
		String msg="Your changes have been saved";
		try{
//			System.out.println(dbf.connectDBase());
			try{
				System.out.println("User ID 3 "+ UserID);
//				dbf.setPlayer(UserID,FirstNameBox.getText(),LastNameBox.getText(),UserIDBox.getText(),PaswordBox.getText(),AddressBox.getText(),PhoneBox.getText(), EmailBox.getText(), BalanceBox.getText());
				cs.setPlayer(UserID,FirstNameBox.getText(),LastNameBox.getText(),UserIDBox.getText(),PaswordBox.getText(),AddressBox.getText(),PhoneBox.getText(), EmailBox.getText(), BalanceBox.getText());
				if(UserID == 0){
//					UserID = dbf.checkPlayer(UserIDBox.getText(),PaswordBox.getText());
					UserID = cs.checkPlayer(UserIDBox.getText(),PaswordBox.getText());
					msg="Your account has been set up. Please login to play the games";
				}
				JOptionPane.showMessageDialog(null,msg,"Casino", JOptionPane.INFORMATION_MESSAGE);
			}catch(Exception e2){
				JOptionPane.showMessageDialog(null,"You are not Reigistred."+ LastNameBox.getText(),"Error Message", JOptionPane.ERROR_MESSAGE);
				System.out.println(e2.toString());
			}
		}catch(Exception e1){
			System.out.println(e1.toString());
		}
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == OkButton){
			
			if(checkFields() == true){
				updatePlayer();
				this.dispose();
				//openRegistrationWindow();
			}else{
				JOptionPane.showMessageDialog(null,"You must fill in all fields.","Casino", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}else{
			//openRegistrationWindow();
			this.dispose();
		}
		
	}
	private void openRegistrationWindow(){
		//MenuWindow mw = new MenuWindow(UserID);
		//mw.setVisible(true);
		//this.dispose();
	}
}

