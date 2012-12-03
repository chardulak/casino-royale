package croyale;

import java.awt.EventQueue;

import javax.swing.JFrame;

import croyale.security.ClientSecurity;

public class MainScreenMVC {
	MainScreenModel model;
	MainScreenView view;
	MainScreenController controller;
	ClientSecurity cs;

	public MainScreenMVC(ClientSecurity c){

		model = new MainScreenModel();
		this.cs = c;
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view = new MainScreenView();
					view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								
					int windowWidth = 1280;
					int windowHeight = 750;
					view.setBounds(50, 100, windowWidth, windowHeight);
					view.setResizable(false);
								
					view.setVisible(true);
					
					controller = new MainScreenController(model, view);
					controller.setClientSecurity(cs);
					controller.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public MainScreenMVC(){

		model = new MainScreenModel();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view = new MainScreenView();
					view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								
					int windowWidth = 1280;
					int windowHeight = 750;
					view.setBounds(50, 100, windowWidth, windowHeight);
					view.setResizable(false);
								
					view.setVisible(true);
					
					controller = new MainScreenController(model, view);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
