package managers;

import java.util.List;
import java.util.stream.Collectors;

import abstractions.Manager;
import abstractions.AbstractModel;
import models.ItemModel;
import models.UserModel;
import models.UserModel.userRole;

public class UserManager extends Manager {

	private static final long serialVersionUID = -7803978115663211042L;
	private UserModel currentlyLoggedIn = null;
	public UserManager() {
		try {
			createUserPrototypes();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
        for (AbstractModel model : models())
        {
        	UserModel user = (UserModel) model;
        	if (user.UserName().equals(userName))
        		return user;
        }
        return null;
    }
	
	private UserModel verifyCredentials(String userName)
    {
		for(AbstractModel model : models())
		{
			UserModel user = (UserModel) model;
			if(user.UserName().equals(userName))
				return user;
		}
		
		return null;
    }
	
	public String[] listModelIdentities()
    {
		
        return allModelIdentities().stream().filter(item -> !item.equals("admin")).toArray(String[]::new);
        
    }
	
	public List<ItemModel> userItemModels()
    {
    	return models().stream().filter(subItem -> ((UserModel)subItem).Role() != userRole.Admin).
    			map(item -> item.itemModel()).collect(Collectors.toList());
    }
	
	private void createUserPrototypes() throws Exception
    {
		UserModel admin = new UserModel("admin", UserModel.userRole.Admin);

		addModel(admin);
        
        /*
         * Initialize ten dummy users for testing/demonstration purposes
         */
        UserModel nUser1 = new UserModel("JW", UserModel.userRole.Employee);
        UserModel nUser2 = new UserModel("NE", UserModel.userRole.Employee);
        UserModel nUser3 = new UserModel("BB", UserModel.userRole.Employee);
        UserModel nUser4 = new UserModel("FL", UserModel.userRole.Employee);
        UserModel nUser5 = new UserModel("TT", UserModel.userRole.Employee);
        UserModel nUser6 = new UserModel("HT", UserModel.userRole.Employee);
        UserModel nUser7 = new UserModel("PB", UserModel.userRole.Employee);
        UserModel nUser8 = new UserModel("MH", UserModel.userRole.Employee);
        UserModel nUser9 = new UserModel("HR", UserModel.userRole.Employee);
        UserModel nUser10 = new UserModel("RB", UserModel.userRole.Employee);
        
        addModel(nUser1);
        addModel(nUser2);
        addModel(nUser3);
        addModel(nUser4);
        addModel(nUser5);
        addModel(nUser6);
        addModel(nUser7);
        addModel(nUser8);
        addModel(nUser9);
        addModel(nUser10);
    }
}
