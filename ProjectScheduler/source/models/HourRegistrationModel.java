package models;

import java.time.LocalDate;
import java.util.Calendar;

import Abstractions.AbstractModel;
import ProjectDomain.ProjectManager;
import formComponents.ItemModel;

public class HourRegistrationModel extends AbstractModel {
	
	private static final long serialVersionUID = 1L;
	private LocalDate originalRegistrationDate;
    private String activityTextContent;
    private int workHours;
    
    private String uName;


    public HourRegistrationModel(String identity,int hours, String userName, String text, AbstractModel ParentActivity)
    {
        setModelidentity(identity);;
        this.workHours = hours;
        this.uName = userName;
        this.activityTextContent = text;

        ParentActivity.addSubModel(this);
        
        originalRegistrationDate = LocalDate.now();
        setSerialId(generateSerialId());
    }

    public int Hours()
    {
        return workHours;
    }
    
    public void setHours(int h)
    {
    	workHours = h;
    }
    
    public String userName()
    {
    	return uName;
    }
    
    public void setUserName(String userName)
    {
    	uName = userName;
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
    
    public ItemModel itemModel()
    {
    	String[] itemData = new String[6];
		
		itemData[0] = modelIdentity();
		itemData[1] = userName();
		itemData[2] = Integer.toString(Hours());
		itemData[3] = originalRegistrationDate.toString();
		itemData[4] = parentModelIdentity();
		itemData[5] = serialId();
		
		return new ItemModel(itemData);
    }
    
    @Override
    protected String generateSerialId()
    {
    	StringBuilder serialBuilder = new StringBuilder();
    	serialBuilder.append(modelIdentity());
    	long currentTimeInMs = Calendar.getInstance().getTimeInMillis();
    	int hashedId = (int) (currentTimeInMs % (long) parentModelIdentity().hashCode());
    	
    	serialBuilder.append(Integer.toString(hashedId));
    	
    	return serialBuilder.toString();
    }
}
