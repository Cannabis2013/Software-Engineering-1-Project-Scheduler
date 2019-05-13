/*
 * Martin Hansen
 */

package main;

import java.awt.EventQueue;

import Application_Facade.ApplicationFrontEnd;


public class MainApplication {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ApplicationFrontEnd();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
