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
import java.awt.Component;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import formComponents.CustomTableComponent;
import models.ItemModel;
import javax.swing.JPopupMenu;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;

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
		setForeground(Color.WHITE);
		setBorder(null);
		setPreferredSize(new Dimension(1000, 560));
		setBackground(new Color(176, 224, 230));
		setBackground(Color.WHITE);
		setMinimumSize(new Dimension(640, 400));
		this.parent = parent;
		initialize();
		updateView();
		
		String userId = service.currentUserLoggedIn().UserName();
		setTitle(String.format("User view : %s", userId));
		
		this.service.subScribe(this);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param parent 
	 */
	private void initialize() {
		setBounds(100, 100, 1000, 560);
		
		menuBar = new JMenuBar();
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
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
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmAddProject = new JMenuItem("Add Project");
		mntmAddProject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				launchAddProject();
			}
		});
		
		mnEdit.add(mntmAddProject);
		
		JMenuItem mntmAddActivity = new JMenuItem("Add Activity");
		mnEdit.add(mntmAddActivity);
		new ImageIcon("./Ressource/calendericon.png");
		
		String[] labels = {"Title", "Start date", "End date", "Total hours", "P"};
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
				gbl_buttonGroup.rowHeights = new int[]{0, 0, 0, 0};
				gbl_buttonGroup.columnWeights = new double[]{1.0, Double.MIN_VALUE};
				gbl_buttonGroup.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
				buttonGroup.setLayout(gbl_buttonGroup);
				
				registerButton = new JButton("Register Hours");
				GridBagConstraints gbc_registerButton = new GridBagConstraints();
				gbc_registerButton.fill = GridBagConstraints.BOTH;
				gbc_registerButton.gridx = 0;
				gbc_registerButton.gridy = 0;
				buttonGroup.add(registerButton, gbc_registerButton);
				
				JButton btnFillAbsenceActivity = new JButton("Fill absence activity");
				btnFillAbsenceActivity.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						AbsentActivityDialog absenceDialog = new AbsentActivityDialog(service);
						absenceDialog.setFrame(new CustomDialog());
					}
				});
				GridBagConstraints gbc_btnFillAbsenceActivity = new GridBagConstraints();
				gbc_btnFillAbsenceActivity.fill = GridBagConstraints.BOTH;
				gbc_btnFillAbsenceActivity.gridx = 0;
				gbc_btnFillAbsenceActivity.gridy = 1;
				buttonGroup.add(btnFillAbsenceActivity, gbc_btnFillAbsenceActivity);
				
				
				JButton btnProjectOverview = new JButton("Management");
				GridBagConstraints gbc_btnProjectOverview = new GridBagConstraints();
				gbc_btnProjectOverview.fill = GridBagConstraints.BOTH;
				gbc_btnProjectOverview.gridx = 0;
				gbc_btnProjectOverview.gridy = 2;
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
		
		JLabel lblActivityOverview = new JLabel("My Assigned Activities");
		GridBagConstraints gbc_lblActivityOverview = new GridBagConstraints();
		gbc_lblActivityOverview.insets = new Insets(0, 0, 5, 0);
		gbc_lblActivityOverview.gridx = 0;
		gbc_lblActivityOverview.gridy = 0;
		panel_2.add(lblActivityOverview, gbc_lblActivityOverview);
		lblActivityOverview.setHorizontalAlignment(SwingConstants.LEFT);
		lblActivityOverview.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		
		activityView = new CustomTableComponent();
		activityView.setHeaderLabels(new String[] {"Title", "Startdate", "Enddate", "Estimated hours", "Total hours", "Project"});
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
		hourRegistrationView.setHeaderLabels(new String[] {"Title", "Username", "Hours", "Registration date", "Activity"});
		GridBagConstraints gbc_hourRegistrationView = new GridBagConstraints();
		gbc_hourRegistrationView.fill = GridBagConstraints.BOTH;
		gbc_hourRegistrationView.gridx = 0;
		gbc_hourRegistrationView.gridy = 3;
		panel_2.add(hourRegistrationView, gbc_hourRegistrationView);
		if(!service.isUserProjectLeader() && !service.isAdmin())
			btnProjectOverview.setEnabled(false);
		
		String username =  service.currentUserLoggedIn().UserName();
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
	
	public void launchProjectDialog()
	{
		ProjectDialog dialog = new ProjectDialog(service);
		dialog.setFrame(new CustomDialog());
		
		dialog.setVisible(true);
	
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
	private static void addPopup(Component component, final JPopupMenu popup) {
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
