package UserDomain;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

	private List<User> users = new ArrayList<User>();
	private User currentlyLoggedIn = null;
	public UserManager() {
		createUserPrototypes();
	}
	
	public Boolean logIn(String userName)
    {
        User user = verifyCredentials(userName);
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
        return currentlyLoggedIn.Role() == User.userRole.Admin;
    }

    public User currentUser()
    {
        return currentlyLoggedIn;
    }

    public User user(String userName)
    {
        for (User u : users)
        {        	
        	if (u.UserName().equals(userName))
        		return u;
        }
        return null;
    }

    public List<String> allUserNames()
    {
        List<String> result = new ArrayList<String>();
        for (User u : users)
            result.add(u.UserName());

        return result;
    }
	
	
	private User verifyCredentials(String userName)
    {
		for(User u : users)
		{
			if(u.UserName().equals(userName))
				return u;
		}
		
		return null;
    }
	
	private void createUserPrototypes()
    {
		User admin = new User("admin", User.userRole.Admin);

        users.add(admin);

        /*
         * Initialize five users for testing purposes
         */
        User nUser1 = new User("Jens_Werner2019", User.userRole.Employee);
        User nUser2 = new User("Niels_Erik1964", User.userRole.Employee);
        User nUser3 = new User("Bent_Bjerre", User.userRole.Employee);
        User nUser4 = new User("Finn_Luger", User.userRole.Employee);
        User nUser5 = new User("Technotonny", User.userRole.Employee);
        
        users.add(nUser1);
        users.add(nUser2);
        users.add(nUser3);
        users.add(nUser4);
        users.add(nUser5);
    }

}
