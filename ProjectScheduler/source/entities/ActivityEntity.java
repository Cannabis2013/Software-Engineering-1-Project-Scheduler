package entities;

import java.time.LocalDate;

import models.ActivityModel;

public class ActivityEntity {
	
	private String title;
    private LocalDate sDate, eDate;
    private ActivityModel.ActivityType aType;

    public ActivityEntity(String title, LocalDate endDate, LocalDate startDate, ActivityModel.ActivityType type)
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
    
    public LocalDate StartDate()
    {
    	return sDate;
    }

    public LocalDate EndDate()
    {
    	return eDate;
    }

    public boolean withinTimespan(LocalDate date)
    {
        return sDate.compareTo(date) <= 0 && eDate.compareTo(date) >= 0;
    }

}
