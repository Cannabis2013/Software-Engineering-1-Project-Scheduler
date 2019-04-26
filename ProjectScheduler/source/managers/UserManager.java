package managers;

import java.util.ArrayList;
import java.util.List;

import Abstractions.AbstractManager;
import Abstractions.Model;
import formComponents.ItemModel;
import models.UserModel;
import models.UserModel.userRole;

public class UserManager extends AbstractManager {

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
	
	public List<String> listModelIdentities()
    {
        List<String> result = new ArrayList<String>();
        for (Model u : models())
            result.add(u.modelId());

        return result;
    }
	
	public ItemModel[] itemModels(Boolean fullList)
    {
        if(fullList)
        	return models().stream().map(item -> item.itemModel()).toArray(ItemModel[]::new);
        else
        	return models().stream().filter(subItem -> ((UserModel) subItem).Role() != userRole.Admin).
        			map(item -> item.itemModel()).toArray(ItemModel[]::new);
    }
	
	private void createUserPrototypes()
    {
		UserModel admin = new UserModel("admin", UserModel.userRole.Admin);

        users.add(admin);
        
        /*
         * Initialize five users for testing purposes
         */
        UserModel nUser1 = new UserModel("JW", UserModel.userRole.Employee);
        UserModel nUser2 = new UserModel("NE", UserModel.userRole.Employee);
        UserModel nUser3 = new UserModel("BB", UserModel.userRole.Employee);
        UserModel nUser4 = new UserModel("FL", UserModel.userRole.Employee);
        UserModel nUser5 = new UserModel("TT", UserModel.userRole.Employee);
        
        users.add(nUser1);
        users.add(nUser2);
        users.add(nUser3);
        users.add(nUser4);
        users.add(nUser5);
    }

	@Override
	public void requestUpdate() {
		// TODO Auto-generated method stub
		
	}

}