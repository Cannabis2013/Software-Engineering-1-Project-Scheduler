package Application_Facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Abstractions.AbstractModel;
import Abstractions.IApplicationProgrammingInterface;
import Abstractions.ICustomObserver;
import ProjectDomain.ProjectManager;
import UserDomain.UserManager;
import formComponents.ItemModel;
import models.ActivityModel;
import models.ProjectModel;
import models.UserModel;
import models.HourRegistrationModel;

public class ApplicationCore implements IApplicationProgrammingInterface {
	
	private String FileName = "ProjectFile";
    private ProjectManager pManager = new ProjectManager();
    private UserManager uManager = new UserManager();

    public ApplicationCore()
    {
    }

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

    public String userAvailability(String username, Date sDate, Date eDate)
    {
        return pManager.UserAvailability(username, uManager, sDate, eDate);

    }

    public String addProject(ProjectModel newProject)
    {
        if (!uManager.isAdmin())
            return "Admin privligges required.";

        pManager.addModel(newProject);

        return "";
    }

    public String removeProject(int index)
    {
        if (!uManager.isAdmin())
            return "Admin privilliges required";

        pManager.removeModelAt(index);

        return "";
    }

    public String removeProject(String identity)
    {
        if (!uManager.isAdmin())
            return "Admin privilliges required";
        
        pManager.removeModel(identity);

        return "";
    }

    public void removeProject(ProjectModel project)
    {
        // Not defined yet.
    }

    public ProjectModel project(int index)
    {
        return pManager.projectAt(index);
    }

    public ProjectModel project(String identity)
    {
        return pManager.project(identity);
    }

    public ItemModel[] projectItemModels()
    {
        return pManager.ProjectItemModels();
    }

    public ItemModel[] projectItemModels(String UserIdentity)
    {
    	Stream<AbstractModel> projects = 
    			pManager.models().stream().filter(item -> ((ProjectModel) item).projectLeaderId().equals(UserIdentity));
        return projects.map(AbstractModel::itemModel).toArray(ItemModel[]::new);
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
        for (AbstractModel model : pManager.models())
            pManager.addActivity(model.modelIdentity(),activtity);
    }

    public void removeAbsenceActivity(String id)
    {
        for (AbstractModel model : pManager.models())
            pManager.RemoveActivityModel(model.modelIdentity(), id);
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
    	ActivityModel activity = pManager.activityModelById(projectId, activityId);
    	
    	if(activity == null)
    		return null;
    	
    	if(uManager.isAdmin() || ((ProjectModel) activity.Parent()).projectLeaderId().equals(currentUserName))
    		return activity;
    	else if(activity.IsUserAssigned(currentUserName))
    		return activity;
    	
    	return null;
    	
    }

    public List<ActivityModel> activitiesById(String activityId)
    {
        return pManager.activityModels().stream().
        		filter(item -> item.modelIdentity().equals(activityId)).collect(Collectors.toList());
    }

    public List<ActivityModel> activities()
    {
    	String currentLoggedinUserName = uManager.currentUser().modelIdentity();
        return uManager.isAdmin() ? pManager.activityModels() : 
            activities(currentLoggedinUserName);
    }

    public List<ActivityModel> activities(String userName)
    {
        return pManager.activityModels(userName);
    }

    public ItemModel[] activityItemModels()
    {
    	String currentLoggedinUserName = uManager.currentUser().modelIdentity();
        return uManager.isAdmin() ? pManager.activityItemModels(uManager) :
            activityItemModels(currentLoggedinUserName);
    }

    public ItemModel[] activityItemModels(String userName)
    {
        return pManager.activityItemModels(userName);
    }

    public void registerHour(String projectId, String activityId, String regId, int hours, String shortDescription) throws Exception
    {
        String userId = uManager.currentUser().modelIdentity();
        ActivityModel parentActivity = activity(projectId, activityId);
         new HourRegistrationModel(regId, hours, userId, shortDescription, parentActivity);
    }

    public void unRegisterHour(String projectId, String activityId, String regId)
    {
        pManager.UnRegisterHour(projectId,activityId,regId);
    }
    
    @Override
    public HourRegistrationModel hourRegistrationModel(String activityId, String regId)
    {
    	ActivityModel activity = pManager.activityModels().stream().
    			filter(item -> item.modelIdentity().equals(activityId)).collect(Collectors.toList()).get(0);
    	
    	if(activity == null)
    		return null;
    	
        String projectId = activity.parentModelIdentity();
        return pManager.getHourRegistrationModel(projectId, activityId, regId);
    }
    
    
	@Override
	public ItemModel[] hourRegistrationItemModels() {
		if (uManager.isAdmin())
            return pManager.RegistrationItemModels();
		
		String currentLoggedInUsername = uManager.currentUser().modelIdentity();
		
        return pManager.RegistrationItemModels(currentLoggedInUsername);
	}
    
    @Override
    public ItemModel[] hourRegistrationItemModels(String userName)
    {
        return pManager.RegistrationItemModels(userName);
    }
    
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
	public AbstractModel model(String serialId) {
		return pManager.modelBySerial(serialId);
	}
}
