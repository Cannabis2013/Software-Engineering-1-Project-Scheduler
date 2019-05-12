package forms;

import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import abstractions.IApplicationProgrammingInterface;
import models.ProjectModel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;

public class AddProject extends JPanel implements FrameImplementable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField startDateTextField;
	private JTextField endDateTextField;
	private JTextArea descriptionTextBox;
	private DateChooser dateChooser;
	private JComboBox<String> projectLeaderSelector;
	private CustomFrame frame = null;
	private IApplicationProgrammingInterface service;
	private String componentTitle = "Add project";
	private JTextField projectNameSelector;
	/**
	 * Create the dialog.
	 */
	public AddProject(IApplicationProgrammingInterface service) {
		setMinimumSize(new Dimension(120, 120));
		this.service = service;
		initialize();
	}
	
	private void initialize() {
		setBounds(100, 100, 610, 316);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{516, 0};
		gridBagLayout.rowHeights = new int[]{316, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		Icon calenderIcon = new ImageIcon("./Ressource/calendericon.png");
		String[] allUserNames = service.allUserNames();
		setPreferredSize(getSize());
		contentPanel.setMinimumSize(new Dimension(120, 120));
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		add(contentPanel, gbc_contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{140, 180, 0};
		gbl_contentPanel.rowHeights = new int[]{39, 171, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		JLabel lblFillInInformation = new JLabel("Fill in information");
		lblFillInInformation.setFont(new Font("Rockwell", Font.PLAIN, 24));
		GridBagConstraints gbc_lblFillInInformation = new GridBagConstraints();
		gbc_lblFillInInformation.fill = GridBagConstraints.VERTICAL;
		gbc_lblFillInInformation.insets = new Insets(0, 0, 5, 0);
		gbc_lblFillInInformation.gridwidth = 2;
		gbc_lblFillInInformation.gridx = 0;
		gbc_lblFillInInformation.gridy = 0;
		contentPanel.add(lblFillInInformation, gbc_lblFillInInformation);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, -26, 22, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblProjectName = new JLabel("Project name");
		GridBagConstraints gbc_lblProjectName = new GridBagConstraints();
		gbc_lblProjectName.fill = GridBagConstraints.VERTICAL;
		gbc_lblProjectName.insets = new Insets(0, 0, 5, 5);
		gbc_lblProjectName.gridx = 0;
		gbc_lblProjectName.gridy = 0;
		panel.add(lblProjectName, gbc_lblProjectName);
		lblProjectName.setFont(new Font("Rockwell", Font.PLAIN, 13));
		
		projectNameSelector = new JTextField();
		GridBagConstraints gbc_projectNameSelector = new GridBagConstraints();
		gbc_projectNameSelector.gridwidth = 2;
		gbc_projectNameSelector.fill = GridBagConstraints.BOTH;
		gbc_projectNameSelector.insets = new Insets(0, 0, 5, 0);
		gbc_projectNameSelector.gridx = 1;
		gbc_projectNameSelector.gridy = 0;
		panel.add(projectNameSelector, gbc_projectNameSelector);
		projectNameSelector.setColumns(10);
		JLabel lblStartDate = new JLabel("Start Date");
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.fill = GridBagConstraints.VERTICAL;
		gbc_lblStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDate.gridx = 0;
		gbc_lblStartDate.gridy = 1;
		panel.add(lblStartDate, gbc_lblStartDate);
		lblStartDate.setFont(new Font("Rockwell", Font.PLAIN, 13));
		
		startDateTextField = new JTextField();
		GridBagConstraints gbc_startDateTextField = new GridBagConstraints();
		gbc_startDateTextField.fill = GridBagConstraints.BOTH;
		gbc_startDateTextField.insets = new Insets(0, 0, 5, 5);
		gbc_startDateTextField.gridx = 1;
		gbc_startDateTextField.gridy = 1;
		panel.add(startDateTextField, gbc_startDateTextField);
		startDateTextField.setColumns(10);
		
		JLabel dateIconStart = new JLabel("");
		GridBagConstraints gbc_dateIconStart = new GridBagConstraints();
		gbc_dateIconStart.fill = GridBagConstraints.BOTH;
		gbc_dateIconStart.insets = new Insets(0, 0, 5, 0);
		gbc_dateIconStart.gridx = 2;
		gbc_dateIconStart.gridy = 1;
		panel.add(dateIconStart, gbc_dateIconStart);
		dateIconStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dateChooser = new DateChooser(startDateTextField);
				dateChooser.setFrame(new CustomDialog());
			}
		});
		dateIconStart.setIcon(calenderIcon);
		JLabel lblEndDate = new JLabel("End Date");
		GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
		gbc_lblEndDate.fill = GridBagConstraints.VERTICAL;
		gbc_lblEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndDate.gridx = 0;
		gbc_lblEndDate.gridy = 2;
		panel.add(lblEndDate, gbc_lblEndDate);
		lblEndDate.setFont(new Font("Rockwell", Font.PLAIN, 13));
		
		endDateTextField = new JTextField();
		GridBagConstraints gbc_endDateTextField = new GridBagConstraints();
		gbc_endDateTextField.fill = GridBagConstraints.BOTH;
		gbc_endDateTextField.insets = new Insets(0, 0, 5, 5);
		gbc_endDateTextField.gridx = 1;
		gbc_endDateTextField.gridy = 2;
		panel.add(endDateTextField, gbc_endDateTextField);
		
				endDateTextField.setColumns(10);
				
				JLabel DateIconEnd = new JLabel("");
				GridBagConstraints gbc_DateIconEnd = new GridBagConstraints();
				gbc_DateIconEnd.fill = GridBagConstraints.VERTICAL;
				gbc_DateIconEnd.insets = new Insets(0, 0, 5, 0);
				gbc_DateIconEnd.gridx = 2;
				gbc_DateIconEnd.gridy = 2;
				panel.add(DateIconEnd, gbc_DateIconEnd);
				DateIconEnd.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dateChooser = new DateChooser(endDateTextField);
						dateChooser.setFrame(new CustomDialog());
					}
				});
				DateIconEnd.setIcon(calenderIcon);
				JLabel lblLeader = new JLabel("Leader");
				GridBagConstraints gbc_lblLeader = new GridBagConstraints();
				gbc_lblLeader.fill = GridBagConstraints.VERTICAL;
				gbc_lblLeader.insets = new Insets(0, 0, 0, 5);
				gbc_lblLeader.gridx = 0;
				gbc_lblLeader.gridy = 3;
				panel.add(lblLeader, gbc_lblLeader);
				lblLeader.setFont(new Font("Rockwell", Font.PLAIN, 13));
				
				projectLeaderSelector = new JComboBox<String>();
				GridBagConstraints gbc_projectLeaderSelector = new GridBagConstraints();
				gbc_projectLeaderSelector.fill = GridBagConstraints.BOTH;
				gbc_projectLeaderSelector.gridwidth = 2;
				gbc_projectLeaderSelector.gridx = 1;
				gbc_projectLeaderSelector.gridy = 3;
				panel.add(projectLeaderSelector, gbc_projectLeaderSelector);
				projectLeaderSelector.setModel(new DefaultComboBoxModel<String>(allUserNames));
				
				JPanel panel_1 = new JPanel();
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridheight = 2;
				gbc_panel_1.insets = new Insets(0, 0, 5, 0);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 1;
				gbc_panel_1.gridy = 1;
				contentPanel.add(panel_1, gbc_panel_1);
				GridBagLayout gbl_panel_1 = new GridBagLayout();
				gbl_panel_1.columnWidths = new int[]{0, 0, 0};
				gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
				gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
				gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
				panel_1.setLayout(gbl_panel_1);
				
				JLabel lblEnterShortDescription = new JLabel("Enter short description");
				GridBagConstraints gbc_lblEnterShortDescription = new GridBagConstraints();
				gbc_lblEnterShortDescription.fill = GridBagConstraints.VERTICAL;
				gbc_lblEnterShortDescription.gridwidth = 2;
				gbc_lblEnterShortDescription.insets = new Insets(0, 0, 5, 0);
				gbc_lblEnterShortDescription.gridx = 0;
				gbc_lblEnterShortDescription.gridy = 0;
				panel_1.add(lblEnterShortDescription, gbc_lblEnterShortDescription);
				lblEnterShortDescription.setFont(new Font("Times", Font.PLAIN, 15));
				
				descriptionTextBox = new JTextArea();
				descriptionTextBox.setLineWrap(true);
				GridBagConstraints gbc_descriptionTextBox = new GridBagConstraints();
				gbc_descriptionTextBox.fill = GridBagConstraints.BOTH;
				gbc_descriptionTextBox.gridwidth = 2;
				gbc_descriptionTextBox.insets = new Insets(0, 0, 5, 0);
				gbc_descriptionTextBox.gridx = 0;
				gbc_descriptionTextBox.gridy = 1;
				panel_1.add(descriptionTextBox, gbc_descriptionTextBox);
				descriptionTextBox.setColumns(10);
				
				JButton cancelButton = new JButton("Cancel");
				GridBagConstraints gbc_cancelButton = new GridBagConstraints();
				gbc_cancelButton.fill = GridBagConstraints.BOTH;
				gbc_cancelButton.insets = new Insets(0, 0, 0, 5);
				gbc_cancelButton.gridx = 0;
				gbc_cancelButton.gridy = 2;
				panel_1.add(cancelButton, gbc_cancelButton);
				cancelButton.setFont(new Font("Lucida Console", Font.PLAIN, 13));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(frame != null)
							frame.close();
					}
				});
				
				JButton saveButton = new JButton("Save");
				GridBagConstraints gbc_saveButton = new GridBagConstraints();
				gbc_saveButton.fill = GridBagConstraints.BOTH;
				gbc_saveButton.gridx = 1;
				gbc_saveButton.gridy = 2;
				panel_1.add(saveButton, gbc_saveButton);
				saveButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						assembleProject();
						close();
					}
				});
	}	

	@Override
	public void setFrame(CustomFrame frame) {
		// TODO Auto-generated method stub
		this.frame = frame;
		frame.setWidget(this);
		frame.setWindowModality(true);
		frame.setResizeable(false);
		frame.ShowDialog();
	}
	
	@Override
	public void close() {
		
		frame.close();
	}
	
	public void assembleProject()
	{
		String projectTitle = projectNameSelector.getText(),startDate = startDateTextField.getText(),
				endDate = endDateTextField.getText(),
				pLeader = (String) projectLeaderSelector.getSelectedItem(),
				description = descriptionTextBox.getText();
		ProjectModel newProject = new ProjectModel(projectTitle,pLeader, startDate, endDate, description);
		try {
			service.addProject(newProject);
		} catch (Exception e) {
			return;
		}
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
