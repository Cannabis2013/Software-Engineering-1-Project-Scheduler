package managers;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import abstractions.AbstractManager;
import abstractions.ICustomObservable;
import abstractions.ICustomObserver;
import abstractions.Model;
import entities.ActivityEntity;
import models.ActivityModel;
import models.HourRegistrationModel;
import models.ItemModel;
import models.ProjectModel;

public class ProjectManager extends AbstractManager implements ICustomObservable, Serializable {
	
	private static final long serialVersionUID = -5891942827829344046L;
	protected List<ICustomObserver> observers = new ArrayList<ICustomObserver>();
	
	public Model ModelBySerial(String serial) throws Exception
	{
		return modelBySerial(serial);
	}
	
	public void addProject(ProjectModel project)
	{
		addModel(project);
	}
	
	public void removeProject(String id) throws Exception
	{
		removeModel(id);
	}
	
	public void removeProjectAt(int index) throws Exception
	{
		removeModelAt(index);
	}
	
	public ProjectModel project(String projectName) throws NullPointerException
	{
		return (ProjectModel) model(projectName);
	}
	
	public ProjectModel projectAt(int index) throws NullPointerException
	{
		return (ProjectModel) modelAt(index);
	}
	
	private List<ProjectModel> allProjects()
	{
		return models().stream().map(item -> (ProjectModel) item).collect(Collectors.toList());
	}
	
	public void addAbsenceActivity(ActivityModel activityAbsence)
	{
		for (ProjectModel project : allProjects()) {
			project.addActivity(activityAbsence);
		}
	}
	
	public void removeAbsenceActivity(String id)
	{
		for (ProjectModel project : allProjects())
			project.removeActivity(id);
	}
	
    public void addActivity(String projectIdentity, ActivityModel activity) throws Exception
    {
        ProjectModel project = project(projectIdentity);
		
        project.addActivity(activity);
    }

    public boolean removeActivityModel(String projectId, String activityId) throws Exception
    {
    	
        ProjectModel project = project(projectId);
        ActivityModel activity = project.activity(activityId);
        if (activity == null)
            return false;
        project.removeActivity(activity);
        return true;
    }

    public ActivityModel activityById(String projectIdentity, String activityId) throws Exception
    {
        ProjectModel project = project(projectIdentity);
        
        try {
			return project.Activities().stream().
					filter(item -> item.modelId().equals(activityId)).collect(Collectors.toList()).get(0);
		} catch (Exception e) {
			throw new Exception("The list probably return a null point which means the object doesn't exists");
		}   
    }
    
    public ActivityModel activityById(String activityId) throws Exception
    {
    	try {
			return activityModels().stream().
					filter(item -> item.modelId().equals(activityId)).collect(Collectors.toList()).get(0);
		} catch (Exception e) {
			throw new Exception("Activity by the given id doesn't exists.");
		}
    }

    public List<ActivityModel> activityModels()
    {
        List<ActivityModel> resultingList = new ArrayList<ActivityModel>();
        for (ProjectModel project : allProjects())
        {
            for(ActivityModel activity : project.Activities())
            	resultingList.add(activity);
        }

        return resultingList;
    }

    public List<ActivityModel> activityModels(String userName)
    {
        List<ActivityModel> resultingList = new ArrayList<ActivityModel>();
        for (Model model : models())
        {
            ProjectModel project = (ProjectModel) model;
            List<ActivityModel> userActivities = project.Activities(userName);
            resultingList.addAll(userActivities);
        }

        return resultingList;
    }

    public void RegisterHour(String projectId, String activityId, HourRegistrationModel obj) throws Exception
    {
        ActivityModel activity;
		try {
			activity = activityById(projectId, activityId);
		} catch (Exception e) {
			throw new Exception("The activity doesn't exists");
		}
        activity.addRegistrationModel(obj);
    }

    public void UnRegisterHour(String projectId, String activityId, String regId) throws Exception
    {
        ActivityModel activity = activityById(projectId, activityId);
        activity.removeRegistrationModel(regId);
    }

    public HourRegistrationModel getHourRegistrationModel(String regId)
    {
        Stream<ActivityModel> activityModels = activityModels().stream().map(item -> item);
        Stream<List<HourRegistrationModel>> hourRegModels = activityModels.map(item -> item.hourRegistrationModels());
        List<HourRegistrationModel> modelsContainsRegId = 
        		hourRegModels.filter(item -> item.stream().
        				anyMatch(subItem -> subItem.modelId().equals(regId))).collect(Collectors.toList()).get(0);
        
        return (HourRegistrationModel) modelsContainsRegId.stream().
        		filter(item -> item.modelId().equals(regId)).distinct().collect(Collectors.toList()).get(0);
    }

    public HourRegistrationModel getHourRegistrationModel(String projectId, String activityId,String regId) throws Exception
    {
    	return project(projectId).activity(activityId).hourRegistrationModel(regId);
    }
    
    // Item models
    
    public ItemModel[] ProjectItemModels()
    {
        int count = models().size(), index = 0;
        ItemModel[] models = new ItemModel[count];

        for (Model p : models())
            models[index++] = p.itemModel();

        return models;
    }
    
    public ItemModel[] ProjectItemModels(String username)
    {
        int count = models().size(), index = 0;
        ItemModel[] models = new ItemModel[count];

        for (ProjectModel project : allProjects())
        {
        	if(project.projectLeaderId().equals(username))
        		models[index++] = project.itemModel();        	
        }

        return models;
    }

