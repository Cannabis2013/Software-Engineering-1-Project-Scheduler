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
import formComponents.CustomTable;
import formComponents.CustomTableComponent;

public class AddActivity extends JPanel implements FrameImplementable{

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	ApplicationFrontEnd parent;
	CustomFrame frame = null;
	private IApplicationProgrammingInterface service;


	public AddActivity(IApplicationProgrammingInterface service) {
		
		initialize();
		setVisible(true);
		this.service = service;
	}
	

	public void initialize() {
		setBounds(100, 100, 700, 472);
		setLayout(null);
		contentPanel.setBounds(0, 0, 700, 450);
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setForeground(new Color(135, 206, 235));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel);
		JLabel lblActivity = new JLabel("Activity ID");
		lblActivity.setBounds(41, 54, 84, 26);
		textField = new JTextField();
		textField.setBounds(137, 54, 154, 26);
		textField.setColumns(10);
		JLabel lblStart = new JLabel("End week");
		lblStart.setBounds(384, 86, 110, 26);
		JLabel lblEndWeek = new JLabel("Start week");
		lblEndWeek.setBounds(384, 54, 84, 26);
		
		textField_2 = new JTextField();
		textField_2.setBounds(476, 54, 130, 26);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(476, 87, 130, 26);
		textField_3.setColumns(10);
		
		JLabel lblProject = new JLabel("Project");
		lblProject.setBounds(51, 92, 43, 16);
		
		JLabel lblNewLabel = new JLabel("Assign users to activity");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(5, 124, 689, 20);
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblFillInInformation = new JLabel("Fill in information");
		lblFillInInformation.setBounds(5, 16, 689, 20);
		lblFillInInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblFillInInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblFillInInformation.setBackground(Color.BLACK);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(137, 85, 154, 27);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 156, 690, 283);
		contentPanel.setLayout(null);
		contentPanel.add(lblNewLabel);
		contentPanel.add(lblFillInInformation);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel(">>");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(313, 120, 61, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("<<");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(313, 145, 61, 16);
		panel.add(label_2);
		
		JLabel lblAvailableUsers = new JLabel("Available Users");
		lblAvailableUsers.setForeground(new Color(0, 128, 0));
		lblAvailableUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvailableUsers.setBounds(98, 6, 120, 16);
		panel.add(lblAvailableUsers);
		
		JLabel lblAssignedUsers = new JLabel("Assigned Users");
		lblAssignedUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssignedUsers.setForeground(Color.RED);
		lblAssignedUsers.setBounds(468, 6, 120, 16);
		panel.add(lblAssignedUsers);
		
		CustomTableComponent panel_1 = new CustomTableComponent();
		panel_1.setBounds(10, 25, 316, 254);
		panel.add(panel_1);
		
		String[] labels = {"Col1", "Col2", "Col3"};
		panel_1.setHeaderLabels(labels);
		
		CustomTableComponent panel_2 = new CustomTableComponent();
		panel_2.setBounds(363, 25, 306, 254);
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
		lblNewLabel_1.setBounds(605, 59, 16, 16);
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
		label.setBounds(605, 92, 16, 16);
		contentPanel.add(label);
		setPreferredSize(getSize());
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
		// TODO Auto-generated method stub
		
	}
}
