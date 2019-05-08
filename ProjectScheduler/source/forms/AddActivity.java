package forms;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Application_Facade.ApplicationFrontEnd;
import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import abstractions.IApplicationProgrammingInterface;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import formComponents.CustomTable;
import formComponents.CustomTableComponent;
import java.awt.Dimension;
import javax.swing.JButton;

public class AddActivity extends JPanel implements FrameImplementable{

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	ApplicationFrontEnd parent;
	CustomFrame frame = null;
	private IApplicationProgrammingInterface service;
	private JTextArea textField_1;


	public AddActivity(IApplicationProgrammingInterface service) {
		setPreferredSize(new Dimension(800, 520));
		initialize();
		this.service = service;
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
		
		textField_2 = new JTextField();
		textField_2.setBounds(326, 55, 110, 26);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(326, 102, 110, 26);
		textField_3.setColumns(10);
		
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(105, 103, 122, 27);
		
		JPanel panel = new JPanel();
		panel.setBounds(9, 187, 785, 307);
		contentPanel.setLayout(null);
		contentPanel.add(lblNewLabel);
		contentPanel.add(lblFillInInformation);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel(">>");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(373, 118, 40, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("<<");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(373, 146, 38, 16);
		panel.add(label_2);
		
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
		
		CustomTableComponent panel_1 = new CustomTableComponent();
		panel_1.setBounds(8, 25, 363, 276);
		panel.add(panel_1);
		
		String[] labels = {"Col1", "Col2", "Col3"};
		panel_1.setHeaderLabels(labels);
		
		CustomTableComponent panel_2 = new CustomTableComponent();
		panel_2.setBounds(414, 25, 363, 276);
		panel.add(panel_2);
		
		panel_2.setHeaderLabels(labels);
		
		contentPanel.add(lblActivity);
		contentPanel.add(lblProject);
		contentPanel.add(comboBox);
		contentPanel.add(textField);
		contentPanel.add(lblStart);
		contentPanel.add(textField_3);
		contentPanel.add(lblEndWeek);
		contentPanel.add(textField_2);
		
		Icon calenderIcon = new ImageIcon("./Ressource/calendericon.png");
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DateChooser dateDialog = new DateChooser(contentPanel, textField_2);
				dateDialog.setFrame(new CustomWidgetFrame());
			}
		});
		lblNewLabel_1.setIcon(calenderIcon);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(432, 58, 20, 20);
		contentPanel.add(lblNewLabel_1);
		
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DateChooser dateDialog = new DateChooser(contentPanel, textField_3);
				dateDialog.setFrame(new CustomWidgetFrame());
			}
		});
		label.setIcon(calenderIcon);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(432, 119, 20, 20);
		contentPanel.add(label);
		
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
