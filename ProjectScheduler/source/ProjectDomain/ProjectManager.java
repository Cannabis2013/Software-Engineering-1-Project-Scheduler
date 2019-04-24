package ProjectDomain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.runtime.SubMonitor;

import Abstractions.AbstractModel;
import Abstractions.AbstractManager;
import Abstractions.ICustomObservable;
import Abstractions.ICustomObserver;
import UserDomain.UserManager;
import entities.ActivityEntity;
import formComponents.ItemModel;
import models.ActivityModel;
import models.HourRegistrationModel;
import models.ProjectModel;

public class ProjectManager extends AbstractManager implements ICustomObservable, Serializable {
	
	private static final long serialVersionUID = -5891942827829344046L;
	protected List<ICustomObserver> observers = new ArrayList<ICustomObserver>();
	
	private static String timeZoneId = "Europe/Copenhagen";
	
	public static int getDateProperty(Date date, int calProperty)
	{
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId));
		cal.setTime(date);
		return cal.get(calProperty);
	}
	
	public ProjectModel project(String projectName)
	{
		return (ProjectModel) model(projectName);
	}
	
	public ProjectModel projectAt(int index)
	{
		return (ProjectModel) modelAt(index);
	}
	
	private List<ProjectModel> allProjects()
	{
		return models().stream().map(item -> (ProjectModel) item).collect(Collectors.toList());
	}

    public void addActivity(String projectIdentity, ActivityModel activity)
    {
        AbstractModel project = model(projectIdentity);
        project.addSubModel(activity);
    }

    public boolean RemoveActivityModel(String projectId, String activityId)
    {
    	
        ProjectModel p = project(projectId);
        AbstractModel activity = p.activity(activityId);
        if (activity == null)
            return false;
        p.removeSubModel(activity);
        return true;
    }

    public ActivityModel activityModelById(String projectIdentity, String activityIdentity) throws Exception
    {
        ProjectModel project = project(projectIdentity);
        
        try {
			ActivityModel activity = project.Activities().stream().
					filter(item -> item.modelIdentity().equals(activityIdentity)).collect(Collectors.toList()).get(0);
			return activity;
		} catch (Exception e) {
			throw new Exception("The list probably return a null point which means the object doesn't exists");
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
        for (AbstractModel model : models())
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
			activity = activityModelById(projectId, activityId);
		} catch (Exception e) {
			throw new Exception("The activity doesn't exists");
		}
        activity.addSubModel(obj);
    }

    public void UnRegisterHour(String projectId, String activityId, String regId) throws Exception
    {
        ActivityModel activity = activityModelById(projectId, activityId);
        activity.removeSubModel(regId);
    }

    public HourRegistrationModel getHourRegistrationModel(String regId)
    {
    	
        Stream<ActivityModel> activityModels = activityModels().stream().map(item -> item);
        Stream<List<HourRegistrationModel>> hourRegModels = activityModels.map(item -> item.HourRegistrationObjects());
        List<HourRegistrationModel> modelsContainsRegId = 
        		hourRegModels.filter(item -> item.stream().
        				anyMatch(subItem -> subItem.modelIdentity().equals(regId))).collect(Collectors.toList()).get(0);
        
        return (HourRegistrationModel) modelsContainsRegId.stream().
        		filter(item -> item.modelIdentity().equals(regId)).distinct().collect(Collectors.toList()).get(0);
    }

    public HourRegistrationModel getHourRegistrationModel(String projectId, String activityId,String regId)
    {
    	return project(projectId).activity(activityId).hourRegistrationModel(regId);
    }
    
    // Item models
    
    public ItemModel[] ProjectItemModels()
    {
        int count = models().size(), index = 0;
        ItemModel[] models = new ItemModel[count];

        for (AbstractModel p : models())
            models[index++] = p.itemModel();

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
        	return activityItemModels(uManager.currentUser().modelIdentity());
    }

    public ItemModel[] activityItemModels(String userName)
    {
    	List<ItemModel> allModels = new ArrayList<ItemModel>();
    	for (ProjectModel project : allProjects()) {
			Stream<ActivityModel> userAssignedActivities = 
					project.Activities().stream().filter(item -> ((ActivityModel) item).IsUserAssigned(userName));
			
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
                List<ItemModel> models = activity.allSubItemModels();
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
                List<HourRegistrationModel> models = activity.HourRegistrationObjects().stream().
                		filter(item -> item.userName() == userName).collect(Collectors.toList());
                for(HourRegistrationModel rModel : models)
                	regItemModels.add(rModel.itemModel());
                
            }
        }

        return regItemModels.toArray(new ItemModel[regItemModels.size()]);
    }

    public String UserAvailability(String userName, UserManager uManager, LocalDate fromDate, LocalDate toDate)
    {
        int partlyOccurrences = 0, fullOccurrences = 0;
        
        List<ActivityEntity> userActivityEntities = UserActivityEntities(userName, uManager);
        
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

    private List<ActivityEntity> UserActivityEntities(String userName,UserManager uManager)
    {
    	return activityModels().stream().map(item -> new ActivityEntity(item.modelIdentity(), item.startDate(), item.endDate(), item.TypeOfActivity())).collect(Collectors.toList());
        
    }

    public List<String> ListModelIdentities()
    {
    	return models().stream().map(item -> item.modelIdentity()).collect(Collectors.toList());
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
