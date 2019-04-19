package models;

import java.util.Calendar;

import Abstractions.AbstractModel;
import formComponents.ItemModel;

public class UserModel extends AbstractModel {

	private static final long serialVersionUID = 1L;
	private String userName;
	private userRole role = userRole.Employee;
	
	public enum userRole {Admin, Employee};
	
	public enum Availability
    {
        NotAvailable,
        PartlyAvailable,
        Available
    }
	
	
	public UserModel(String userName, userRole role)
    {
        this.userName = userName;
        this.role = role;
    }
	
	public userRole Role()
	{
		return role;
	}
	
	public String UserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public static String _roleStringRepresentation(userRole r)
    {
        return r == userRole.Admin ? "Administrator" : "Employee";
    }
	
	public ItemModel itemModel()
    {
		String[] itemData = new String[2];
		
		itemData[0] = modelIdentity();
		itemData[1] = _roleStringRepresentation(role);
		
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
