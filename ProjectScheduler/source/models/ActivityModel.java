package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Abstractions.AbstractModel;
import UserDomain.UserManager;
import formComponents.ItemModel;

public class ActivityModel extends AbstractModel {
	
	private static final long serialVersionUID = 1L;
	private List<String> assignedUserIdentities = new ArrayList<String>();
    private Date sDate, eDate;
    private String reason;
    private ActivityType Type = ActivityType.Work_Related;

    public enum ActivityType
    {
        Work_Related,
        Absent_Related
    };
    
    public ActivityModel(String activityTitle, 
        	AbstractModel parentModel,
            Date sDate, 
            Date eDate,
            List<String> assignedUsers,
            String description
            )
        {
            setModelidentity(activityTitle);
            this.sDate = sDate;
            this.eDate = eDate;
            
            if(assignedUsers != null)
            	assignedUserIdentities = assignedUsers;            
            
            setParent(parentModel);
            setDescription(description);
            
            assignSerialId();
        }
    
    public ActivityModel(String activityTitle, 
    	AbstractModel parentModel,
        Date sDate, 
        Date eDate
        )
    {
        setModelidentity(activityTitle);
        this.sDate = sDate;
        this.eDate = eDate;
        
        setParent(parentModel);
        
        assignSerialId();
    }

    public ActivityModel(String activityTitle,String reason, Date sDate, Date eDate)
    {
        this.reason = reason;
        setModelidentity(activityTitle);;
        this.sDate = sDate;
        this.eDate = eDate;

        Type = ActivityType.Absent_Related;
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

    public Date startDate()
    {
    	return sDate;
    }
    
    public void setStartDate(Date date)
    {
    	sDate = date;
    }
    
    public Date endDate()
    {
    	return eDate;
    }
    
    public void setEndDate(Date date)
    {
    	eDate = date;
    }
    

    public int startWeek()
    {
    	return 0;
    }

    public int endWeek()
    {
    	return 0;
    }

    public int EstimatedDuration()
    {
    	return endWeek() - startWeek();
    }

    public void assignUser(String userID)
    {
    	assignedUserIdentities.add(userID);
    }

    public void AssignUsers(List<String> userIDs)
    {
        for(String userId : userIDs)
            assignedUserIdentities.add(userId);
    }

    public boolean IsUserAssigned(UserManager uManager)
    {
        String currentUserName = uManager.currentUser().modelIdentity();
        return assignedUserIdentities.stream().anyMatch(item -> item.equals(currentUserName));
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

    public List<String> AssignedUsers()
    {
        return assignedUserIdentities;
    }

    public void ClearAssignedUserIdentities()
    {
        assignedUserIdentities.clear();;
    }
    
    public List<HourRegistrationModel> HourRegistrationObjects()
    {
    	List<HourRegistrationModel> result = new ArrayList<HourRegistrationModel>();
    	for(AbstractModel model : subModels())
    	{
    		HourRegistrationModel hourModel = (HourRegistrationModel) model;
    		result.add(hourModel);
    	}
    	return result;
    }
    
    public List<HourRegistrationModel> HourRegistrationObjects(String userName)
    {
    	List<HourRegistrationModel> result = new ArrayList<HourRegistrationModel>();
    	for(AbstractModel model : subModels())
    	{
    		HourRegistrationModel hourModel = (HourRegistrationModel) model;
    		if(hourModel.userName().equals(userName))
    			result.add(hourModel);
    	}
    	return result;
    }
    
    public int TotalRegisteredHours()
    {
        int totalHours = 0;
        for(AbstractModel model : subModels())
        {
    		HourRegistrationModel hourModel = (HourRegistrationModel) model;
    		totalHours += hourModel.Hours();
        }

        return totalHours;
    }

    public int TotalRegisteredHours(String userName)
    {
        int totalHours = 0;
        
        for(AbstractModel model : subModels())
        {
    		HourRegistrationModel hourModel = (HourRegistrationModel) model;
            if (hourModel.userName().equals(userName))
                totalHours += hourModel.Hours();
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
    
    private String ItemModelDataAt(int index)
    {
    	return ItemModelData()[index];
    }
    
    private String[] ItemModelData()
    {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
    	
    	String[] itemData = new String[5];
		
		itemData[0] = modelIdentity();
		itemData[1] = dateFormat.format(sDate);
		itemData[2] = dateFormat.format(eDate);
		itemData[3] = Integer.toString(TotalRegisteredHours());
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
    	serialBuilder.append(modelIdentity());
    	int hashedId = 0;
    	if(Type == ActivityType.Work_Related)
    		 hashedId = parentModelIdentity().hashCode();
    	else
    		hashedId = reason.hashCode();
    	
    	serialBuilder.append(Integer.toString(hashedId));
    	
    	return serialBuilder.toString();
    }
}
