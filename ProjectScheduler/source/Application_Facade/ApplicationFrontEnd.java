package Application_Facade;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import forms.CustomDialog;
import forms.LoginDialog;
import forms.ProjectManagement;
import forms.UserView;
import models.ActivityModel;
import models.ProjectModel;

public class ApplicationFrontEnd {
	
	private ApplicationCore coreApp = new ApplicationCore();
	
	public ApplicationFrontEnd() {
		initializeItems();
		launchLoginDialog();
	}
	
	public void launchMainView()
	{
		ProjectManagement pView = new ProjectManagement(this,coreApp);
		pView.setFrame(new CustomDialog());
	}
	
	public void launchUserView()
	{
		UserView uView = new UserView(this, coreApp);
		uView.setFrame(new CustomDialog());
	}
	public void launchLoginDialog()
	{
		LoginDialog dialog = new LoginDialog(this, coreApp);
		dialog.setFrame(new CustomDialog());
		dialog.setVisible(true);
	}
	
	private void initializeItems()
	{
		try {
			coreApp.login("admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ProjectModel project = 
				new ProjectModel("FL", LocalDate.now(), LocalDate.now().plusMonths(1), "Test project");
		try {
			coreApp.addProject(project);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<String> assignedUsers = new ArrayList<>();
		assignedUsers.add("BB");
		assignedUsers.add("TT");
		assignedUsers.add("JW");
		ActivityModel activity1 = new ActivityModel("GUI Test", 
				LocalDate.now().plusDays(3), 
				LocalDate.now().plusDays(6), 
				25, assignedUsers, "Testing some GUI features");
		try {
			coreApp.addActivity(project.projectName(), activity1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		coreApp.logut();
	}
}
