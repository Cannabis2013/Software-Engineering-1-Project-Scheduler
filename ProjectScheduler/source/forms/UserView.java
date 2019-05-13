package forms;
import Application_Facade.ApplicationFrontEnd;
import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import abstractions.IApplicationProgrammingInterface;
import abstractions.ICustomObserver;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import formComponents.CustomTableComponent;
import models.HourRegistrationModel;
import models.ItemModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class UserView extends JPanel implements FrameImplementable, ICustomObserver {
	
	private ApplicationFrontEnd parent;
	private static final long serialVersionUID = 1L;
	private CustomFrame frame;
	private IApplicationProgrammingInterface service;
	private JMenuBar menuBar;
	private JButton registerButton;
	private String componentTitle;
	private CustomTableComponent activityView, hourRegistrationView;
	
	public UserView(ApplicationFrontEnd parent, IApplicationProgrammingInterface service) {
  
		this.service = service;
		this.parent = parent;
		initialize();
		updateView();
		
		String userId = service.currentUserLoggedIn().UserName();
		setTitle(String.format("User View : %s", userId));
		
		this.service.subScribe(this);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param parent 
	 */
	private void initialize() {
		
		setForeground(Color.WHITE);
		setBorder(null);
		setPreferredSize(new Dimension(1000, 560));
		setBackground(new Color(176, 224, 230));
		setBackground(Color.WHITE);
		setMinimumSize(new Dimension(640, 400));
		
		setBounds(100, 100, 1000, 560);
		
		menuBar = new JMenuBar();
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLogOut = new JMenuItem("Log Out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.close();
				parent.launchLoginDialog();
			}
		});
		mnFile.add(mntmLogOut);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);
	
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{200, 486, 0};
		gridBagLayout.rowHeights = new int[]{560, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		panel.setBorder(null);
		panel.setBackground(new Color(176, 224, 230));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{169, 0};
		gbl_panel.rowHeights = new int[]{1, 42, 167, 165, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblManagement = new JLabel("My Page");
		lblManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagement.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		
		GridBagConstraints gbc_lblManagement = new GridBagConstraints();
		gbc_lblManagement.fill = GridBagConstraints.BOTH;
		gbc_lblManagement.insets = new Insets(0, 0, 5, 0);
		gbc_lblManagement.gridx = 0;
		gbc_lblManagement.gridy = 0;
		panel.add(lblManagement, gbc_lblManagement);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("./Ressource/userIcon.png"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		JLabel lblWelcome = new JLabel("Welcome " + service.currentUserLoggedIn().UserName());
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
		gbc_lblWelcome.anchor = GridBagConstraints.NORTH;
		gbc_lblWelcome.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWelcome.insets = new Insets(0, 0, 5, 0);
		gbc_lblWelcome.gridx = 0;
		gbc_lblWelcome.gridy = 2;
		panel.add(lblWelcome, gbc_lblWelcome);
		
		JPanel buttonGroup = new JPanel();
		GridBagConstraints gbc_buttonGroup = new GridBagConstraints();
		gbc_buttonGroup.anchor = GridBagConstraints.SOUTH;
		gbc_buttonGroup.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonGroup.gridx = 0;
		gbc_buttonGroup.gridy = 3;
		panel.add(buttonGroup, gbc_buttonGroup);
		GridBagLayout gbl_buttonGroup = new GridBagLayout();
		gbl_buttonGroup.columnWidths = new int[]{0, 0};
		gbl_buttonGroup.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_buttonGroup.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_buttonGroup.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		buttonGroup.setLayout(gbl_buttonGroup);
		
		registerButton = new JButton("Register Hours");
		GridBagConstraints gbc_registerButton = new GridBagConstraints();
		gbc_registerButton.fill = GridBagConstraints.BOTH;
		gbc_registerButton.gridx = 0;
		gbc_registerButton.gridy = 0;
		buttonGroup.add(registerButton, gbc_registerButton);
		
		JButton btnFillAbsenceActivity = new JButton("Fill Absence Activity");
		btnFillAbsenceActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AbsentActivityDialog absenceDialog = new AbsentActivityDialog(service);
				absenceDialog.setFrame(new CustomDialog());
			}
		});
		
		JButton btnEditSelectedHour = new JButton("Edit Selected Hour");
		btnEditSelectedHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ItemModel hourItemModel = hourRegistrationView.currentItem();
					String projectId = hourItemModel.text(5);
					String activityId = hourItemModel.text(4);
					String regId = hourItemModel.text(0);
					HourRegistrationModel regModel = service.hourRegistrationModel(projectId, activityId, regId);
					RegisterHour hourDialog = new RegisterHour(service, regModel);
					hourDialog.setFrame(new CustomDialog());
				} catch (Exception e) {
					return;
				}
			}
		});
		GridBagConstraints gbc_btnEditSelectedHour = new GridBagConstraints();
		gbc_btnEditSelectedHour.fill = GridBagConstraints.BOTH;
		gbc_btnEditSelectedHour.gridx = 0;
		gbc_btnEditSelectedHour.gridy = 1;
		buttonGroup.add(btnEditSelectedHour, gbc_btnEditSelectedHour);
		
		JButton btnRemoveRegisteredHour = new JButton("Remove Selected Registration");
		btnRemoveRegisteredHour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ItemModel item;
				try {
					item = hourRegistrationView.currentItem();
				} catch (Exception e) {
					return;
				}
				String parentProjectId = item.text(5),
						parentActivityId = item.text(4),
						registrationId = item.text(0);
				
				try {
					service.unRegisterHour(parentProjectId, parentActivityId, registrationId);
				} catch (Exception e) {
					return;
				}
				
			}
		});
		GridBagConstraints gbc_btnRemoveRegisteredHour = new GridBagConstraints();
		gbc_btnRemoveRegisteredHour.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemoveRegisteredHour.gridx = 0;
		gbc_btnRemoveRegisteredHour.gridy = 2;
		buttonGroup.add(btnRemoveRegisteredHour, gbc_btnRemoveRegisteredHour);
		GridBagConstraints gbc_btnFillAbsenceActivity = new GridBagConstraints();
		gbc_btnFillAbsenceActivity.fill = GridBagConstraints.BOTH;
		gbc_btnFillAbsenceActivity.gridx = 0;
		gbc_btnFillAbsenceActivity.gridy = 3;
		buttonGroup.add(btnFillAbsenceActivity, gbc_btnFillAbsenceActivity);
		
		JButton btnRemoveAbsenceActivity = new JButton("Remove absence activity");

		btnRemoveAbsenceActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ItemModel model;
				try {
					model = activityView.currentItem();
				} catch (Exception e) {
					return;
				}
				
				try {
					service.removeAbsenceActivity(model.text(0));
				} catch (Exception e) {
					
				}
			}
		});
		GridBagConstraints gbc_btnRemoveAbsenceActivity = new GridBagConstraints();
		gbc_btnRemoveAbsenceActivity.fill = GridBagConstraints.BOTH;
		gbc_btnRemoveAbsenceActivity.gridx = 0;
		gbc_btnRemoveAbsenceActivity.gridy = 4;
		buttonGroup.add(btnRemoveAbsenceActivity, gbc_btnRemoveAbsenceActivity);
		
		
		JButton btnProjectOverview = new JButton("Management");
		GridBagConstraints gbc_btnProjectOverview = new GridBagConstraints();
		gbc_btnProjectOverview.fill = GridBagConstraints.BOTH;
		gbc_btnProjectOverview.gridx = 0;
		gbc_btnProjectOverview.gridy = 5;
		buttonGroup.add(btnProjectOverview, gbc_btnProjectOverview);
		btnProjectOverview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.close();
				if(service.isUserProjectLeader() || service.isAdmin())
					parent.launchMainView();
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterHour rh = new RegisterHour(service);
				rh.setFrame(new CustomDialog());
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(176, 224, 230));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblActivityOverview = new JLabel("Assigned Activities");
		GridBagConstraints gbc_lblActivityOverview = new GridBagConstraints();
		gbc_lblActivityOverview.insets = new Insets(0, 0, 5, 0);
		gbc_lblActivityOverview.gridx = 0;
		gbc_lblActivityOverview.gridy = 0;
		panel_2.add(lblActivityOverview, gbc_lblActivityOverview);
		lblActivityOverview.setHorizontalAlignment(SwingConstants.LEFT);
		lblActivityOverview.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		
		activityView = new CustomTableComponent();
		activityView.setHeaderLabels(new String[] {"Title", "Start Week", "End Week", "Estimated Hours", "Total Hours", "Project"});
		GridBagConstraints gbc_activityView = new GridBagConstraints();
		gbc_activityView.insets = new Insets(0, 0, 5, 0);
		gbc_activityView.fill = GridBagConstraints.BOTH;
		gbc_activityView.gridx = 0;
		gbc_activityView.gridy = 1;
		panel_2.add(activityView, gbc_activityView);
		
		JLabel lblRegisteredHoursEntities = new JLabel("Registered Hours Entities");
		GridBagConstraints gbc_lblRegisteredHoursEntities = new GridBagConstraints();
		gbc_lblRegisteredHoursEntities.insets = new Insets(0, 0, 5, 0);
		gbc_lblRegisteredHoursEntities.gridx = 0;
		gbc_lblRegisteredHoursEntities.gridy = 2;
		panel_2.add(lblRegisteredHoursEntities, gbc_lblRegisteredHoursEntities);
		lblRegisteredHoursEntities.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegisteredHoursEntities.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		
		hourRegistrationView = new CustomTableComponent();
		hourRegistrationView.setHeaderLabels(new String[] {"Title", "Username", "Hours", "Registration Date", "Activity", "Project"});
		GridBagConstraints gbc_hourRegistrationView = new GridBagConstraints();
		gbc_hourRegistrationView.fill = GridBagConstraints.BOTH;
		gbc_hourRegistrationView.gridx = 0;
		gbc_hourRegistrationView.gridy = 3;
		panel_2.add(hourRegistrationView, gbc_hourRegistrationView);
		if(!service.isUserProjectLeader() && !service.isAdmin())
			btnProjectOverview.setEnabled(false);

	}
	
	@Override
	public void setFrame(CustomFrame frame) {
		this.frame = frame;
		
		this.frame.setWidget(this);
		this.frame.setMenuBar(menuBar);
		this.frame.ShowDialog();
	}

	@Override
	public void close() {
		frame.close();
	}
	
	public void launchAddProject() {
		AddProject ap = new AddProject(service);
		
		ap.setFrame(new CustomDialog());
	}

	@Override
	public void updateView() {
		
		activityView.clear();
		List<ItemModel> activities = service.activityItemModels();
		activityView.addItems(activities);
		
		if(activityView.rowCount() > 0)
			registerButton.setEnabled(true);
		else
			registerButton.setEnabled(false);
		
		hourRegistrationView.clear();
		List<ItemModel> registeredHours = service.hourRegistrationItemModels();
		hourRegistrationView.addItems(registeredHours);
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
