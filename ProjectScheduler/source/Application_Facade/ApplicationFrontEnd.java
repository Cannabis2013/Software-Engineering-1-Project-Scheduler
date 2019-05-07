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
		ProjectView pView = new ProjectView(this);
	}
	
	public void launchAddProject() {
		AddProject ap = new AddProject(this);
		ap.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				ap.dispose();
			}
		});
	}
	
	public void launchLoginDialog()
	{
		LoginDialog dialog = new LoginDialog(this,coreApp);
		dialog.setFrame(new CustomWidgetFrame());
		
		dialog.setVisible(true);
	}
	
	public void launchProjectDialog()
	{
		ProjectDialog dialog = new ProjectDialog(this);
		dialog.setFrame(new CustomWidgetFrame());
		
		dialog.setVisible(true);
	
	}

	public void launchAddActivity() {
		AddActivity aa = new AddActivity(this);
		aa.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				aa.dispose();
			}
		});
	}

}
