package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import abstractions.Model;
import formComponents.ItemModel;

public class ProjectModel extends Model
{
	private static final long serialVersionUID = 1L;
	private static int id = 1000;
	private String projectLeaderId;
    private LocalDate sDate, eDate;
    
    public ProjectModel(String name, String projectLeaderId, LocalDate startDate, LocalDate endDate, String description) throws IllegalArgumentException
    {	
    	setModelidentity(name);
    	setProjectLeaderId(projectLeaderId);
    	sDate = startDate;
    	eDate = endDate;
    	
    	if(eDate.compareTo(sDate) < 0)
    		throw new IllegalArgumentException("The end date is before the start date.");
    	
    	setDescription(description);
    	setSerialId(generateSerialId());
    }
    
    public ProjectModel(String name, String projectLeaderId, String startDate, String endDate, String description) 
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
    	
    	setModelidentity(name);
    	setSerialId(generateSerialId());
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
    
    public ActivityModel activity(String id)
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
		
		itemData[0] = modelId();
		itemData[1] = projectLeaderId;
		itemData[2] = sDate.format(formatter);
		itemData[3] = eDate.format(formatter);
		itemData[4] = serialId();
		
		return new ItemModel(itemData);
    }

	@Override
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
