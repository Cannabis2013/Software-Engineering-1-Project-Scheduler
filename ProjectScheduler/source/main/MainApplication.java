package main;

import java.awt.EventQueue;

import Application_Facade.ApplicationFrontEnd;


public class MainApplication {

	public MainApplication() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationFrontEnd facade = new ApplicationFrontEnd();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
