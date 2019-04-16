package UserDomain;

import java.util.ArrayList;
import java.util.List;

import models.UserModel;

public class UserManager {

	private List<UserModel> users = new ArrayList<UserModel>();
	private UserModel currentlyLoggedIn = null;
	public UserManager() {
		createUserPrototypes();
	}
	
	public Boolean logIn(String userName)
    {
        UserModel user = verifyCredentials(userName);
        if (user == null)
            return false;

        currentlyLoggedIn = user;

        return true;
    }

    public void logout()
    {
        currentlyLoggedIn = null;
    }

    public boolean isAdmin()
    {
        return currentlyLoggedIn.Role() == UserModel.userRole.Admin;
    }

    public UserModel currentUser()
    {
        return currentlyLoggedIn;
    }

    public UserModel user(String userName)
    {
        for (UserModel u : users)
        {        	
        	if (u.UserName().equals(userName))
        		return u;
        }
        return null;
    }

    public List<String> allUserNames()
    {
        List<String> result = new ArrayList<String>();
        for (UserModel u : users)
            result.add(u.UserName());

        return result;
    }
	
	
	private UserModel verifyCredentials(String userName)
    {
		for(UserModel u : users)
		{
			if(u.UserName().equals(userName))
				return u;
		}
		
		return null;
    }
	
	private void createUserPrototypes()
    {
		UserModel admin = new UserModel("admin", UserModel.userRole.Admin);

        users.add(admin);

        /*
         * Initialize five users for testing purposes
         */
        UserModel nUser1 = new UserModel("Jens_Werner2019", UserModel.userRole.Employee);
        UserModel nUser2 = new UserModel("Niels_Erik1964", UserModel.userRole.Employee);
        UserModel nUser3 = new UserModel("Bent_Bjerre", UserModel.userRole.Employee);
        UserModel nUser4 = new UserModel("Finn_Luger", UserModel.userRole.Employee);
        UserModel nUser5 = new UserModel("Technotonny", UserModel.userRole.Employee);
        
        users.add(nUser1);
        users.add(nUser2);
        users.add(nUser3);
        users.add(nUser4);
        users.add(nUser5);
    }

}
