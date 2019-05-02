package Application_Facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Abstractions.Model;
import Abstractions.IApplicationProgrammingInterface;
import Abstractions.ICustomObserver;
import formComponents.ItemModel;
import managers.ProjectManager;
import managers.UserManager;
import models.ActivityModel;
import models.ProjectModel;
import models.UserModel;
import models.HourRegistrationModel;

public class ApplicationCore implements IApplicationProgrammingInterface {
	
	private String FileName = "ProjectFile";
    private ProjectManager pManager = new ProjectManager();
    private UserManager uManager = new UserManager();

    public ApplicationCore()
    {}
    
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

    public Boolean login(String userName)
    {
        return uManager.logIn(userName);
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

    public List<String> userNames()
    {
        return uManager.listModelIdentities();
    }

    public ItemModel[] userListModels(Boolean IncludeAdmin)
    {
        if(uManager.isAdmin())
            return uManager.itemModels(IncludeAdmin);
        else
            return uManager.itemModels(false);
    }

    public String userAvailability(String username, LocalDate sDate, LocalDate eDate)
    {
        return pManager.UserAvailability(username, uManager, sDate, eDate);

    }
    
    // Models
    public String addProject(ProjectModel newProject)
    {
        if (!uManager.isAdmin())
            return "Admin privligges required.";
        
        pManager.addProject(newProject);

        return "";
    }

    public String removeProject(int index)
    {
        if (!uManager.isAdmin())
            return "Admin privilliges required";

        pManager.removeProjectAt(index);

        return "";
    }

    public String removeProject(String identity)
    {
        if (!uManager.isAdmin())
            return "Admin privilliges required";
        
        try {
			pManager.removeProject(identity);
		} catch (Exception e) {
			return e.getMessage();
		}

        return "";
    }

    public void removeProject(ProjectModel project) throws Exception 
    {
    	if(uManager.isAdmin())
    	{
    		try {
				pManager.removeProject(project.projectName());
			} catch (Exception e) {
				throw e;
			}
    	}
    }

    public ProjectModel project(int index)
    {
        return pManager.projectAt(index);
    }

    public ProjectModel project(String identity)
    {
        return pManager.project(identity);
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

    public void addAbsenceActivity(ActivityModel activtity)
    {
    	pManager.addAbsenceActivity(activtity);
    }

    public void removeAbsenceActivity(String id)
    {
    	pManager.removeAbsenceActivity(id);
    }

    public void removeActivity(String projectId, String activityId) throws Exception
    {
        ProjectModel project = pManager.project(projectId);
        String currentLoggedInUserName = uManager.currentUser().UserName();
        if (project.projectLeaderId().equals(currentLoggedInUserName) || uManager.isAdmin())
            pManager.RemoveActivityModel(projectId, activityId);
        else
        	throw new Exception("User not admin nor projectleader for the parent project.");
    }

    public ActivityModel activity(String projectId, String activityId)
    {
    	String currentUserName = uManager.currentUser().UserName();
    	ActivityModel activity;
		try {
			activity = pManager.activityById(projectId, activityId);
		} catch (Exception e) {
			return null;
		}
    	
    	if(uManager.isAdmin() || ((ProjectModel) activity.Parent()).projectLeaderId().equals(currentUserName))
    		return activity;
    	else if(activity.isUserAssigned(currentUserName))
    		return activity;
    	
    	return null;
    }

    public List<ActivityModel> activitiesById(String activityId)
    {
        return pManager.activityModels().stream().
        		filter(item -> item.modelId().equals(activityId)).collect(Collectors.toList());
    }

    public List<ActivityModel> activities()
    {
    	String currentLoggedinUserName = uManager.currentUser().modelId();
        return uManager.isAdmin() ? pManager.activityModels() : 
            activities(currentLoggedinUserName);
    }

    public List<ActivityModel> activities(String userName)
    {
        return pManager.activityModels(userName);
    }
    
    public void registerHour(String projectId, String activityId, String regId, int hours, String shortDescription) throws Exception
    {
        ActivityModel activity = activity(projectId, activityId);
        String userId = uManager.currentUser().modelId();
        if(!activity.isUserAssigned(userId))
        	throw new Exception("User isn't assigned to the selected activity");
        new HourRegistrationModel(regId, hours, userId, shortDescription, activity);
    }

    public void unRegisterHour(String projectId, String activityId, String regId) throws Exception
    {
    	HourRegistrationModel regModel = pManager.getHourRegistrationModel(projectId, activityId, regId);
    	if(!regModel.userId().equals(uManager.currentUser().UserName()) && !uManager.isAdmin())
    			throw new Exception("Current user is not allowed to do this");
        pManager.UnRegisterHour(projectId,activityId,regId);
    }
    
    @Override
    public HourRegistrationModel hourRegistrationModel(String activityId, String regId) throws Exception
    {
    	ActivityModel activity;
		try {
			activity = pManager.activityById(activityId);
		} catch (Exception e) {
			throw e;
		}
    	
    	String userId = uManager.currentUser().UserName();
    	
    	if(!activity.isUserAssigned(userId) && !uManager.isAdmin())
    		throw new Exception("User is not assigned to this activity and is therefore not allowed to retrieve this object.");
    	
        String projectId = activity.parentModelId();
        return pManager.getHourRegistrationModel(projectId, activityId, regId);
    }
    
    // Item models
    
    public ItemModel[] projectItemModels()
    {
        return pManager.ProjectItemModels();
    }

    public ItemModel[] projectItemModels(String UserIdentity)
    {
    	return pManager.ProjectItemModels(UserIdentity);
    }

    public ItemModel[] activityItemModels()
    {
    	String currentLoggedinUserName = uManager.currentUser().UserName();
        return uManager.isAdmin() ? pManager.activityItemModels(uManager) :
            activityItemModels(currentLoggedinUserName);
    }

    public ItemModel[] activityItemModels(String userName)
    {
        return pManager.activityItemModels(userName);
    }
    
	@Override
	public ItemModel[] hourRegistrationItemModels() {
		if (uManager.isAdmin())
            return pManager.RegistrationItemModels();
		
		String currentLoggedInUsername = uManager.currentUser().modelId();
		
        return pManager.RegistrationItemModels(currentLoggedInUsername);
	}
    
    @Override
    public ItemModel[] hourRegistrationItemModels(String userName)
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
	public Model modelBySerial(String serialId) {
		try {
			return pManager.ModelBySerial(serialId);
		} catch (Exception e) {
			return null;
		}
	}
}
