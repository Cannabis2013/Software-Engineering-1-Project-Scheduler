package models;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.ibm.icu.impl.duration.DateFormatter;

import abstractions.Model;
import managers.UserManager;

public class ActivityModel extends Model {
	
	private static final long serialVersionUID = 1L;
	private List<String> assignedUserIdentities = new ArrayList<String>();
    private LocalDate sDate, eDate;
    private int estimatedWorkHours;
    private String reason;
    private ActivityType Type = ActivityType.Work_Related;

    public enum ActivityType
    {
        Work_Related,
        Absent_Related
    };
    
    public ActivityModel(String activityTitle,
            LocalDate sDate, 
            LocalDate eDate,
            int estimatedWorkHours,
            List<String> assignedUsers,
            String description
            ) throws IllegalArgumentException
        {
            setModelidentity(activityTitle);
            this.sDate = sDate;
            this.eDate = eDate;
            
            if(eDate.compareTo(sDate) < 0)
            	throw new IllegalArgumentException("The end date is before the start date.");
            
            this.estimatedWorkHours = estimatedWorkHours;
            
            if(assignedUsers != null)
            	assignedUserIdentities = assignedUsers;            
            
            setDescription(description);
        }
    
    public ActivityModel(String activityTitle,
        LocalDate sDate, 
        LocalDate eDate
        ) throws IllegalArgumentException
    {
        setModelidentity(activityTitle);
        this.sDate = sDate;
        this.eDate = eDate;
        
        if(eDate.compareTo(sDate) < 0)
        	throw new IllegalArgumentException("The end date is before the start date.");
    }

    public ActivityModel(String activityTitle,String reason, LocalDate sDate, LocalDate eDate, String userId)
    {
        this.reason = reason;
        setModelidentity(activityTitle);;
        this.sDate = sDate;
        this.eDate = eDate;
        
        
        if(eDate.compareTo(sDate) < 0)
        	throw new IllegalArgumentException("The end date is before the start date.");
        
        assignUser(userId);
        
        Type = ActivityType.Absent_Related;
    }
    
    public String activityName()
    {
    	return modelId();
    }
    
    public int estimatedHours()
    {
    	return estimatedWorkHours;
    }
    
    public void setEstimatedHours(int hours)
    {
    	estimatedWorkHours = hours;
    }
    
    public String Reason()
    {
    	return reason;
    }
    
    public void setReason(String reason)
    {
    	this.reason = reason;
    }

    public ActivityType TypeOfActivity()
    {
    	return Type;
    }

    public LocalDate startDate()
    {
    	return sDate;
    }
    
    public void setStartDate(LocalDate date)
    {
    	sDate = date;
    }
    
    public LocalDate endDate()
    {
    	return eDate;
    }
    
    public void setEndDate(LocalDate date)
    {
    	eDate = date;
    }
    
    public int startWeek()
    {
    	TemporalField tempField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
    	return sDate.get(tempField);
    }

    public int endWeek()
    {
    	TemporalField tempField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
    	return eDate.get(tempField);
    }

    public int EstimatedDuration()
    {
    	return endWeek() - startWeek();
    }

    public void assignUser(String id)
    {
    	assignedUserIdentities.add(id);
    }

    public void AssignUsers(List<String> userIds)
    {
        for(String userId : userIds)
            assignedUserIdentities.add(userId);
    }

    public boolean isUserAssigned(UserManager uManager)
    {
        String currentUserName = uManager.currentUser().UserName();
        return assignedUserIdentities.stream().anyMatch(item -> item.equals(currentUserName));
    }
    
    public boolean isUserAssignedToActivity(String user)
    {
    	return assignedUserIdentities.contains(user);
    }

    public List<String> AssignedUsers()
    {
    	return assignedUserIdentities;
    }
    
    public void ClearAssignedUserIdentities()
    {
    	assignedUserIdentities.clear();;
    }
    
    public boolean isUserAssigned(String userName)
    {
        for(String uName : assignedUserIdentities)
        {
        	if(uName.equals(userName))
        		return true;
        }
        return false;
    }
    
    public void addRegistrationModel(HourRegistrationModel model)
    {
    	addSubModel(model);
    }
    
    public void removeRegistrationModel(String id)
    {
    	removeSubModel(id);
    }
    
    public HourRegistrationModel hourRegistrationModel(String id)
    {
    	return (HourRegistrationModel) subModel(id);
    }
    
    public List<HourRegistrationModel> hourRegistrationModels()
    {
    	return subModels();
    }
    
    public List<HourRegistrationModel> HourRegistrationObjects(String userName)
    {
    	List<HourRegistrationModel> models = subModels();
    	return models.stream().filter(item -> item.userId().equals(userName)).collect(Collectors.toList());
    }
    
    public double totalRegisteredHours()
    {
        double totalHours = 0;
        List<HourRegistrationModel> models = subModels();
        for(HourRegistrationModel hourModel : models)
    		totalHours += hourModel.hours();

        return totalHours;
    }

    public double totalRegisteredHours(String userName)
    {
        double totalHours = 0;
        List<HourRegistrationModel> models = subModels();
        for(HourRegistrationModel hourModel : models)
        {
            if (hourModel.userId().equals(userName))
                totalHours += hourModel.hours();
        }

        return totalHours;
    }
    
    public ItemModel itemModel()
    {
		return new ItemModel(ItemModelData());
    }
    
    public ItemModel itemModel(int[] properties)
    {
    	String[] propertyStrings = new String[properties.length];
    	int index = 0;
    	
    	for (int i = 0; i < properties.length; i++) {
			int propertyIndex = properties[i];
			if(propertyIndex >= 0  && propertyIndex < ItemModelData().length)
				propertyStrings[index++] = ItemModelDataAt(propertyIndex);
		}
    	
    	return new ItemModel(propertyStrings);
    }
    
    public List<ItemModel> HourRegistrationItemModels()
    {
    	return allSubItemModels();
    }
    
    private String ItemModelDataAt(int index)
    {
    	return ItemModelData()[index];
    }
    
    private String[] ItemModelData()
    {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	
    	String[] itemData = new String[6];
		
		itemData[0] = modelId();
		itemData[1] = sDate.format(formatter);
		itemData[2] = eDate.format(formatter);
		itemData[3] = Integer.toString(estimatedWorkHours);
		itemData[4] = Double.toString(totalRegisteredHours());
		itemData[5] = parentModelId();
		
		return itemData;
    }
}
