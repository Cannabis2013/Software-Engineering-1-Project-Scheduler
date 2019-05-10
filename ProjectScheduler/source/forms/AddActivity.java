package forms;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import abstractions.IApplicationProgrammingInterface;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

import formComponents.CustomTableComponent;
import models.ActivityModel;
import models.ItemModel;
import static_Domain.DateFormatizer;

import java.awt.Dimension;
import javax.swing.JButton;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.event.CaretListener;

import org.eclipse.swt.widgets.Item;

import javax.swing.event.CaretEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddActivity extends JPanel implements FrameImplementable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField activityTitleTextBox;
	private JTextField startDateSelector;
	private JTextField endDateSelector;
	private JLabel addUserLinkLabel, removeUserLinkLabel;
	private CustomTableComponent availableUserView,assignedUserView;
	private CustomFrame frame = null;
	private IApplicationProgrammingInterface service;
	private JTextArea descriptionTextBox;
	private JComboBox<String> projectSelector;
	private JTextField workHourSelector;
	private ActivityModel activity;
	private enum openMode {add,edit};
	private openMode mode;
	private String componentTitle;
	/**
	 * @wbp.parser.constructor
	 */
	public AddActivity(IApplicationProgrammingInterface service) {
		this.service = service;
		setPreferredSize(new Dimension(800, 572));
		initialize();
		availableUserView.addItems(this.service.userListModels());
		
		mode = openMode.add;
		
		componentTitle = "Add activity";
	}
	
	public AddActivity(IApplicationProgrammingInterface service, ActivityModel activity)
	{
		this.service = service;
		this.activity = activity;
		setPreferredSize(new Dimension(800, 572));
		initialize();
		initializeSelectors();
		
		mode = openMode.edit;
	}
	

	public void initialize() {
		setBounds(100, 100, 800, 520);
		setBackground(new Color(176, 224, 230));
		setLayout(null);
		contentPanel.setBounds(0, 0, 800, 566);
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setForeground(new Color(135, 206, 235));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel);
		JLabel lblActivity = new JLabel("Activity Title");
		lblActivity.setHorizontalAlignment(SwingConstants.CENTER);
		lblActivity.setBounds(15, 55, 84, 26);
		activityTitleTextBox = new JTextField();
		activityTitleTextBox.setBounds(105, 55, 122, 26);
		activityTitleTextBox.setColumns(10);
		JLabel lblStart = new JLabel("End date");
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart.setBounds(239, 102, 90, 26);
		JLabel lblEndWeek = new JLabel("Start date");
		lblEndWeek.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndWeek.setBounds(239, 55, 90, 26);
		
		startDateSelector = new JTextField();
		startDateSelector.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				initializeViews();
			}
		});
		
		startDateSelector.setEditable(false);
		startDateSelector.setBounds(326, 55, 110, 26);
		startDateSelector.setColumns(10);
		
		endDateSelector = new JTextField();
		endDateSelector.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				initializeViews();
			}
		});
		endDateSelector.setEditable(false);
		endDateSelector.setBounds(326, 102, 110, 26);
		endDateSelector.setColumns(10);
		
		JLabel lblProject = new JLabel("Project ID");
		lblProject.setHorizontalAlignment(SwingConstants.CENTER);
		lblProject.setBounds(15, 107, 78, 16);
		
		JLabel lblNewLabel = new JLabel("Assign users to activity");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 211, 800, 20);
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblFillInInformation = new JLabel("Fill in information");
		lblFillInInformation.setBounds(15, 16, 764, 26);
		lblFillInInformation.setHorizontalAlignment(SwingConstants.LEFT);
		lblFillInInformation.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblFillInInformation.setBackground(Color.BLACK);
		
		projectSelector = new JComboBox<String>();
		
		projectSelector.setBounds(105, 103, 122, 27);
		List<ItemModel> projectModels = service.projectItemModels();
		for(ItemModel model : projectModels)
			projectSelector.addItem(model.text(0));
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 243, 780, 323);
		contentPanel.setLayout(null);
		contentPanel.add(lblNewLabel);
		contentPanel.add(lblFillInInformation);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		addUserLinkLabel = new JLabel(">>");
		addUserLinkLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isDatesInitialized())
					return;
				
				List<ItemModel> currentItems;
				try {
					currentItems = availableUserView.currentItems();
				} catch (Exception e1) {
					return;
				}
				for(ItemModel item : currentItems)
				{
					if(!item.text(2).equals("Not available"))
					{
						assignedUserView.addItem(item);
						availableUserView.removeItem(item);
					}	
				}
			}
		});
		
		addUserLinkLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addUserLinkLabel.setBounds(370, 118, 40, 16);
		panel.add(addUserLinkLabel);
		
		removeUserLinkLabel = new JLabel("<<");
		removeUserLinkLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				List<ItemModel> currentItems;
				try {
					currentItems = assignedUserView.currentItems();
				} catch (Exception e1) {
					return;
				}
				for(ItemModel item : currentItems)
				{
					assignedUserView.removeItem(item);
					availableUserView.addItem(item);
				}
			}
		});
		removeUserLinkLabel.setHorizontalAlignment(SwingConstants.CENTER);
		removeUserLinkLabel.setBounds(370, 146, 40, 16);
		panel.add(removeUserLinkLabel);
		
		JLabel lblAvailableUsers = new JLabel("Available Users");
		lblAvailableUsers.setForeground(new Color(0, 128, 0));
		lblAvailableUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvailableUsers.setBounds(8, 6, 357, 16);
		panel.add(lblAvailableUsers);
		
		JLabel lblAssignedUsers = new JLabel("Assigned Users");
		lblAssignedUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssignedUsers.setForeground(Color.RED);
		lblAssignedUsers.setBounds(411, 6, 360, 16);
		panel.add(lblAssignedUsers);
		
		availableUserView = new CustomTableComponent();

		availableUserView.setBounds(8, 25, 363, 276);
		availableUserView.setMultipleSelectionMode(true);

		panel.add(availableUserView);
		
		String[] labels = {"Username", "Role", "Availability"};
		availableUserView.setHeaderLabels(labels);
		
		assignedUserView = new CustomTableComponent();

		assignedUserView.setBounds(414, 25, 363, 276);
		assignedUserView.setMultipleSelectionMode(true);

		panel.add(assignedUserView);
		
		assignedUserView.setHeaderLabels(labels);
		
		contentPanel.add(lblActivity);
		contentPanel.add(lblProject);
		contentPanel.add(projectSelector);
		contentPanel.add(activityTitleTextBox);
		contentPanel.add(lblStart);
		contentPanel.add(endDateSelector);
		contentPanel.add(lblEndWeek);
		contentPanel.add(startDateSelector);
		
		Icon calenderIcon = new ImageIcon("./Ressource/calendericon.png");
		JLabel startDateSelectorDialog = new JLabel("");
		startDateSelectorDialog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DateChooser dateDialog = new DateChooser(contentPanel, startDateSelector);
				dateDialog.setFrame(new CustomWidgetFrame());
			}
		});
		startDateSelectorDialog.setIcon(calenderIcon);
		startDateSelectorDialog.setHorizontalAlignment(SwingConstants.CENTER);
		startDateSelectorDialog.setBounds(432, 58, 20, 20);
		contentPanel.add(startDateSelectorDialog);
		
		
		JLabel endDateSelectorDialog = new JLabel("");
		endDateSelectorDialog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DateChooser dateDialog = new DateChooser(contentPanel, endDateSelector);
				dateDialog.setFrame(new CustomWidgetFrame());
			}
		});
		endDateSelectorDialog.setIcon(calenderIcon);
		endDateSelectorDialog.setHorizontalAlignment(SwingConstants.CENTER);
		endDateSelectorDialog.setBounds(432, 107, 20, 20);
		contentPanel.add(endDateSelectorDialog);
		
		descriptionTextBox = new JTextArea();
		descriptionTextBox.setBounds(479, 54, 175, 111);
		contentPanel.add(descriptionTextBox);
		descriptionTextBox.setColumns(10);
		
		JLabel label_3 = new JLabel("Short Description");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(479, 31, 171, 26);
		contentPanel.add(label_3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLACK);
		panel_3.setBounds(0, 198, 800, 2);
		contentPanel.add(panel_3);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(664, 101, 117, 29);
		contentPanel.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(mode == openMode.add)
					assembleActivity();
				else
					reAssembleActivity();
				close();
			}
		});

		btnSave.setBounds(664, 54, 117, 29);

		contentPanel.add(btnSave);
		
		JLabel lblEstimatedWorkHours = new JLabel("Estimated ");
		lblEstimatedWorkHours.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstimatedWorkHours.setBounds(15, 140, 84, 26);
		contentPanel.add(lblEstimatedWorkHours);
		
		workHourSelector = new JTextField();
		workHourSelector.setBounds(105, 147, 122, 26);
		contentPanel.add(workHourSelector);
		workHourSelector.setColumns(10);
		
		JLabel lblWorkHours = new JLabel("Work Hours");
		lblWorkHours.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkHours.setBounds(15, 155, 84, 26);
		contentPanel.add(lblWorkHours);
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				close();
			}
		});
	}

	public void initializeViews()
	{
		LocalDate sDate = null, eDate = null;
		try {
			sDate = DateFormatizer.dateFromString(startDateSelector.getText());
		} catch (DateTimeParseException e) {
			return;
		}
		
		try {
			eDate = DateFormatizer.dateFromString(endDateSelector.getText());
		} catch (DateTimeParseException e) {
			return;
		}
		
		List<ItemModel> availableUsermodels = availableUserView.allItems();
		availableUserView.clear();
		
		for(ItemModel item : availableUsermodels)
		{
			String availability = service.userAvailability(item.text(0), sDate, eDate);
			item.setText(availability, 2);
		}
		
		availableUserView.addItems(availableUsermodels);
		
		List<ItemModel> assignedUserModels = assignedUserView.allItems();
		assignedUserView.clear();
		
		for(ItemModel item : assignedUserModels)
		{
			String availability = service.userAvailability(item.text(0), sDate, eDate);
			if(availability.equals("Not available"))
			{
				assignedUserModels.remove(item);
				availableUserView.addItem(item);
			}
		}
		
		assignedUserView.addItems(assignedUserModels);	
	}
	
	public void initializeSelectors()
	{
		activityTitleTextBox.setText(activity.modelId());
		
		projectSelector.setSelectedItem(activity.parentModelId());
		
		String startDate = DateFormatizer.dateToString(activity.startDate()),
				endDate = DateFormatizer.dateToString(activity.endDate());
		
		workHourSelector.setText(Integer.toString(activity.estimatedHours()));
		
		startDateSelector.setText(startDate);
		endDateSelector.setText(endDate);
		
		descriptionTextBox.setText(activity.description());
		
		List<String> currentAssignedUsers = activity.AssignedUsers();
		List<ItemModel> restUsers = this.service.userListModels().
				stream().filter(item -> !currentAssignedUsers.contains(item.text(0))).collect(Collectors.toList());
		
		List<ItemModel> assignedUsers = currentAssignedUsers.
				stream().map(item -> new ItemModel(item)).collect(Collectors.toList());
		
		availableUserView.addItems(restUsers);
		assignedUserView.addItems(assignedUsers);
	}
	
	@Override
	public void setFrame(CustomFrame frame) {
		this.frame = frame;
		frame.setWidget(this);
		frame.setWindowModality(true);
		frame.ShowDialog();
	}

	@Override
	public void close() {
		frame.close();
	}
	
	public void assembleActivity()
	{
		if(projectSelector.getItemCount() < 1)
			return;
		
		String activityId = activityTitleTextBox.getText(),
				projectId = (String) projectSelector.getSelectedItem(),
				description = descriptionTextBox.getText();
		
		int workHours;
		try {
			workHours = Integer.parseInt(workHourSelector.getText());
		} catch (NumberFormatException e1) {
			workHours = 0;
		}
		
		LocalDate sDate = DateFormatizer.dateFromString(startDateSelector.getText()), 
				eDate = DateFormatizer.dateFromString(endDateSelector.getText());
		
		List<String> assignedUsers = assignedUserView.allItems().
				stream().map(item -> item.text(0)).collect(Collectors.toList());
		
		ActivityModel activity = new ActivityModel(activityId, sDate, eDate, workHours, assignedUsers, description);
		try {
			service.addActivity(projectId, activity);
		} catch (Exception e) {
			return;
		}
	}
	
	public void reAssembleActivity()
	{
		if(projectSelector.getItemCount() < 1)
			return;
		
		try {
			service.removeActivity(activity.parentModelId(), activity.modelId());
		} catch (Exception e) {
			return;
		}
		
		String activityId = activityTitleTextBox.getText(),
				projectId = (String) projectSelector.getSelectedItem(),
				description = descriptionTextBox.getText();
		
		int workHours;
		try {
			workHours = Integer.parseInt(workHourSelector.getText());
		} catch (NumberFormatException e1) {
			workHours = 0;
		}
		
		LocalDate sDate = DateFormatizer.dateFromString(startDateSelector.getText()), 
				eDate = DateFormatizer.dateFromString(endDateSelector.getText());
		
		List<String> assignedUsers = assignedUserView.allItems().
				stream().map(item -> item.text(0)).collect(Collectors.toList());
		
		activity.setModelidentity(activityId);
		activity.setEstimatedHours(workHours);
		activity.setStartDate(sDate);
		activity.setEndDate(eDate);
		activity.ClearAssignedUserIdentities();
		activity.AssignUsers(assignedUsers);
		activity.setDescription(description);
		
		try {
			service.addActivity(projectId, activity);
		} catch (Exception e) {
			return;
		}
	}
	
	public boolean isDatesInitialized()
	{
		LocalDate sDate = null, eDate = null;
		try {
			sDate = DateFormatizer.dateFromString(startDateSelector.getText());
		} catch (DateTimeParseException e) {
			return false;
		}
		
		try {
			eDate = DateFormatizer.dateFromString(endDateSelector.getText());
		} catch (DateTimeParseException e) {
			return false;
		}
		
		return true;
	}

	@Override
	public void setTitle(String title) {
		componentTitle = title;
	}

	@Override
	public String title() {
		return componentTitle;
	}
}
