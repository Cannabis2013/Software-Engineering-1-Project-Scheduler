package models;

import java.awt.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import Abstractions.AbstractModel;
import ProjectDomain.ProjectManager;

public class HourRegistrationModel extends AbstractModel {
	private LocalDate originalRegistrationDate;
    private String activityTextContent;
    private int workHours;
    
    public String userName;


    public HourRegistrationModel(String identity,int hours, String userName, String text, AbstractModel ParentActivity)
    {
        setModelidentity(identity);;
        this.workHours = hours;
        this.userName = userName;
        this.activityTextContent = text;

        ParentActivity.AddSubModel(this);
        
        originalRegistrationDate = LocalDate.now();
        
    }


    public int Hours()
    {
        return workHours;
    }
    
    public void setHours(int h)
    {
    	workHours = h;
    }

    public LocalDate registrationDate()
    {
    	return originalRegistrationDate;
    }
    
    public String ShortDescription()
    {
    	return activityTextContent;
    }
    
    public void setShortDescription(String description)
    {
    	activityTextContent = description;
    }

    public String CorrespondingProjectId(ProjectManager pManager)
    {
        return pManager.model(parentModelIdentity()).parentModelIdentity();
    }
    
    public Object itemModel()
    {
		return null;
    }
}
