package models;

import java.util.Calendar;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Abstractions.AbstractModel;
import ProjectDomain.ProjectManager;
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
    	
    	int year = ProjectManager.getDateProperty(startDate, Calendar.YEAR);
    	
    	
    	StringBuilder serial = new StringBuilder();
    	serial.append(Integer.toString(year));
    	serial.append(Integer.toString(id++));
    	
    	serialId = serial.toString();
    }
    
    public String serialIdentification()
    {
    	return serialId;
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
    	return subModels().stream().map(item -> item.itemModel()).collect(Collectors.toList());
    }
    
    public List<ActivityModel> Activities(String userName)
    {
    	Stream<ActivityModel> models = subModels().stream().map(item -> (ActivityModel) item);
    	return models.filter(item -> item.IsUserAssigned(userName)).collect(Collectors.toList());
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
