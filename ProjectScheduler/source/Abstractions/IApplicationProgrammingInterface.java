package Abstractions;

import java.util.Date;
import java.util.List;

import formComponents.ItemModel;
import models.ActivityModel;
import models.ProjectModel;
import models.UserModel;
import models.HourRegistrationModel;

public interface IApplicationProgrammingInterface {
	// User section
    Boolean login(String userName);
    void logut();

    Boolean isAdmin();

    UserModel currentUserLoggedIn();

    List<String> userNames();

    ItemModel[] userListModels(Boolean IncludeAdmin);

    String userAvailability(String username, 
        Date sDate, Date eDate);

    // General model section
    
    AbstractModel model(String serialId);
    
    // Project section

    String addProject(ProjectModel newProject);
    String removeProject(int index);
    String removeProject(String identity);
    void removeProject(ProjectModel project);

    ProjectModel project(int index);
    ProjectModel project(String title);

    ItemModel[] projectItemModels();
    ItemModel[] projectItemModels(String UserIdentity);

    // Activities
    
    void addAbsenceActivity(ActivityModel activtity);
    void addActivity(String projectTitle, ActivityModel activity);
    void removeAbsenceActivity(String id);
    
    
    void removeActivity(String projectid, String activityId) throws Exception;

    ActivityModel activity(String projectId, String activityId);

    List<ActivityModel> activitiesById(String activityId);
    List<ActivityModel> activities();
    List<ActivityModel> activities(String userName);

    ItemModel[] activityItemModels();
    ItemModel[] activityItemModels(String userName);

    // Hour registrations

    void registerHour(String projectId, String activityId,
        String regId, int hours, String shortDescription);

    void unRegisterHour(String projectId, String activityId, String regId);

    HourRegistrationModel hourRegistrationModel(String activityId, String regId);
    ItemModel[] hourRegistrationItemModels();
    ItemModel[] hourRegistrationItemModels(String userNames);

    // Observer

    void subScribe(ICustomObserver observer);
    void unSubScribe(ICustomObserver observer);
    void unSubScribeAll();
}
