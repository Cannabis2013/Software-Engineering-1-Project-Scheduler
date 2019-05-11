package models;

import java.util.Calendar;

import abstractions.AbstractModel;

public class UserModel extends AbstractModel {

	private static final long serialVersionUID = 1L;
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
        setModelidentity(userName);
        this.role = role;
    }
	
	public userRole Role()
	{
		return role;
	}
	
	public String UserName() {
		return modelId();
	}
	
	public static String _roleStringRepresentation(userRole r)
    {
        return r == userRole.Admin ? "Administrator" : "Employee";
    }
	
	public ItemModel itemModel()
    {
		String[] itemData = new String[2];
		
		itemData[0] = modelId();
		itemData[1] = _roleStringRepresentation(role);
		
		return new ItemModel(itemData);
    }
}
