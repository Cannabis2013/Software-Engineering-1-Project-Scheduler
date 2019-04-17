package ProjectDomain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public ItemModel[] ProjectItemModels()
    {
        int count = models().size(), index = 0;
        ItemModel[] models = new ItemModel[count];

        for (AbstractModel p : models())
            models[index++] = p.itemModel();

        return models;
    }

    public void addActivity(String projectIdentity, ActivityModel activity)
    {
        AbstractModel project = model(projectIdentity);
        project.addSubModel(activity);
    }

    public boolean RemoveActivityModel(String projectId, String activityId)
    {
        AbstractModel p = model(projectId);
        AbstractModel activity = p.subModel(activityId);
        if (activity == null)
            return false;
        p.removeSubModel(activity);
        return true;
    }

    public ActivityModel activityModelById(String projectIdentity, String activityIdentity)
    {
        AbstractModel project = model(projectIdentity);
        
        return (ActivityModel) project.subModels().stream().
        		filter(item -> item.modelIdentity().equals(activityIdentity)).collect(Collectors.toList()).get(0);
        
    }

    public List<ActivityModel> activityModels()
    {
        List<ActivityModel> resultingList = new ArrayList<ActivityModel>();
        for (AbstractModel project : models())
        {
            for(AbstractModel activity : project.subModels())
            {
            	resultingList.add((ActivityModel) activity);
            }
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

    public void RegisterHour(String projectId, String activityId, HourRegistrationModel obj)
    {
        ActivityModel activity = activityModelById(projectId, activityId);
        activity.addSubModel(obj);
    }

    public void UnRegisterHour(String projectId, String activityId, String regId)
    {
        ActivityModel activity = activityModelById(projectId, activityId);
        activity.removeSubModel(regId);
    }

    public HourRegistrationModel getHourRegistrationModel(String regId)
    {
        List<ActivityModel> activities = activityModels();

        for (ActivityModel activity : activities)
        {
            List<HourRegistrationModel> hourRegistrations = activity.HourRegistrationObjects();
            HourRegistrationModel regModel =  hourRegistrations.stream().filter(item -> item.modelIdentity().equals(regId)).collect(Collectors.toList()).get(0);
            if(regModel != null)
            	return regModel;
            
        }

        return null;
    }

    public HourRegistrationModel getHourRegistrationModel(String projectId, String activityId,String regId)
    {
    	return (HourRegistrationModel) model(projectId).subModel(activityId).subModel(regId);
    }

    public ItemModel[] activityItemModels(UserManager uManager)
    {
    	
        List<ItemModel> models = new ArrayList<ItemModel>();
        if (uManager.isAdmin())
        {
            for (AbstractModel item : models())
            {
                ProjectModel project = (ProjectModel)item;
                models.addAll(project.activityItemModels());
            }
            
            return models.toArray(new ItemModel[models.size()]);
        }

        String userId = uManager.currentUser().modelIdentity();

        for (AbstractModel item : models())
        {
            ProjectModel project = (ProjectModel)item;
            for (AbstractModel model : project.subModels())
            {
            	ActivityModel activity = (ActivityModel) model;
                if (!activity.IsUserAssigned(uManager) && project.projectLeaderId() != userId)
                    continue;

                
                models.add(activity.itemModel());
            }
        }
        
        return models.toArray(new ItemModel[models.size()]);
    }

    public ItemModel[] activityItemModels(String userName)
    {
    	List<ItemModel> allModels = new ArrayList<ItemModel>();
    	for (AbstractModel model : models())
        {
            ProjectModel project = (ProjectModel)model;
            
            Stream<AbstractModel> stream = project.subModels().stream().filter(item -> ((ActivityModel) item).IsUserAssigned(userName) && project.projectLeaderId() == userName);
            List<ItemModel> models = stream.map(AbstractModel::itemModel).collect(Collectors.toList());
            allModels.addAll(models);
        }
        
        return allModels.toArray(new ItemModel[allModels.size()]);
    }

    public ItemModel[] RegistrationItemModels()
    {
        List<ItemModel> regModels = new ArrayList<ItemModel>();
        for (AbstractModel project : models())
        {
            for (AbstractModel model : project.subModels())
            {
                ActivityModel activity = (ActivityModel) model;
                List<ItemModel> models = activity.allSubItemModels();
                regModels.addAll(models);
            }
        }

        return regModels.toArray(new ItemModel[regModels.size()]);
    }

    public ItemModel[] RegistrationItemModels(String userName)
    {
    	List<ItemModel> regItemModels = new ArrayList<ItemModel>();
        for (AbstractModel project : models())
        {
            for (AbstractModel model : project.subModels())
            {
                ActivityModel activity = (ActivityModel) model;
                List<AbstractModel> models = activity.subModels().stream().filter(item -> ((HourRegistrationModel) item).userName() == userName).collect(Collectors.toList());
                for(AbstractModel rModel : models)
                	regItemModels.add(rModel.itemModel());
                
            }
        }

        return regItemModels.toArray(new ItemModel[regItemModels.size()]);
    }

    public String UserAvailability(String userName, UserManager uManager, Date fromDate, Date toDate)
    {
        int partlyOccurrences = 0, fullOccurrences = 0;

        for (ActivityEntity item : UserActivityEntities(userName, uManager))
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
		// TODO Auto-generated method stub
		
	}

}
