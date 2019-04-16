package models;

import Abstractions.AbstractModel;

public class UserModel extends AbstractModel {

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
	
	public Object itemModel()
    {
		return null;
    }

}