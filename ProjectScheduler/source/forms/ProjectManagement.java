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
import models.ActivityModel;
import models.HourRegistrationModel;
import models.ItemModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Canvas;
import customGraphics.GraphicsPlot2D;

public class ProjectManagement extends JPanel implements FrameImplementable, ICustomObserver {
	
	private ApplicationFrontEnd parent;
	private static final long serialVersionUID = 1L;
	private CustomFrame frame;
	private JButton addProjectButton, removeProjectButton;
	private IApplicationProgrammingInterface service;
	private JMenuBar menuBar;
	private CustomTableComponent activityView;
	private CustomTableComponent projectSelectorView;
	private String componentTitle = "Management";
	private GraphicsPlot2D graphView;
	
	public ProjectManagement(ApplicationFrontEnd parent, IApplicationProgrammingInterface service) {
		this.service = service;
		setForeground(Color.WHITE);
		setBorder(null);
		setPreferredSize(new Dimension(1000, 540));
		setBackground(new Color(176, 224, 230));
		setBackground(Color.WHITE);
		setMinimumSize(new Dimension(640, 400));
		this.parent = parent;
		initialize();
		setButtonsEnabled();
		updateView();
		
		this.service.subScribe(this);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 224, 230));
		add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{363, 113, 0};
		gbl_panel_1.rowHeights = new int[]{331, 35, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblActivityOverview = new JLabel("Project overview");
		GridBagConstraints gbc_lblActivityOverview = new GridBagConstraints();
		gbc_lblActivityOverview.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblActivityOverview.gridx = 0;
		gbc_lblActivityOverview.gridy = 0;
		panel_2.add(lblActivityOverview, gbc_lblActivityOverview);
		lblActivityOverview.setHorizontalAlignment(SwingConstants.CENTER);
		lblActivityOverview.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		
		projectSelectorView = new CustomTableComponent();
		String[] labels = {"Title", "Start date", "End date", "Total hours", "Project"};
		projectSelectorView.setHeaderLabels(labels);
		GridBagConstraints gbc_projectSelectorView = new GridBagConstraints();
		gbc_projectSelectorView.fill = GridBagConstraints.BOTH;
		gbc_projectSelectorView.gridx = 0;
		gbc_projectSelectorView.gridy = 1;
		panel_2.add(projectSelectorView, gbc_projectSelectorView);
		projectSelectorView.setColumnCount(5);
		projectSelectorView.setMultipleSelectionMode(true);
		projectSelectorView.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String projectId;
				try {
					projectId = projectSelectorView.currentItem().text(0);
				} catch (Exception e1) {
					return;
				}
				
				List<ItemModel> activityItemModels;
				try {
					activityItemModels = service.projectActivityItemModels(projectId);
				} catch (Exception e1) {
					e1.printStackTrace();
					return;
				}
				
				activityView.clear();
				activityView.addItems(activityItemModels);
			}
		});
		projectSelectorView.setHeaderLabels(new String[] {"Project title", "Project leader", "Start date", "End date", "Total hours"});
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		panel_1.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblActivityOverview_1 = new JLabel("Activity overview");
		lblActivityOverview_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblActivityOverview_1.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblActivityOverview_1 = new GridBagConstraints();
		gbc_lblActivityOverview_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblActivityOverview_1.gridx = 0;
		gbc_lblActivityOverview_1.gridy = 0;
		panel_3.add(lblActivityOverview_1, gbc_lblActivityOverview_1);
		activityView = new CustomTableComponent();
		activityView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					ItemModel model = activityView.currentItem();
					String projectId = model.text(5);
					String activityId = model.text(0);
					
					ActivityModel activity = service.activity(projectId, activityId);
					List<HourRegistrationModel> regObjects = activity.hourRegistrationModels();
					
					graphView.setData(regObjects);
					
				} catch (Exception e) {
					
				}
				
				
			}
		});
		GridBagConstraints gbc_activityView = new GridBagConstraints();
		gbc_activityView.fill = GridBagConstraints.BOTH;
		gbc_activityView.gridx = 0;
		gbc_activityView.gridy = 1;
		panel_3.add(activityView, gbc_activityView);
		activityView.setHeaderLabels(new String[] {"Title", "Start week", "End week", "Estimated hours", "Total hours", "Project"});
		
		graphView = new GraphicsPlot2D();
		graphView.setPreferredSize(new Dimension(10, 400));
		GridBagConstraints gbc_graphView = new GridBagConstraints();
		gbc_graphView.fill = GridBagConstraints.BOTH;
		gbc_graphView.gridx = 0;
		gbc_graphView.gridy = 2;
		panel_3.add(graphView, gbc_graphView);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridwidth = 2;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		panel_1.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnRegisterHours = new JButton("User page");
		GridBagConstraints gbc_btnRegisterHours = new GridBagConstraints();
		gbc_btnRegisterHours.fill = GridBagConstraints.BOTH;
		gbc_btnRegisterHours.gridx = 0;
		gbc_btnRegisterHours.gridy = 0;
		panel.add(btnRegisterHours, gbc_btnRegisterHours);
		
		addProjectButton = new JButton("Add Project");
		
		GridBagConstraints gbc_addProjectButton = new GridBagConstraints();
		gbc_addProjectButton.fill = GridBagConstraints.BOTH;
		gbc_addProjectButton.gridx = 1;
		gbc_addProjectButton.gridy = 0;
		panel.add(addProjectButton, gbc_addProjectButton);
		
		removeProjectButton = new JButton("Remove project");
		
		GridBagConstraints gbc_removeProjectButton = new GridBagConstraints();
		gbc_removeProjectButton.fill = GridBagConstraints.BOTH;
		gbc_removeProjectButton.gridx = 2;
		gbc_removeProjectButton.gridy = 0;
		panel.add(removeProjectButton, gbc_removeProjectButton);
		JButton btnAddActivity = new JButton("Add Activity");
		GridBagConstraints gbc_btnAddActivity = new GridBagConstraints();
		gbc_btnAddActivity.fill = GridBagConstraints.BOTH;
		gbc_btnAddActivity.gridx = 0;
		gbc_btnAddActivity.gridy = 1;
		panel.add(btnAddActivity, gbc_btnAddActivity);
		
		JButton btnEditActivity = new JButton("Edit Activity");
		btnEditActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String projectId = projectSelectorView.currentItem().text(0);
					String activityId = activityView.currentItem().text(0);;
					ActivityModel activity = service.activity(projectId, activityId);
					
					AddActivity addDialog = new AddActivity(service, activity);
					addDialog.setFrame(new CustomDialog());
				} catch (Exception e) {
					return;
				}
				

			}
		});
		GridBagConstraints gbc_btnEditActivity = new GridBagConstraints();
		gbc_btnEditActivity.fill = GridBagConstraints.BOTH;
		gbc_btnEditActivity.gridx = 1;
		gbc_btnEditActivity.gridy = 1;
		panel.add(btnEditActivity, gbc_btnEditActivity);
		
		JButton btnRemoveActivty = new JButton("Remove Activty");
		GridBagConstraints gbc_btnRemoveActivty = new GridBagConstraints();
		gbc_btnRemoveActivty.fill = GridBagConstraints.BOTH;
		gbc_btnRemoveActivty.gridx = 2;
		gbc_btnRemoveActivty.gridy = 1;
		panel.add(btnRemoveActivty, gbc_btnRemoveActivty);
		btnAddActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchAddActivity();
			}
		});
		removeProjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pTitle;
				try {
					pTitle = projectSelectorView.currentItem().text(0);
				} catch (Exception e2) {
					return;
				}
				try {
					service.removeProject(pTitle);
				} catch (Exception e1) {
					
				}
			}
		});
		addProjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchAddProject();
			}
		});
		btnRegisterHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchUserView();
			}
		});
		
		setBounds(100, 100, 1000, 540);
		
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
		
		if(service.isAdmin())
		{
			JMenuItem mntmAddProject = new JMenuItem("Add Project");
			mnEdit.add(mntmAddProject);
			mntmAddProject.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					launchAddProject();
				}
			});
		}
		
		JMenuItem mntmAddActivity = new JMenuItem("Add Activity");
		mnEdit.add(mntmAddActivity);
		new ImageIcon("./Ressource/calendericon.png");
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

	public void launchAddActivity() {
		AddActivity aa = new AddActivity(service);
		aa.setFrame(new CustomDialog());
	}
	
	public void launchAddProject() {
		AddProject ap = new AddProject(service);
		ap.setFrame(new CustomDialog());
	}
	
	public void launchUserView() {
		frame.close();
		parent.launchUserView();
	}
	
	public void setButtonsEnabled()
	{
		if(!service.isAdmin())
		{
			addProjectButton.setEnabled(false);
			removeProjectButton.setEnabled(false);
		}
	}
	
	@Override
	public void updateView() {
		
		projectSelectorView.clear();
		List<ItemModel> assignedProjects = service.projectItemModels();
		projectSelectorView.addItems(assignedProjects);
		activityView.clear();
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
