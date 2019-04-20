package models;

import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private String pLeaderId;
    private Date sDate, eDate;
    
    public ProjectModel(String name, String projectLeaderId, Date startDate, Date endDate, String description)
    {	
    	setModelidentity(name);
    	setProjectLeaderId(projectLeaderId);
    	sDate = startDate;
    	eDate = endDate;
    	setDescription(description);
    	setSerialId(generateSerialId());
    }
    
    public ProjectModel(String name, String projectLeaderId, String startDate, String endDate, String description) throws ParseException
    {
    	SimpleDateFormat simpleDate = new SimpleDateFormat("dd-mm-yyyy");
    	
    	try {
			sDate = simpleDate.parse(startDate);
			eDate = simpleDate.parse(endDate);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Something went wrong with date conversion.");
		}
    	
    	if(eDate.compareTo(sDate) < 0)
    		throw new IllegalArgumentException("The end date is before the start date.");
    	
    	setModelidentity(name);
    	setSerialId(generateSerialId());
    	setProjectLeaderId(projectLeaderId);
    	setDescription(description);
    }
    
    public String projectName()
    {
    	return modelIdentity();
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
		String[] itemData = new String[5];
		
		itemData[0] = modelIdentity();
		itemData[1] = pLeaderId;
		itemData[2] = sDate.toString();
		itemData[3] = eDate.toString();
		itemData[4] = serialId();
		
		return new ItemModel(itemData);
    }

	@Override
	protected String generateSerialId() {
		
		if(id > 9999)
    		id = 1000;
    	
    	int year = ProjectManager.getDateProperty(sDate, Calendar.YEAR);
    	
    	StringBuilder serial = new StringBuilder();
    	serial.append(Integer.toString(year));
    	serial.append(Integer.toString(id++));
    	
    	return serial.toString();
	}
}
