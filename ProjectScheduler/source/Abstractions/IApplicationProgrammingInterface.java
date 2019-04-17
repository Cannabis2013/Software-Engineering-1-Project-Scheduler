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
    Boolean Login(String userName, String password);
    void Logut();

    Boolean IsAdmin();

    UserModel CurrentUserLoggedIn();

    List<String> UserNames();

    ItemModel[] UserListModels(Boolean IncludeAdmin);

    String UserAvailability(String username, 
        Date sDate, Date eDate);

    // Project section

    String AddProject(ProjectModel newProject);
    String RemoveProject(int index);
    String RemoveProject(String identity);
    void RemoveProject(ProjectModel project);

    ProjectModel Project(int index);
    ProjectModel Project(String identity);

    ItemModel[] ProjectItemModels();
    ItemModel[] ProjectItemModels(String UserIdentity);

    // Activities
    void AddAbsenceActivity(ActivityModel activtity);
    void RemoveAbsenceActivity(String id);

    void RemoveActivity(String projectid, String activityId);

    ActivityModel Activity(String projectId, String activityId);
    ActivityModel Activity(String activityId);

    List<ActivityModel> Activities();
    List<ActivityModel> Activities(String userName);

    ItemModel[] activityItemModels();
    ItemModel[] activityItemModels(String userName);

    // Hour registrations

    void RegisterHour(String projectId, String activityId,
        String regId, int hours, String shortDescription);

    void UnRegisterHour(String projectId, String activityId, String regId);

    HourRegistrationModel HourRegistrationModel(String activityId, String regId);
    ItemModel[] HourRegistrationItemModels();
    ItemModel[] HourRegistrationItemModels(String userNames);

    // Observer

    void SubScribe(ICustomObserver observer);
    void UnSubScribe(ICustomObserver observer);
    void UnSubScribeAll();
}
