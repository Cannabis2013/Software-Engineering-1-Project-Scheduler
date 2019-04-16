package models;




import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Abstractions.AbstractModel;

public class ProjectModel extends AbstractModel
{
	private String pLeaderId, description;
    private Date sDate, eDate;
    
    public Date startDate()
    {
    	return sDate;
    }
    
    public Date endDate()
    {
    	return eDate;
    }
    
    public void setStartDate(Date date)
    {
    	sDate = date;
    }
    
    public void setEndDate(Date date)
    {
    	eDate = date;
    }
    

    public String projectLeaderId()
    {
    	return pLeaderId;
    }
    
    public void setProjectLeaderId(string id)
    {
    	pLeaderId = id;
    }

    public String shortDescription()
    {
    	return description;
    }
    
    public void setShortDescription(String description)
    {
    	this.description = description;
    }

    
    public List<ActivityModel> Activities(String userName)
    {
    	List<ActivityModel> result = new ArrayList<ActivityModel>();
    	for(AbstractModel model : SubModels())
    	{
    		
    	}
        var userActivities = AllSubModels<ActivityModel>().Where(item => item.IsUserAssigned(userName));
        return userActivities.Select(item => new ActivityModel(item)).ToList();
    }

    public override ListViewItem ItemModel()
    {
        var model = new ListViewItem(ModelIdentity);
        
        model.SubItems.Add(projectLeaderId);

        model.SubItems.Add(StartDate.ToString("dd/MM/yyyy"));
        model.SubItems.Add(EndDate.ToString("dd/MM/yyyy"));

        model.ImageIndex = 0;
        model.StateImageIndex = 0;

        return model;
    }
	
}
