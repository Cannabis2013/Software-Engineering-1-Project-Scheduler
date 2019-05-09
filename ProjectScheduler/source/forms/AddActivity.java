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
import models.ItemModel;
import static_Domain.DateFormatizer;

import java.awt.Dimension;
import javax.swing.JButton;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.beans.PropertyChangeEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class AddActivity extends JPanel implements FrameImplementable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField startWeekTextBox;
	private JTextField endWeekTextBox;
	private JLabel addUserLinkLabel, removeUserLinkLabel;
	private CustomTableComponent availableUserView,assignedUserView;
	private CustomFrame frame = null;
	private IApplicationProgrammingInterface service;
	private JTextArea textField_1;

	public AddActivity(IApplicationProgrammingInterface service) {
		this.service = service;
		setPreferredSize(new Dimension(800, 520));
		initialize();
		availableUserView.addItems(this.service.userListModels());
		initializeViews();
	}
	

	public void initialize() {
		setBounds(100, 100, 800, 520);
		setBackground(new Color(176, 224, 230));
		setLayout(null);
		contentPanel.setBounds(0, 0, 800, 500);
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setForeground(new Color(135, 206, 235));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel);
		JLabel lblActivity = new JLabel("Activity Title");
		lblActivity.setBounds(15, 55, 84, 26);
		textField = new JTextField();
		textField.setBounds(105, 55, 122, 26);
		textField.setColumns(10);
		JLabel lblStart = new JLabel("End week");
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart.setBounds(239, 102, 90, 26);
		JLabel lblEndWeek = new JLabel("Start week");
		lblEndWeek.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndWeek.setBounds(239, 55, 90, 26);
		
		startWeekTextBox = new JTextField();
		startWeekTextBox.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				initializeViews();
			}
		});
		
		startWeekTextBox.setEditable(false);
		startWeekTextBox.setBounds(326, 55, 110, 26);
		startWeekTextBox.setColumns(10);
		
		endWeekTextBox = new JTextField();
		endWeekTextBox.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				initializeViews();
			}
		});
		endWeekTextBox.setEditable(false);
		endWeekTextBox.setBounds(326, 102, 110, 26);
		endWeekTextBox.setColumns(10);
		
		JLabel lblProject = new JLabel("Project ID");
		lblProject.setHorizontalAlignment(SwingConstants.CENTER);
		lblProject.setBounds(15, 107, 78, 16);
		
		JLabel lblNewLabel = new JLabel("Assign users to activity");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(15, 160, 774, 20);
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblFillInInformation = new JLabel("Fill in information");
		lblFillInInformation.setBounds(15, 16, 764, 20);
		lblFillInInformation.setHorizontalAlignment(SwingConstants.LEFT);
		lblFillInInformation.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		lblFillInInformation.setBackground(Color.BLACK);
		
		JComboBox<?> comboBox = new JComboBox<Object>();
		comboBox.setBounds(105, 103, 122, 27);
		
		JPanel panel = new JPanel();
		panel.setBounds(9, 187, 785, 307);
		contentPanel.setLayout(null);
		contentPanel.add(lblNewLabel);
		contentPanel.add(lblFillInInformation);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		addUserLinkLabel = new JLabel(">>");
		
		addUserLinkLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addUserLinkLabel.setBounds(373, 118, 40, 16);
		panel.add(addUserLinkLabel);
		
		removeUserLinkLabel = new JLabel("<<");
		removeUserLinkLabel.setHorizontalAlignment(SwingConstants.CENTER);
		removeUserLinkLabel.setBounds(373, 146, 38, 16);
		panel.add(removeUserLinkLabel);
		
		JLabel lblAvailableUsers = new JLabel("Available Users");
		lblAvailableUsers.setForeground(new Color(0, 128, 0));
		lblAvailableUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvailableUsers.setBounds(5, 6, 360, 16);
		panel.add(lblAvailableUsers);
		
		JLabel lblAssignedUsers = new JLabel("Assigned Users");
		lblAssignedUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssignedUsers.setForeground(Color.RED);
		lblAssignedUsers.setBounds(419, 6, 360, 16);
		panel.add(lblAssignedUsers);
		
		availableUserView = new CustomTableComponent();
		availableUserView.setBounds(8, 25, 363, 276);
		panel.add(availableUserView);
		
		String[] labels = {"Username", "Role", "Availability"};
		availableUserView.setHeaderLabels(labels);
		
		assignedUserView = new CustomTableComponent();
		assignedUserView.setBounds(414, 25, 363, 276);
		panel.add(assignedUserView);
		
		assignedUserView.setHeaderLabels(labels);
		
		contentPanel.add(lblActivity);
		contentPanel.add(lblProject);
		contentPanel.add(comboBox);
		contentPanel.add(textField);
		contentPanel.add(lblStart);
		contentPanel.add(endWeekTextBox);
		contentPanel.add(lblEndWeek);
		contentPanel.add(startWeekTextBox);
		
		Icon calenderIcon = new ImageIcon("./Ressource/calendericon.png");
		JLabel startDateSelectorDialog = new JLabel("");
		startDateSelectorDialog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DateChooser dateDialog = new DateChooser(contentPanel, startWeekTextBox);
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
				DateChooser dateDialog = new DateChooser(contentPanel, endWeekTextBox);
				dateDialog.setFrame(new CustomWidgetFrame());
			}
		});
		endDateSelectorDialog.setIcon(calenderIcon);
		endDateSelectorDialog.setHorizontalAlignment(SwingConstants.CENTER);
		endDateSelectorDialog.setBounds(432, 107, 20, 20);
		contentPanel.add(endDateSelectorDialog);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(662, 54, 117, 29);
		contentPanel.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				close();
			}
		});
		btnCancel.setBounds(662, 102, 117, 29);
		contentPanel.add(btnCancel);
		
		textField_1 = new JTextArea();
		textField_1.setBounds(479, 54, 175, 88);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_3 = new JLabel("Short Description");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(479, 31, 171, 26);
		contentPanel.add(label_3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLACK);
		panel_3.setBounds(0, 154, 800, 2);
		contentPanel.add(panel_3);
	}

	public void initializeViews()
	{
		LocalDate sDate = null, eDate = null;
		try {
			sDate = DateFormatizer.dateFromString(startWeekTextBox.getText());
		} catch (DateTimeParseException e) {
			return;
		}
		
		try {
			eDate = DateFormatizer.dateFromString(endWeekTextBox.getText());
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
}
