package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Abstractions.AbstractModel;
import UserDomain.UserManager;

public class ActivityModel extends AbstractModel {
	private List<String> assignedUserIdentities;
    private Date sDate, eDate;
    private String reason;
    private ActivityType Type = ActivityType.Work_Related;

    public enum ActivityType
    {
        Work_Related,
        Absent_Related
    };
    
    public ActivityModel(String activityTitle,AbstractModel parentProjectModel, 
        Date sDate, 
        Date eDate, 
        List<String> assignedUsers)
    {
        setModelidentity(activityTitle);
        sDate = sDate;
        eDate = eDate;
        assignedUserIdentities = assignedUsers;

        parentProjectModel.AddSubModel(this);
        setParent(parentProjectModel);
    }

    public ActivityModel(String activityTitle,String reason, Date sDate, Date eDate)
    {
        this.reason = reason;
        setModelidentity(activityTitle);;
        sDate = sDate;
        eDate = eDate;

        Type = ActivityType.Absent_Related;
    }

    public ActivityModel(ActivityModel copy)
    {
        setSubModels(copy.SubModels());
        setModelidentity(copy.modelIdentity());
        sDate = copy.startDate();
        eDate = copy.endDate();
        assignedUserIdentities = copy.assignedUserIdentities;
        setParent(copy.Parent());;
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
        for(String userName : assignedUserIdentities)
        {
        	if(userName.equals(currentUserName))
        		return true;
        }
        return false;
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
    	for(AbstractModel model : SubModels())
    	{
    		HourRegistrationModel hourModel = (HourRegistrationModel) model;
    		result.add(hourModel);
    	}
    	return result;
    }
    
    public List<HourRegistrationModel> HourRegistrationObjects(String userName)
    {
    	List<HourRegistrationModel> result = new ArrayList<HourRegistrationModel>();
    	for(AbstractModel model : SubModels())
    	{
    		HourRegistrationModel hourModel = (HourRegistrationModel) model;
    		if(hourModel.userName.equals(userName))
    			result.add(hourModel);
    	}
    	return result;
    }

    public int TotalRegisteredHours(String userName)
    {
        int totalHours = 0;
        if (userName != null)
        {
        	for(AbstractModel model : SubModels())
            {
        		HourRegistrationModel hourModel = (HourRegistrationModel) model;
                if (hourModel.userName.equals(userName))
                    totalHours += hourModel.Hours();
            }
        }
        else
        {
        	for(AbstractModel model : SubModels())
            {
        		HourRegistrationModel hourModel = (HourRegistrationModel) model;
        		totalHours += hourModel.Hours();
            }
        }

        return totalHours;
    }
    
    public Object itemModel()
    {
		return null;
    }
}
