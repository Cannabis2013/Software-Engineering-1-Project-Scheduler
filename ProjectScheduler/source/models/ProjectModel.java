package models;

import java.util.Calendar;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Abstractions.AbstractModel;
import formComponents.ItemModel;

public class ProjectModel extends AbstractModel
{
	private static final long serialVersionUID = 1L;
	private static int id = 1000;
	private String serialId , pLeaderId;
    private Date sDate, eDate;
    
    public ProjectModel(String name, String projectLeaderId, Date startDate, Date endDate)
    {
    	setModelidentity(name);
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(startDate);
    	int year = cal.get(Calendar.YEAR);
    	
    	StringBuilder serial = new StringBuilder();
    	serial.append(Integer.toString(year));
    	serial.append(Integer.toString(id++));
    	
    	serialId = serial.toString();
    }
    
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

    public List<ItemModel> activityItemModels()
    {
    	List<ItemModel> models = new ArrayList<ItemModel>();
        for (AbstractModel activity : subModels())
            models.add(((ActivityModel) activity).itemModel());

        return models;
    }
    
    public List<ActivityModel> Activities(String userName)
    {
    	List<ActivityModel> result = new ArrayList<ActivityModel>();
    	for(AbstractModel model : subModels())
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
