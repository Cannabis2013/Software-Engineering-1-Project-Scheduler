package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import abstractions.AbstractModel;

public class ProjectModel extends AbstractModel
{
	private static final long serialVersionUID = 1L;
	private static int id = 1;
	private String projectLeaderId;
    private LocalDate sDate, eDate;
    
    public ProjectModel(String projectLeaderId, LocalDate startDate, LocalDate endDate, String description) throws IllegalArgumentException
    {	
    	setProjectLeaderId(projectLeaderId);
    	sDate = startDate;
    	eDate = endDate;
    	
    	if(eDate.compareTo(sDate) < 0)
    		throw new IllegalArgumentException("The end date is before the start date.");
    	
    	setModelidentity(generateSerialId());
    	setDescription(description);
    }
    
    public ProjectModel(String projectLeaderId, String startDate, String endDate, String description) 
    		throws IllegalArgumentException
    {
    	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	try {
			sDate = LocalDate.parse(startDate,dateFormat);
			eDate = LocalDate.parse(endDate, dateFormat);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Something went wrong with date conversion.");
		}
    	
    	
    	if(eDate.compareTo(sDate) < 0)
    		throw new IllegalArgumentException("The end date is before the start date.");
    	
    	setModelidentity(generateSerialId());
    	setProjectLeaderId(projectLeaderId);
    	setDescription(description);
    }
    
    public String projectName()
    {
    	return modelId();
    }
    
    public LocalDate startDate()
    {
    	return sDate;
    }
    
    public LocalDate endDate()
    {
    	return eDate;
    }
    
    public void setStartDate(LocalDate date)
    {
    	sDate = date;
    }
    
    public void setEndDate(LocalDate date)
    {
    	eDate = date;
    }
    

    public String projectLeaderId()
    {
    	return projectLeaderId;
    }
    
    public void setProjectLeaderId(String id)
    {
    	projectLeaderId = id;
    }
    
    public void addActivity(ActivityModel activity)
    {
    	addSubModel(activity);
    }
    
    public void removeActivity(String id)
    {
    	removeSubModel(id);
    }
    
    public void removeActivity(ActivityModel activity)
    {
    	removeSubModel(activity);
    }
    
    public ActivityModel activity(String id) throws Exception
    {
    	return (ActivityModel) subModel(id);
    }

    public List<ActivityModel> Activities()
    {
    	return subModels();
    }
    
    public List<ActivityModel> Activities(String userName)
    {
    	Stream<ActivityModel> models = subModels().stream().map(item -> (ActivityModel) item);
    	return models.filter(item -> item.isUserAssigned(userName)).collect(Collectors.toList());
    }
    
    public void removeAllActivities()
    {
    	removeAllSubModels();
    }
    
    public List<ItemModel> activityItemModels()
    {
    	List<ActivityModel> aModels = subModels();
    	return aModels.stream().map(item -> item.itemModel()).collect(Collectors.toList());
    }
    
    public ItemModel itemModel()
    {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	
		String[] itemData = new String[5];
		
		itemData[0] = projectName();
		itemData[1] = projectLeaderId;
		itemData[2] = sDate.format(formatter);
		itemData[3] = eDate.format(formatter);
		
		return new ItemModel(itemData);
    }

	protected String generateSerialId() {
		
		if(id > 9999)
    		id = 1000;
    	
    	int year = sDate.getYear();
    	
    	StringBuilder serial = new StringBuilder();
    	serial.append(Integer.toString(year));
    	serial.append(Integer.toString(id++));
    	
    	return serial.toString();
	}
}