    public ItemModel[] activityItemModels(UserManager uManager)
    {
        if (uManager.isAdmin())
        {
        	List<ItemModel> allModels = new ArrayList<ItemModel>();
        	for (ProjectModel project : allProjects()) {
				Stream<ItemModel> activityItemModels = project.Activities().stream().map(item -> item.itemModel());
				allModels.addAll(activityItemModels.collect(Collectors.toList()));
			}
        	return allModels.toArray(new ItemModel[allModels.size()]);
        }
        else
        	return activityItemModels(uManager.currentUser().modelId());
    }

    public ItemModel[] activityItemModels(String userName)
    {
    	List<ItemModel> allModels = new ArrayList<ItemModel>();
    	for (ProjectModel project : allProjects()) {
			Stream<ActivityModel> userAssignedActivities = 
					project.Activities().stream().filter(item -> ((ActivityModel) item).isUserAssigned(userName));
			
			Stream<ItemModel> itemModels = userAssignedActivities.map(item -> item.itemModel());
			allModels.addAll(itemModels.collect(Collectors.toList()));
		}
    	
    	return allModels.toArray(new ItemModel[allModels.size()]);
    }

    public ItemModel[] RegistrationItemModels()
    {
        List<ItemModel> regModels = new ArrayList<ItemModel>();
        for (ProjectModel project : allProjects())
        {
            for (ActivityModel activity : project.Activities())
            {
                List<ItemModel> models = activity.HourRegistrationItemModels();
                regModels.addAll(models);
            }
        }

        return regModels.toArray(new ItemModel[regModels.size()]);
    }

    public ItemModel[] RegistrationItemModels(String userName)
    {
    	List<ItemModel> regItemModels = new ArrayList<ItemModel>();
        for (ProjectModel project : allProjects())
        {
            for (ActivityModel activity : project.Activities())
            {
                List<HourRegistrationModel> models = activity.hourRegistrationModels().stream().
                		filter(item -> item.userId() == userName).collect(Collectors.toList());
                for(HourRegistrationModel rModel : models)
                	regItemModels.add(rModel.itemModel());
                
            }
        }

        return regItemModels.toArray(new ItemModel[regItemModels.size()]);
    }

    public String userAvailability(String userName, LocalDate fromDate, LocalDate toDate)
    {
        int partlyOccurrences = 0, fullOccurrences = 0;
        
        List<ActivityEntity> userActivityEntities = UserActivityEntities(userName);
        
        boolean hasAnyAbsenceActivities = userActivityEntities.stream().
        		anyMatch(item -> item.TypeOfActivity() == ActivityModel.ActivityType.Absent_Related);
        
        if(userActivityEntities.size() < 20 && !hasAnyAbsenceActivities)
        	return "Available";

        for (ActivityEntity item : userActivityEntities)
        {
            if(item.TypeOfActivity() == ActivityModel.ActivityType.Work_Related)
            {
                if (fromDate.compareTo(item.StartDate()) < 0  && toDate.compareTo(item.EndDate()) > 0)
                    partlyOccurrences++;
                else if (fromDate.compareTo(item.StartDate()) < 0 && item.withinTimespan(toDate))
                    partlyOccurrences++;
                else if (item.withinTimespan(fromDate) && toDate.compareTo(item.EndDate()) > 0)
                    partlyOccurrences++;
                else if (item.strictlyWithinTimespan(fromDate) && item.strictlyWithinTimespan(toDate))
                	partlyOccurrences++;
                else if (item.withinTimespan(fromDate) && item.withinTimespan(toDate))
                    fullOccurrences++;
            }
            else
            {
                if (fromDate.compareTo(item.StartDate()) < 0  && toDate.compareTo(item.EndDate()) > 0)
                    return "Partly available";
                else if (fromDate.compareTo(item.StartDate()) < 0 && item.withinTimespan(toDate))
                    return "Partly available";
                else if (item.withinTimespan(fromDate) && toDate.compareTo(item.EndDate()) > 0)
                    return "Partly available";
                else if (item.strictlyWithinTimespan(fromDate) && item.strictlyWithinTimespan(toDate))
                    return "Partly available";
                else if (item.withinTimespan(fromDate) && item.withinTimespan(toDate))
                    return "Not available";
            }
        }

        if (fullOccurrences >= 20)
            return "Not available";
        return partlyOccurrences + fullOccurrences >= 20
            ? "Partly available"
            : "Available";
    }

    private List<ActivityEntity> UserActivityEntities(String userName)
    {
    	return activityModels().stream().map(item -> new ActivityEntity(item.modelId(), item.startDate(), item.endDate(), item.TypeOfActivity())).collect(Collectors.toList());
        
    }

    public List<String> allProjectNames()
    {
    	return models().stream().map(item -> item.modelId()).collect(Collectors.toList());
    }

    public void RequestUpdate()
    {
        NotifyObservers();
    }

    public void NotifyObservers()
    {
        for (ICustomObserver observer : observers)
        {
            observer.updateView();
        }
    }

    public void SubScribe(ICustomObserver observer)
    {
        observers.add(observer);
    }

    public void UnSubScribe(ICustomObserver observer)
    {
        observers.remove(observer);
    }

    public void UnSubScribeAll()
    {
        observers.clear();
    }

	@Override
	public void requestUpdate() {
		for (ICustomObserver observer : observers)
			observer.updateView();
	}
}
