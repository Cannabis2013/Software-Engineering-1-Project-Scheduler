package Application_Facade;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import UserDomain.UserManager;
import forms.LoginDialog;
import forms.ProjectView;

public class ApplicationFrontEnd {
	
	private UserManager uManager = new UserManager();
	
	public ApplicationFrontEnd() {
		launchLoginDialog();
	}
	
	public void validateLogin(String userName)
	{
		if(uManager.logIn(userName))
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
		else
			launchLoginDialog();
	}
	
	public void launchLoginDialog()
	{
		LoginDialog dialog = new LoginDialog(this);
		dialog.setVisible(true);
	}

}
