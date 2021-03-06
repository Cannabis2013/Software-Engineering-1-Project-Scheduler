/*
 * Martin Hansen
 */


package Application_Facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import abstractions.IApplicationProgrammingInterface;
import abstractions.ICustomObserver;
import managers.ProjectManager;
import managers.UserManager;
import models.ActivityModel;
import models.ProjectModel;
import models.UserModel;
import models.HourRegistrationModel;
import models.ItemModel;

public class ApplicationCore implements IApplicationProgrammingInterface {
	
	private String FileName = "ProjectFile";
    private ProjectManager pManager = new ProjectManager();
    private UserManager uManager = new UserManager();

    public ApplicationCore()
    {
    	
    }
    
    // Persistence
    public void writePersistence()
    {
        FileOutputStream saveFileStream = null;
        ObjectOutputStream serializer = null;
        try {
			saveFileStream = new FileOutputStream(FileName);
			serializer = new ObjectOutputStream(saveFileStream);
			if(pManager != null)
				serializer.writeObject(pManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @SuppressWarnings("unused")
	private void readPersistenceData()
    {
    	FileInputStream saveFileStream = null;
        ObjectInputStream deSerializer = null;
        try {
			saveFileStream = new FileInputStream(FileName);
			deSerializer = new ObjectInputStream(saveFileStream);
			pManager = (ProjectManager) deSerializer.readObject();
		} catch (Exception e) {
			pManager = new ProjectManager();
		}
    }

    public void login(String userName) throws Exception
    {
        uManager.logIn(userName);
    }

    public void logut()
    {
        uManager.logout();
    }

    public Boolean isAdmin()
    {
        return uManager.isAdmin();
    }

    public UserModel currentUserLoggedIn()
    {
        return uManager.currentUser();
    }

    public String[] allUserNames()
    {
        return uManager.listModelIdentities();
    }

    public List<ItemModel> userListModels()
    {
            return uManager.userItemModels();
    }

    public String userAvailability(String username, LocalDate sDate, LocalDate eDate)
    {
        return pManager.userAvailability(username, sDate, eDate);
    }
    
    public List<ItemModel> allUserAvailability(LocalDate sDate, LocalDate eDate)
    {
    	List<ItemModel> listModels = uManager.userItemModels();
    	return pManager.allUserAvailability(listModels,sDate, eDate);
    }
    // Models
    public void addProject(ProjectModel newProject) throws Exception
    {
        if (!uManager.isAdmin())
            throw new Exception("Admin privilliges required");
        
        pManager.addProject(newProject);
    }


    public void removeProject(String identity) throws Exception
    {
        if (!uManager.isAdmin())
            throw new Exception("Admin privilliges required");
        
        try {
			pManager.removeProject(identity);
		} catch (Exception e) {
			throw e;
		}
    }

    public ProjectModel project(String identity) throws Exception
    {
    	String userId = uManager.currentUser().UserName();
    	ProjectModel project = pManager.project(identity);
    	if(project.projectLeaderId().equals(userId) || isAdmin())
    		return project;
    	
    	throw new Exception("Not admin or projectleader for this project");
    }
    
    @Override
	public void addActivity(String projectTitle, ActivityModel activity) throws Exception {
		ProjectModel project = pManager.project(projectTitle);
		String currentUserId = uManager.currentUser().UserName();
		
		if(project.projectLeaderId().equals(currentUserId) || uManager.isAdmin())
			pManager.addActivity(projectTitle, activity);
		else
			throw new Exception();
	}

    public void addAbsenceActivity(ActivityModel activity) throws Exception
    {
    	
    	pManager.addAbsenceActivity(activity);
    }

    public void removeAbsenceActivity(String id) throws Exception
    {
    	pManager.removeAbsenceActivity(id);
    }

    public void removeActivity(String projectId, String activityId) throws Exception
    {
        ProjectModel project = pManager.project(projectId);
        String currentLoggedInUserName = uManager.currentUser().UserName();
        if (project.projectLeaderId().equals(currentLoggedInUserName) || uManager.isAdmin())
            pManager.removeActivityModel(projectId, activityId);
        else
        	throw new Exception("User not admin nor projectleader for the parent project.");
    }

    public ActivityModel activity(String projectId, String activityId) throws Exception
    {
    	String currentUserName = uManager.currentUser().UserName();
    	
    	ActivityModel activity = pManager.activityById(projectId, activityId);	
    	
    	if(uManager.isAdmin() || ((ProjectModel) activity.Parent()).projectLeaderId().equals(currentUserName))
    		return activity;
    	
    	throw new Exception("User isn't permitted to retrieve the selected activity.");
    }

    public List<ActivityModel> activities()
    {
    	String currentLoggedinUserName = uManager.currentUser().UserName();
        return uManager.isAdmin() ? pManager.activityModels() : 
            activities(currentLoggedinUserName);
    }

    public List<ActivityModel> activities(String userName)
    {
        return pManager.activityModels(userName);
    }
    
    public void registerHour(String projectId, String activityId, String regId, double hours, String shortDescription) throws Exception
    {
    	ActivityModel activity = pManager.activityById(projectId, activityId);
        	
        String userId = uManager.currentUser().UserName();
        if(!activity.isUserAssigned(userId))
        	throw new IllegalAccessException("User isn't assigned to the selected activity");
        
        HourRegistrationModel regModel = new HourRegistrationModel(regId, hours, userId, shortDescription);
        pManager.registerHour(projectId, activityId, regModel);
    }

    public void unRegisterHour(String projectId, String activityId, String regId) throws Exception
    {
    	HourRegistrationModel regModel = pManager.getHourRegistrationModel(projectId, activityId, regId);
    	if(!regModel.userId().equals(uManager.currentUser().UserName()) && !uManager.isAdmin())
    			throw new Exception("Current user is not allowed to do this");
        pManager.UnRegisterHour(projectId,activityId,regId);
    }
    
    @Override
    public HourRegistrationModel hourRegistrationModel(String projectId,String activityId, String regId) throws Exception
    {
    	ActivityModel activity;
		try {
			activity = pManager.activityById(projectId,activityId);
		} catch (Exception e) {
			throw e;
		}
    	
    	String userId = uManager.currentUser().UserName();
    	
    	if(!activity.isUserAssigned(userId) && !uManager.isAdmin())
    		throw new Exception("User is not assigned to this activity and is therefore not allowed to retrieve this object.");
    	
        return pManager.getHourRegistrationModel(projectId, activityId, regId);
    }
    
    // Item models
    
    public List<ItemModel> projectItemModels()
    {
    	return uManager.isAdmin() ?  pManager.ProjectItemModels() : 
    		 pManager.ProjectItemModels(currentUserLoggedIn().UserName());
    }

    public List<ItemModel> activityItemModels()
    {
    	String currentLoggedinUserName = uManager.currentUser().UserName();
        return uManager.isAdmin() ? pManager.activityItemModels(uManager) :
            activityItemModels(currentLoggedinUserName);
    }

    public List<ItemModel> activityItemModels(String userName)
    {
        return pManager.activityItemModels(userName);
    }
    
    @Override
	public List<ItemModel> projectActivityItemModels(String projectId) throws Exception
    {
		String userId = uManager.currentUser().UserName();
		ProjectModel project = pManager.project(projectId);
		if(!project.projectLeaderId().equals(userId) && !isAdmin())
			throw new Exception("User is not project leader for the selected project");
		
		return pManager.projectActivityItemModels(project);
	}
    
	@Override
	public List<ItemModel> hourRegistrationItemModels() {
		if (uManager.isAdmin())
            return pManager.RegistrationItemModels();
		
		String currentLoggedInUsername = uManager.currentUser().UserName();
		
        return pManager.RegistrationItemModels(currentLoggedInUsername);
	}
    
    @Override
    public List<ItemModel> hourRegistrationItemModels(String userName)
    {
        return pManager.RegistrationItemModels(userName);
    }
    
    // Observer/observable
    @Override
    public void subScribe(ICustomObserver observer)
    {
        pManager.SubScribe(observer);
    }
	
	@Override
    public void unSubScribe(ICustomObserver observer)
    {
        pManager.UnSubScribe(observer);
    }
	
	@Override
    public void unSubScribeAll()
    {
        pManager.UnSubScribeAll();
    }

	@Override
	public boolean isUserProjectLeader() {
		
		String userId = uManager.currentUser().UserName();
		return pManager.isProjectLeaderForAnyProject(userId);
		
	}

	@Override
	public void requestUpdate() {
		pManager.requestUpdate();
	}
}
