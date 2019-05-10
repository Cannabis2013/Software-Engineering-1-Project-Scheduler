package managers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import abstractions.AbstractManager;
import abstractions.Model;
import models.ItemModel;
import models.UserModel;
import models.UserModel.userRole;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UserManager extends AbstractManager {

	private List<UserModel> users = new ArrayList<UserModel>();
	private UserModel currentlyLoggedIn = null;
	public UserManager() {
		createUserPrototypes();
	}
	
	public Boolean logIn(String userName) throws Exception
    {
        UserModel user = verifyCredentials(userName);
        if (user == null)
            throw new Exception("Wrong userName");

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
	
	private UserModel verifyCredentials(String userName)
    {
		for(UserModel u : users)
		{
			if(u.UserName().equals(userName))
				return u;
		}
		
		return null;
    }
	
	public String[] listModelIdentities()
    {
		String[] result = new String[users.size() - 1];
		int index = 0;
        for (int i = 0; i < users.size(); i++) {
			String userName = users.get(i).UserName();
			if(!userName.equals("admin"))
				result[index++] = userName;
		}

        return result;
    }
	
	public List<ItemModel> userItemModels()
    {
    	return users.stream().filter(subItem -> subItem.Role() != userRole.Admin).
    			map(item -> item.itemModel()).collect(Collectors.toList());
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
        UserModel nUser6 = new UserModel("HT", UserModel.userRole.Employee);
        UserModel nUser7 = new UserModel("PB", UserModel.userRole.Employee);
        UserModel nUser8 = new UserModel("MH", UserModel.userRole.Employee);
        
        users.add(nUser1);
        users.add(nUser2);
        users.add(nUser3);
        users.add(nUser4);
        users.add(nUser5);
        users.add(nUser6);
        users.add(nUser7);
        users.add(nUser8);
    }

	@Override
	public void requestUpdate() {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

}
