package models;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import Abstractions.Model;
import formComponents.ItemModel;
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
        	Model parentModel,
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
            
            setParent(parentModel);
            setDescription(description);
            
            assignSerialId();
        }
    
    public ActivityModel(String activityTitle, 
    	Model parentModel,
        LocalDate sDate, 
        LocalDate eDate
        ) throws IllegalArgumentException
    {
        setModelidentity(activityTitle);
        this.sDate = sDate;
        this.eDate = eDate;
        
        if(eDate.compareTo(sDate) < 0)
        	throw new IllegalArgumentException("The end date is before the start date.");
        
        setParent(parentModel);
        
        assignSerialId();
    }

    public ActivityModel(String activityTitle,String reason, LocalDate sDate, LocalDate eDate)
    {
        this.reason = reason;
        setModelidentity(activityTitle);;
        this.sDate = sDate;
        this.eDate = eDate;
        
        if(eDate.compareTo(sDate) < 0)
        	throw new IllegalArgumentException("The end date is before the start date.");

        Type = ActivityType.Absent_Related;
    }
    
    public int estimatedHours()
    {
    	return estimatedWorkHours;
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

    public boolean IsUserAssigned(UserManager uManager)
    {
        String currentUserName = uManager.currentUser().modelId();
        return assignedUserIdentities.stream().anyMatch(item -> item.equals(currentUserName));
    }

    public List<String> AssignedUsers()
    {
    	return assignedUserIdentities;
    }
    
    public void ClearAssignedUserIdentities()
    {
    	assignedUserIdentities.clear();;
    }
    
    public boolean IsUserAssigned(String userName)
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
    
    public int totalRegisteredHours()
    {
        int totalHours = 0;
        List<HourRegistrationModel> models = subModels();
        for(HourRegistrationModel hourModel : models)
    		totalHours += hourModel.hours();

        return totalHours;
    }

    public int totalRegisteredHours(String userName)
    {
        int totalHours = 0;
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
    	
    	String[] itemData = new String[5];
		
		itemData[0] = modelId();
		itemData[1] = sDate.format(formatter);
		itemData[2] = eDate.format(formatter);
		itemData[3] = Integer.toString(totalRegisteredHours());
		itemData[4] = serialId();
		
		return itemData;
    }
    
    public void assignSerialId()
    {
    	setSerialId(generateSerialId());
    }
    
    @Override
    protected String generateSerialId()
    {
    	StringBuilder serialBuilder = new StringBuilder();
    	serialBuilder.append(modelId());
    	int hashedId = 0;
    	if(Type == ActivityType.Work_Related)
    		 hashedId = parentModelId().hashCode();
    	else
    		hashedId = reason.hashCode();
    	
    	serialBuilder.append(Integer.toString(hashedId));
    	
    	return serialBuilder.toString();
    }
}
