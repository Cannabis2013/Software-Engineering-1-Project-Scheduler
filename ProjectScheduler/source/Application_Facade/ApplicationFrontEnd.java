package Application_Facade;


import forms.CustomWidgetFrame;
import forms.LoginDialog;
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
