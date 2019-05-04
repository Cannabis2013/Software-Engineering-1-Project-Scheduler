package Application_Facade;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import forms.AddProject;
import forms.CustomWidgetFrame;
import forms.LoginDialog;
import forms.ProjectView;

public class ApplicationFrontEnd {
	
	private ApplicationCore coreApp = new ApplicationCore();
	
	public ApplicationFrontEnd() {
		launchLoginDialog();
	}
	
	public void launchMainView()
	{
		ProjectView pView = new ProjectView(this);
		pView.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				launchLoginDialog();
				pView.dispose();
			}
		});
	}
	
	public void launchAddProjectView() {
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

}
