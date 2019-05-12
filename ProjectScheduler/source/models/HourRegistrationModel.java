package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

import abstractions.AbstractModel;

public class HourRegistrationModel extends AbstractModel {
	
	private static final long serialVersionUID = 1L;
	private LocalDate originalRegistrationDate;
    private double workHours;
    private String userId;


    public HourRegistrationModel(String identity,double hours, String userId, String shortDescription)
    {
        setModelidentity(identity);
        this.workHours = hours;
        this.userId = userId;
        setDescription(shortDescription);
        
        originalRegistrationDate = LocalDate.now();
    }
    
    public String registrationId()
    {
    	return modelId();
    }
    
    public void setRegistrationId(String id)
    {
    	setModelidentity(id);
    	
    }

    public double hours()
    {
        return workHours;
    }
    
    public void setHours(double hours)
    {
    	workHours = hours;
    	StateChanged();
    }
    
    public String userId()
    {
    	return userId;
    }
    
    public LocalDate registrationDate()
    {
    	return originalRegistrationDate;
    }
    
    public int registrationWeekOfTheYear()
    {
    	TemporalField tempField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
    	return originalRegistrationDate.get(tempField);
    }

    public String parentProjectName()
    {
    	return ((ProjectModel) root(this)).projectName();
    }
    
    public ItemModel itemModel()
    {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	
    	String[] itemData = new String[6];
		
		itemData[0] = modelId();
		itemData[1] = userId();
		itemData[2] = Double.toString(hours());
		itemData[3] = originalRegistrationDate.format(formatter);
		itemData[4] = parentModelId();
		itemData[5] = parentProjectName();
		
		return new ItemModel(itemData);
    }   
}
