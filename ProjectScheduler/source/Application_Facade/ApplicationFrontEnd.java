package Application_Facade;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import forms.AddActivity;
import forms.AddProject;
import forms.CustomWidgetFrame;
import forms.LoginDialog;
import forms.ProjectDialog;
import forms.ProjectView;

public class ApplicationFrontEnd {
	
	private ApplicationCore coreApp = new ApplicationCore();
	
	public ApplicationFrontEnd() {
		launchLoginDialog();
	}
	
	public void launchMainView()
	{
		ProjectView pView = new ProjectView(this,coreApp);
	}
	
	
	
	public void launchLoginDialog()
	{
		LoginDialog dialog = new LoginDialog(this, coreApp);
		dialog.setFrame(new CustomWidgetFrame());
		
		dialog.setVisible(true);
	}
	
	

}
