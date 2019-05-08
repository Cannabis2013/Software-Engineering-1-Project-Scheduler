package Application_Facade;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenuBar;

import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import forms.AddActivity;
import forms.AddProject;
import forms.CustomWidgetFrame;
import forms.LoginDialog;
import forms.ProjectDialog;
import forms.ProjectView;
import forms.UserView;

public class ApplicationFrontEnd {
	
	private ApplicationCore coreApp = new ApplicationCore();
	
	public ApplicationFrontEnd() {
		launchLoginDialog();
	}
	
	public void launchMainView()
	{
		ProjectView pView = new ProjectView(this,coreApp);
		pView.setFrame(new CustomWidgetFrame());
	}
	
	public void launchUserView()
	{
		UserView uView = new UserView(this, coreApp);
		uView.setFrame(new CustomWidgetFrame());
	}
	
	
	
	public void launchLoginDialog()
	{
		LoginDialog dialog = new LoginDialog(this, coreApp);
		dialog.setFrame(new CustomWidgetFrame());
		dialog.setVisible(true);
	}
	
	

}
