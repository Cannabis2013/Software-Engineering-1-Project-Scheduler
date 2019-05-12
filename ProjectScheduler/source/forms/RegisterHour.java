package forms;

import java.awt.BorderLayout;
import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import abstractions.IApplicationProgrammingInterface;
import models.ActivityModel;
import models.HourRegistrationModel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class RegisterHour extends JPanel implements FrameImplementable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField titleTextbox;
	private JTextArea descriptionTextBox;
	private CustomFrame frame = null;
	private JSpinner hourSpinBox;
	private IApplicationProgrammingInterface service;
	private JComboBox<String> activitySelector;
	private List<ActivityModel> activities;
	private String componentTitle = "Register hour";
	private HourRegistrationModel regModel;
	private enum openMode {add,edit};
	private openMode mode;
	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public RegisterHour(IApplicationProgrammingInterface service) {
		this.service = service;
		initialize();
		mode = openMode.add;
	}
	
	public RegisterHour(IApplicationProgrammingInterface service, HourRegistrationModel regModel) {
		this.service = service;
		this.regModel = regModel;
		initialize();
		initializeSelectors();
		mode = openMode.edit;
	}
	
	private void initialize() {
		setBounds(100, 100, 610, 316);
		setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		JLabel lblFillInInformation = new JLabel("Register Hours");
		lblFillInInformation.setBounds(58, 28, 184, 39);
		lblFillInInformation.setFont(new Font("Rockwell", Font.PLAIN, 21));
		JLabel lblProjectId = new JLabel("Title");
		lblProjectId.setHorizontalAlignment(SwingConstants.CENTER);
		lblProjectId.setBounds(58, 93, 70, 24);
		lblProjectId.setFont(new Font("Rockwell", Font.PLAIN, 13));
		JLabel lblStartDate = new JLabel("Select Activity");
		lblStartDate.setBounds(58, 143, 91, 22);
		lblStartDate.setFont(new Font("Rockwell", Font.PLAIN, 13));
		JLabel lblEndDate = new JLabel("Total Hours");
		lblEndDate.setBounds(58, 194, 91, 22);
		lblEndDate.setFont(new Font("Rockwell", Font.PLAIN, 13));
		
		titleTextbox = new JTextField();
		titleTextbox.setBounds(161, 90, 161, 26);
		titleTextbox.setColumns(10);
		
		JLabel lblEnterShortDescription = new JLabel("Enter short description");
		lblEnterShortDescription.setBounds(345, 61, 255, 16);
		lblEnterShortDescription.setFont(new Font("Times", Font.PLAIN, 15));
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(345, 252, 86, 29);
		cancelButton.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(frame != null)
					close();
			}
		});
		
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(449, 252, 86, 29);
		saveButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mode == openMode.add)
					assembleRegistrationObject();
				else
					reAssembleRegistrationObject();
				close();
				
			}
		});
		
		descriptionTextBox = new JTextArea();
		descriptionTextBox.setLineWrap(true);
		descriptionTextBox.setBounds(342, 89, 258, 145);
		descriptionTextBox.setColumns(10);
		contentPanel.setLayout(null);
		contentPanel.add(lblFillInInformation);
		contentPanel.add(lblEnterShortDescription);
		contentPanel.add(lblStartDate);
		contentPanel.add(lblEndDate);
		contentPanel.add(lblProjectId);
		contentPanel.add(titleTextbox);
		contentPanel.add(cancelButton);
		contentPanel.add(saveButton);
		contentPanel.add(descriptionTextBox);

		
		activitySelector = new JComboBox<String>();
		
		activities = service.activities();
		String[] allActivityIdentitties = activities.stream().map(item -> item.activityId()).toArray(String[]::new);
		
		activitySelector.setModel(new DefaultComboBoxModel<String>(allActivityIdentitties));
		activitySelector.setBounds(161, 140, 161, 26);
		contentPanel.add(activitySelector);
		
		hourSpinBox = new JSpinner();
		hourSpinBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				Double number = (Double) hourSpinBox.getValue();
				
				if(number % 0.5 != 0.0)
				{
					int val = (int) number.doubleValue();
					hourSpinBox.setValue((double) val);
				}
			}
		});
		
		SpinnerNumberModel model = new SpinnerNumberModel(1.0, 0.5, 48.0, 0.5);
		hourSpinBox.setModel(model);
		
		hourSpinBox.setBounds(159, 194, 163, 22);
		contentPanel.add(hourSpinBox);
		setPreferredSize(getSize());
	}
	
	public void initializeSelectors()
	{
		titleTextbox.setText(regModel.registrationId());
		activitySelector.setSelectedItem(regModel.parentModelId());
		hourSpinBox.setValue(regModel.hours());
		descriptionTextBox.setText(regModel.description());
	}

	@Override
	public void setFrame(CustomFrame frame) {
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
	
	public void assembleRegistrationObject()
	{
		int currentIndex = activitySelector.getSelectedIndex();
		String title = titleTextbox.getText(), 
				description = descriptionTextBox.getText();
		service.currentUserLoggedIn().UserName();
		String activityId = activities.get(currentIndex).activityId(), projectId = activities.get(currentIndex).parentModelId();
		
		double hour = ((Double) hourSpinBox.getValue()).doubleValue();
		
		try {
			service.registerHour(projectId, activityId, title, hour, description);
		} catch (Exception e) {
			titleTextbox.setText("Choose another name. Duplicate.");
			return;
		}
		
	}
	
	public void reAssembleRegistrationObject()
	{
		int currentIndex = activitySelector.getSelectedIndex();
		String title = titleTextbox.getText(), 
				description = descriptionTextBox.getText();
		double hour = ((Double) hourSpinBox.getValue()).doubleValue();
		
		regModel.setRegistrationId(title);
		regModel.setParent(activities.get(currentIndex));
		regModel.setHours(hour);
		regModel.setDescription(description);
		service.requestUpdate();
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
