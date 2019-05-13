package abstractions;

import java.time.LocalDate;
import java.util.List;

import models.ActivityModel;
import models.ProjectModel;
import models.UserModel;
import models.HourRegistrationModel;
import models.ItemModel;

public interface IApplicationProgrammingInterface {
	// User section
    void login(String userName) throws Exception;
    void logut();

    Boolean isAdmin();
    
    UserModel currentUserLoggedIn();

    String[] allUserNames();

    List<ItemModel> userListModels();

    String userAvailability(String username, 
        LocalDate sDate, LocalDate eDate);

    List<ItemModel> allUserAvailability(LocalDate sDate, LocalDate eDate);
    
    // Project section

    void addProject(ProjectModel newProject) throws Exception;
    void removeProject(String identity) throws Exception;

    ProjectModel project(String title) throws NullPointerException, Exception;

    List<ItemModel> projectItemModels();
    
    boolean isUserProjectLeader();

    // Activities
    
    void addAbsenceActivity(ActivityModel activity) throws Exception;
    void addActivity(String projectTitle, ActivityModel activity) throws Exception;
    void removeAbsenceActivity(String id) throws Exception;
    
    void removeActivity(String projectid, String activityId) throws Exception;

    ActivityModel activity(String projectId, String activityId) throws Exception;

    List<ActivityModel> activities();
    List<ActivityModel> activities(String userName);

    List<ItemModel> activityItemModels();
    List<ItemModel> activityItemModels(String userName);
	List<ItemModel> projectActivityItemModels(String projectId) throws Exception;

    // Hour registrations

    void registerHour(String projectId, String activityId,
        String regId, double hours, String shortDescription) throws Exception;

    void unRegisterHour(String projectId, String activityId, String regId) throws Exception;

    HourRegistrationModel hourRegistrationModel(String projectId,String activityId, String regId) throws Exception;
    List<ItemModel> hourRegistrationItemModels();
    List<ItemModel> hourRegistrationItemModels(String userNames);

    // Observer
    
    void requestUpdate();
    void subScribe(ICustomObserver observer);
    void unSubScribe(ICustomObserver observer);
    void unSubScribeAll();
}
