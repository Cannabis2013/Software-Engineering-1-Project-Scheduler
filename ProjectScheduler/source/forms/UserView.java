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

public class UserView extends JPanel implements FrameImplementable, ICustomObserver {
	
	private ApplicationFrontEnd parent;
	private static final long serialVersionUID = 1L;
	private CustomFrame frame;
	private IApplicationProgrammingInterface service;
	private JMenuBar menuBar;
	private CustomTableComponent activityView, registerHourView;
	
	public UserView(ApplicationFrontEnd parent, IApplicationProgrammingInterface service) {
		this.service = service;
		setForeground(Color.WHITE);
		setBorder(null);
		setPreferredSize(new Dimension(1000, 560));
		setBackground(new Color(176, 224, 230));
		setBackground(Color.WHITE);
		setMinimumSize(new Dimension(1000, 560));
		this.parent = parent;
		initialize();
		updateView();
		
		
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
		setLayout(null);
		new ImageIcon("./Ressource/calendericon.png");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(200, 0, 800, 560);
		panel_1.setBackground(new Color(176, 224, 230));
		add(panel_1);
		panel_1.setLayout(null);
		activityView = new CustomTableComponent();
		activityView.setBounds(31, 45, 685, 233);
		panel_1.add(activityView);
		
		String[] labels = {"Title", "Start date", "End date", "Total hours", "Project"};
		activityView.setHeaderLabels(labels);
		
		JLabel lblActivityOverview = new JLabel("My Assigned Activities");
		lblActivityOverview.setHorizontalAlignment(SwingConstants.LEFT);
		lblActivityOverview.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblActivityOverview.setBounds(31, 6, 253, 45);
		panel_1.add(lblActivityOverview);
		
		JLabel lblRegisteredHoursEntities = new JLabel("Registered Hours Entities");
		lblRegisteredHoursEntities.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegisteredHoursEntities.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblRegisteredHoursEntities.setBounds(31, 270, 253, 45);
		panel_1.add(lblRegisteredHoursEntities);
		
		registerHourView = new CustomTableComponent();
		registerHourView.setHeaderLabels(new String[] {"Title", "Estimated working hours", "Total registered hours", "Parent project"});
		registerHourView.setBounds(31, 307, 685, 233);
		panel_1.add(registerHourView);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 200, 560);
		add(panel);
		panel.setBorder(null);
		panel.setBackground(new Color(176, 224, 230));
		panel.setLayout(null);
		
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(0, 0, 0, 0);
		panel.add(verticalStrut);
		
		JLabel lblManagement = new JLabel("My Page");
		lblManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagement.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblManagement.setBounds(26, 6, 169, 42);

		panel.add(lblManagement);
		
		
		JButton btnProjectOverview = new JButton("Management");
		btnProjectOverview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.close();
				parent.launchMainView();
			}
		});
		btnProjectOverview.setBounds(41, 383, 141, 29);
		panel.add(btnProjectOverview);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("./Ressource/userIcon.png"));
		lblNewLabel.setBounds(27, 60, 167, 167);
		panel.add(lblNewLabel);
		
		String username =  service.currentUserLoggedIn().UserName();
		JLabel lblWelcome = new JLabel("Welcome " + username);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(26, 239, 169, 16);
		panel.add(lblWelcome);
		
		JButton btnRegisterHours_1 = new JButton("Register Hours");
		btnRegisterHours_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterHour rh = new RegisterHour(service);
				rh.setFrame(new CustomWidgetFrame());
			}
		});
		btnRegisterHours_1.setBounds(41, 302, 141, 29);
		panel.add(btnRegisterHours_1);
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
		dialog.setFrame(new CustomWidgetFrame());
		
		dialog.setVisible(true);
	
	}
	
	public void launchAddProject() {
		AddProject ap = new AddProject(service);
		
		ap.setFrame(new CustomWidgetFrame());
	}

	@Override
	public void updateView() {
		
		activityView.clear();
		
		List<ItemModel> activities = service.activityItemModels();
		
		activityView.addItems(activities);
		
		registerHourView.clear();
		
		List<ItemModel> registeredHours = service.hourRegistrationItemModels();
		
		registerHourView.addItems(registeredHours);
	}
}
