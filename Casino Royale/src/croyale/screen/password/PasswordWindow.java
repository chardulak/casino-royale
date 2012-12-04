package croyale.screen.password;

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

public class PasswordWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = 7956599113963455798L;
	
	private Session session;
	
	private JTextField PasswordBox, PasswordBox2;
	private JButton OkButton,CancelButton;
	
	public PasswordWindow(Session s) {
		session = s;
		setWindow();
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
		Container passwordPane = new JPanel();
		passwordPane.setBackground(new Color(0,176,80));
		
		JPanel registerForm = new JPanel();
		registerForm.setLayout(new BoxLayout(registerForm,BoxLayout.Y_AXIS));
		registerForm.setOpaque(false);
		registerForm.setBounds(50, 200, 400, 400);
		
		registerForm.add(Box.createVerticalStrut(50));
		
		// Label for Old Password
		JPanel PasswordPanel = new JPanel();
		PasswordPanel.setLayout(new BoxLayout(PasswordPanel, BoxLayout.X_AXIS));
		PasswordPanel.setPreferredSize(new Dimension(100,20));
		JLabel PasswordLabel = new JLabel("Old Password:");
		PasswordPanel.add(PasswordLabel);
		PasswordPanel.add(Box.createHorizontalStrut(3));
								
		// Password Text Field Input area
		PasswordBox = new JTextField(20);
		PasswordBox.setText("");
		PasswordPanel.add(PasswordBox);
		registerForm.add(PasswordPanel);
		registerForm.add(Box.createVerticalStrut(20));
		

		// Label for New Password
		JPanel PasswordPanel2 = new JPanel();
		PasswordPanel2.setLayout(new BoxLayout(PasswordPanel2, BoxLayout.X_AXIS));
		PasswordPanel2.setPreferredSize(new Dimension(100,20));
		JLabel PasswordLabel2 = new JLabel("New Password:");
		PasswordPanel2.add(PasswordLabel2);
		PasswordPanel2.add(Box.createHorizontalStrut(3));
								
		// Password Text Field Input area
		PasswordBox2 = new JTextField(20);
		PasswordBox2.setText("");
		PasswordPanel2.add(PasswordBox2);
		registerForm.add(PasswordPanel2);
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
		
		passwordPane.add(registerForm);
		this.add(passwordPane);
	}
	
	private boolean checkFields(){
		boolean flg = true;
		if(PasswordBox.getText().trim().length() == 0){
			flg = false;
		}
		if(PasswordBox2.getText().trim().length() == 0){
			flg = false;
		}
		return flg;
	}
	
	private void updatePlayer(){
		try{
			if (session.cs.checkPlayer(session.userIDString, PasswordBox.getText()) == session.UserID){
				session.cs.setPassword(session.UserID, PasswordBox2.getText());
				this.dispose();
			}
			else{
				JOptionPane.showMessageDialog(null,"Incorrect Password.","Casino", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch (Exception e){
			
		}
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == OkButton){
			
			if(checkFields() == true){
				updatePlayer();
			}else{
				JOptionPane.showMessageDialog(null,"You must fill in all fields.","Casino", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		else{
			this.dispose();
		}
		
	}
}

