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

import org.eclipse.swt.widgets.MessageBox;

import formComponents.CustomTableComponent;
import models.ItemModel;

public class ProjectView extends JPanel implements FrameImplementable, ICustomObserver {
	
	private ApplicationFrontEnd parent;
	private static final long serialVersionUID = 1L;
	private CustomFrame frame;
	DateChooser dateChooser;
	private IApplicationProgrammingInterface service;
	private JMenuBar menuBar;
	private CustomTableComponent activityView;
	private CustomTableComponent projectView;
	
	public ProjectView(ApplicationFrontEnd parent, IApplicationProgrammingInterface service) {
		this.service = service;
		setForeground(Color.WHITE);
		setBorder(null);
		setPreferredSize(new Dimension(1000, 540));
		setBackground(new Color(176, 224, 230));
		setBackground(Color.WHITE);
		setMinimumSize(new Dimension(1000, 540));
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
		setLayout(null);
		Icon calenderIcon = new ImageIcon("./Ressource/calendericon.png");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(200, 0, 800, 540);
		panel_1.setBackground(new Color(176, 224, 230));
		add(panel_1);
		panel_1.setLayout(null);
		activityView = new CustomTableComponent();
		activityView.setBounds(31, 65, 623, 443);
		panel_1.add(activityView);
		
		String[] labels = {"Title", "Estimated working hours", "Total registered hours", "Parent project"};
		activityView.setHeaderLabels(labels);
		JButton btnAddActivity = new JButton("Add Activity");
		btnAddActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchAddActivity();
			}
		});
		btnAddActivity.setBounds(666, 65, 128, 29);
		panel_1.add(btnAddActivity);
		
		JButton btnEditActivity = new JButton("Edit Activity");
		btnEditActivity.setBounds(666, 105, 128, 29);
		panel_1.add(btnEditActivity);
		
		JButton btnRemoveActivty = new JButton("Remove Activty");
		

		btnRemoveActivty.setBounds(664, 145, 130, 29);

		panel_1.add(btnRemoveActivty);
		
		JLabel lblActivityOverview = new JLabel("Activity Overview");
		lblActivityOverview.setHorizontalAlignment(SwingConstants.CENTER);
		lblActivityOverview.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblActivityOverview.setBounds(31, 8, 253, 45);
		panel_1.add(lblActivityOverview);
		
		JButton btnCheckUsers = new JButton("Check Users");
		btnCheckUsers.setBounds(666, 185, 128, 29);
		panel_1.add(btnCheckUsers);
		
		JButton button = new JButton("Check Description");
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(666, 225, 128, 29);
		panel_1.add(button);
		
		JButton btnRegisterHours = new JButton("Back to My Page");
		btnRegisterHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchUserView();
			}
		});
		btnRegisterHours.setBounds(666, 265, 128, 29);
		panel_1.add(btnRegisterHours);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 200, 540);
		add(panel);
		panel.setBorder(null);
		panel.setBackground(new Color(176, 224, 230));
		panel.setLayout(null);
		
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(0, 0, 0, 0);
		panel.add(verticalStrut);
		
		JLabel lblManagement = new JLabel("Projects");
		lblManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagement.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblManagement.setBounds(13, 6, 169, 45);

		panel.add(lblManagement);
		
		projectView = new CustomTableComponent();
		projectView.setBounds(24, 65, 158, 303);
		panel.add(projectView);
		
		
		String[] projectViewHeaderLabels = {"Project title"};
		projectView.setHeaderLabels(projectViewHeaderLabels);
		
		if(service.isAdmin())
		{
			JButton removeProjectButton = new JButton("Remove Project");
			removeProjectButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String pTitle;
					try {
						pTitle = projectView.currentItem().text(0);
					} catch (Exception e2) {
						return;
					}
					try {
						service.removeProject(pTitle);
					} catch (Exception e1) {
						
					}
				}
			});
			removeProjectButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
			removeProjectButton.setBounds(41, 477, 126, 29);
			panel.add(removeProjectButton);
			
			JButton editProjectButton = new JButton("Edit Project");
			editProjectButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			editProjectButton.setBounds(41, 447, 126, 29);
			panel.add(editProjectButton);
			
			JButton addProjectButton = new JButton("Add Project");
			addProjectButton.setBounds(41, 414, 126, 29);
			panel.add(addProjectButton);
			addProjectButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					launchAddProject();
				}
			});
			addProjectButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
			
			JButton projectOverViewButton = new JButton("Project Overview");
			projectOverViewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					launchProjectDialog();
				}
			});
			projectOverViewButton.setBounds(41, 383, 126, 29);
			panel.add(projectOverViewButton);
		}
		
		JButton btnProjectOverview = new JButton("Project Overview");
		btnProjectOverview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchProjectDialog();
				
			}
		});
		btnProjectOverview.setBounds(41, 383, 126, 29);
		panel.add(btnProjectOverview);

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
		dialog.setVisible(true);
		dialog.setFrame(new CustomWidgetFrame());
	
	}

	public void launchAddActivity() {
		AddActivity aa = new AddActivity(service);
		aa.setFrame(new CustomWidgetFrame());
	}
	
	public void launchAddProject() {
		AddProject ap = new AddProject(service);
		ap.setFrame(new CustomWidgetFrame());
	}
	
	public void launchUserView() {
		frame.close();
		parent.launchUserView();
	}

	@Override
	public void updateView() {
		
		projectView.clear();
		activityView.clear();
		
		List<ItemModel> assignedProjects = service.projectItemModels();
		
		projectView.addItems(assignedProjects);
		
		List<ItemModel> activities;
		
		activities = service.activityItemModels();
		
		activityView.addItems(activities);
	}
}
