package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

import abstractions.Model;
import managers.ProjectManager;

public class HourRegistrationModel extends Model {
	
	private static final long serialVersionUID = 1L;
	private LocalDate originalRegistrationDate;
    private int workHours;
    private String userId;


    public HourRegistrationModel(String identity,int hours, String userId, String shortDescription)
    {
        setModelidentity(identity);;
        this.workHours = hours;
        this.userId = userId;
        setDescription(shortDescription);

        
        originalRegistrationDate = LocalDate.now();
        setSerialId(generateSerialId());
    }

    public int hours()
    {
        return workHours;
    }
    
    public void setHours(int hours)
    {
    	workHours = hours;
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

    public String parentProjectId(ProjectManager pManager)
    {
    	return root(this).modelId();
    }
    
    public ItemModel itemModel()
    {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	
    	String[] itemData = new String[6];
		
		itemData[0] = modelId();
		itemData[1] = userId();
		itemData[2] = Integer.toString(hours());
		itemData[3] = originalRegistrationDate.format(formatter);
		itemData[4] = parentModelId();
		itemData[5] = serialId();
		
		return new ItemModel(itemData);
    }
    
    @Override
    protected String generateSerialId()
    {
    	StringBuilder serialBuilder = new StringBuilder();
    	serialBuilder.append(modelId());
    	int hashedId = hashCode();
    	
    	serialBuilder.append(Integer.toString(hashedId));
    	
    	return serialBuilder.toString();
    }
}
