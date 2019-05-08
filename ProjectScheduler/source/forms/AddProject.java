package forms;

import java.awt.BorderLayout;
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

public class AddProject extends JPanel implements FrameImplementable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JTextField projectTitleTextField;
	private JTextField startDateTextField;
	private JTextField endDateTextField;
	private JTextField descriptionTextBox;
	private DateChooser dateChooser;
	private JComboBox<String> projectLeaderSelector;
	private CustomFrame frame = null;
	private IApplicationProgrammingInterface service;
	/**
	 * Create the dialog.
	 */
	public AddProject(IApplicationProgrammingInterface service) {
		this.service = service;
		initialize();
	}
	
		private void initialize() {
		setBounds(100, 100, 610, 316);
		setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		JLabel lblFillInInformation = new JLabel("Fill in information");
		lblFillInInformation.setBounds(58, 28, 184, 39);
		lblFillInInformation.setFont(new Font("Rockwell", Font.PLAIN, 21));
		JLabel lblProjectId = new JLabel("Project ID");
		lblProjectId.setBounds(58, 94, 70, 24);
		lblProjectId.setFont(new Font("Rockwell", Font.PLAIN, 13));
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(58, 134, 63, 22);
		lblStartDate.setFont(new Font("Rockwell", Font.PLAIN, 13));
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(58, 174, 60, 22);
		lblEndDate.setFont(new Font("Rockwell", Font.PLAIN, 13));
		JLabel lblLeader = new JLabel("Leader");
		lblLeader.setBounds(58, 214, 60, 24);
		lblLeader.setFont(new Font("Rockwell", Font.PLAIN, 13));
		
		projectTitleTextField = new JTextField();
		projectTitleTextField.setBounds(146, 90, 134, 26);
		projectTitleTextField.setColumns(10);
		
		startDateTextField = new JTextField();
		startDateTextField.setBounds(146, 130, 134, 26);
		startDateTextField.setColumns(10);
		
		endDateTextField = new JTextField();
		endDateTextField.setBounds(146, 170, 134, 26);
		endDateTextField.setColumns(10);
		
		JLabel lblEnterShortDescription = new JLabel("Enter short description");
		lblEnterShortDescription.setBounds(345, 61, 141, 16);
		lblEnterShortDescription.setFont(new Font("Times", Font.PLAIN, 15));
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(345, 252, 86, 29);
		cancelButton.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(frame != null)
					frame.close();
			}
		});
		
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(449, 252, 86, 29);
		saveButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				assembleProject();
				close();
			}
		});
		
		descriptionTextBox = new JTextField();
		descriptionTextBox.setBounds(342, 89, 183, 145);
		descriptionTextBox.setColumns(10);
		contentPanel.setLayout(null);
		contentPanel.add(lblFillInInformation);
		contentPanel.add(lblEnterShortDescription);
		contentPanel.add(lblStartDate);
		contentPanel.add(lblEndDate);
		contentPanel.add(lblLeader);
		contentPanel.add(lblProjectId);
		contentPanel.add(projectTitleTextField);
		contentPanel.add(endDateTextField);
		contentPanel.add(startDateTextField);
		contentPanel.add(cancelButton);
		contentPanel.add(saveButton);
		contentPanel.add(descriptionTextBox);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dateChooser = new DateChooser(contentPanel, startDateTextField);
				dateChooser.setFrame(new CustomWidgetFrame());
			}
		});
		Icon calenderIcon = new ImageIcon("./Ressource/calendericon.png");
		lblNewLabel.setIcon(calenderIcon);
		lblNewLabel.setBounds(298, 135, 16, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dateChooser = new DateChooser(contentPanel, endDateTextField);
				dateChooser.setFrame(new CustomWidgetFrame());
			}
		});
		label.setIcon(calenderIcon);
		label.setBounds(298, 175, 16, 16);
		contentPanel.add(label);
		
		projectLeaderSelector = new JComboBox<String>();
		String[] allUserNames = service.allUserNames();
		projectLeaderSelector.setModel(new DefaultComboBoxModel<String>(allUserNames));
		projectLeaderSelector.setBounds(146, 210, 176, 26);
		contentPanel.add(projectLeaderSelector);
		setPreferredSize(getSize());
	}

	@Override
	public void setFrame(CustomFrame frame) {
		// TODO Auto-generated method stub
		this.frame = frame;
		frame.setWidget(this);
		frame.setWindowModality(true);
		frame.ShowDialog();
	}

	@Override
	public void close() {
		
		frame.close();
	}
	
	public void assembleProject()
	{
		String pTitle = projectTitleTextField.getText(),
				startDate = startDateTextField.getText(),
				endDate = endDateTextField.getText(),
				pLeader = (String) projectLeaderSelector.getSelectedItem(),
				description = descriptionTextBox.getText();
		ProjectModel newProject = new ProjectModel(pTitle, pLeader, startDate, endDate, description);
		try {
			service.addProject(newProject);
		} catch (Exception e) {
			return;
		}
	}

}
