package croyale.screen.main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import croyale.Session;

public class MainScreenMVC {
	MainScreenModel model;
	MainScreenView view;
	MainScreenController controller;
	Session session;

	public MainScreenMVC(Session s){

		session = s;
		model = new MainScreenModel(session);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view = new MainScreenView();
					view.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					
					int windowWidth = 1280;
					int windowHeight = 750;
					view.setBounds(50, 100, windowWidth, windowHeight);
					view.setResizable(false);
								
					view.setVisible(true);
					
					controller = new MainScreenController(model, view);
					controller.setSession(session);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
}
