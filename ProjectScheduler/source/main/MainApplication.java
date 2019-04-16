package main;

import java.awt.EventQueue;

import Application_Facade.GUIFacade;


public class MainApplication {

	public MainApplication() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					GUIFacade facade = new GUIFacade();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
