package abstractions;

import java.time.LocalDate;
import java.util.List;

import formComponents.ItemModel;
import models.ActivityModel;
import models.ProjectModel;
import models.UserModel;
import models.HourRegistrationModel;

public interface IApplicationProgrammingInterface {
	// User section
    void login(String userName) throws Exception;
    void logut();

    Boolean isAdmin();

    UserModel currentUserLoggedIn();

    List<String> userNames();

    ItemModel[] userListModels(Boolean IncludeAdmin);

    String userAvailability(String username, 
        LocalDate sDate, LocalDate eDate);

    // General model section
    
    Model modelBySerial(String serialId);
    
    // Project section

    void addProject(ProjectModel newProject) throws Exception;
    void removeProject(int index) throws Exception;
    void removeProject(String identity) throws Exception;
    void removeProject(ProjectModel project) throws Exception;

    ProjectModel project(int index);
    ProjectModel project(String title);

    ItemModel[] projectItemModels();
    ItemModel[] projectItemModels(String UserIdentity);

    // Activities
    
    void addAbsenceActivity(ActivityModel activtity);
    void addActivity(String projectTitle, ActivityModel activity) throws Exception;
    void removeAbsenceActivity(String id);
    
    void removeActivity(String projectid, String activityId) throws Exception;

    ActivityModel activity(String projectId, String activityId) throws Exception;

    List<ActivityModel> activitiesById(String activityId);
    List<ActivityModel> activities();
    List<ActivityModel> activities(String userName);

    ItemModel[] activityItemModels();
    ItemModel[] activityItemModels(String userName);

    // Hour registrations

    void registerHour(String projectId, String activityId,
        String regId, int hours, String shortDescription) throws Exception;

    void unRegisterHour(String projectId, String activityId, String regId) throws Exception;

    HourRegistrationModel hourRegistrationModel(String activityId, String regId) throws Exception;
    ItemModel[] hourRegistrationItemModels();
    ItemModel[] hourRegistrationItemModels(String userNames);

    // Observer

    void subScribe(ICustomObserver observer);
    void unSubScribe(ICustomObserver observer);
    void unSubScribeAll();
}
