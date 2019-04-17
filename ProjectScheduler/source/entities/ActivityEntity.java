package entities;

import java.util.Date;

import models.ActivityModel;

public class ActivityEntity {
	
	private String title;
    private Date sDate, eDate;
    private ActivityModel.ActivityType aType;

    public ActivityEntity(String title, Date endDate, Date startDate, ActivityModel.ActivityType type)
    {
        this.title = title;
        this.eDate = endDate;
        this.sDate = startDate;
        this.aType = type;
    }

    public ActivityModel.ActivityType TypeOfActivity()
    {
    	return aType;
    }

    public String activityId()
    {
    	return title;
    }
    
    public Date StartDate()
    {
    	return sDate;
    }

    public Date EndDate()
    {
    	return eDate;
    }

    public boolean withinTimespan(Date date)
    {
        return sDate.compareTo(date) <= 0 && eDate.compareTo(date) >= 0;
    }

}
