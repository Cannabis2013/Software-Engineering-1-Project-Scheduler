/*
 * Martin Hansen
 */


package entities;

import java.time.LocalDate;

import models.ActivityModel;

public class ActivityEntity {
	
	private String title;
    private LocalDate sDate, eDate;
    private ActivityModel.ActivityType aType;

    public ActivityEntity(String title, LocalDate startDate, LocalDate endDate,ActivityModel.ActivityType type)
    {
        this.title = title;
        this.sDate = startDate;
        this.eDate = endDate;
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
    
    public LocalDate StartDate()
    {
    	return sDate;
    }

    public LocalDate EndDate()
    {
    	return eDate;
    }

    public boolean strictlyWithinTimespan(LocalDate date)
    {
        return sDate.compareTo(date) < 0 && eDate.compareTo(date) > 0;
    }
    
    public boolean withinTimespan(LocalDate date)
    {
        return sDate.compareTo(date) <= 0 && eDate.compareTo(date) >= 0;
    }

}
