package models;




import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibm.icu.util.Calendar;

import Abstractions.AbstractModel;
import formComponents.ItemModel;

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
    
    public void setProjectLeaderId(String id)
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
    		ActivityModel activity = (ActivityModel) model;
    		if(activity.IsUserAssigned(userName))
    			result.add(activity);
    	}
        
    	return result;
    }
    
    public ItemModel itemModel()
    {
		String[] itemData = new String[4];
		
		itemData[0] = modelIdentity();
		itemData[1] = pLeaderId;
		itemData[2] = sDate.toString();
		itemData[3] = eDate.toString();
		
		return new ItemModel(itemData);
    }
}
