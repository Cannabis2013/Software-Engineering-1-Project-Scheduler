package forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Application_Facade.ApplicationFrontEnd;
import formComponents.DateChooser;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import formComponents.CustomTable;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

public class AddActivity extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private DateChooser dateChooser;
	ApplicationFrontEnd parent;
	private CustomTable table;
	private CustomTable table_1;


	public AddActivity(ApplicationFrontEnd parent) {
		this.parent = parent;
		initialize();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void initialize() {
		setBounds(100, 100, 755, 509);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setForeground(new Color(135, 206, 235));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{120, 154, 93, 84, 18, 111, 90, 0};
		gbl_contentPanel.rowHeights = new int[]{20, 26, 28, 20, 285, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel lblFillInInformation = new JLabel("Fill in information");
		lblFillInInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblFillInInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblFillInInformation.setBackground(Color.BLACK);
		GridBagConstraints gbc_lblFillInInformation = new GridBagConstraints();
		gbc_lblFillInInformation.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFillInInformation.insets = new Insets(0, 0, 5, 0);
		gbc_lblFillInInformation.gridwidth = 7;
		gbc_lblFillInInformation.gridx = 0;
		gbc_lblFillInInformation.gridy = 0;
		contentPanel.add(lblFillInInformation, gbc_lblFillInInformation);
		JLabel lblActivity = new JLabel("Activity ID");
		GridBagConstraints gbc_lblActivity = new GridBagConstraints();
		gbc_lblActivity.anchor = GridBagConstraints.EAST;
		gbc_lblActivity.fill = GridBagConstraints.VERTICAL;
		gbc_lblActivity.insets = new Insets(0, 0, 5, 5);
		gbc_lblActivity.gridx = 0;
		gbc_lblActivity.gridy = 1;
		contentPanel.add(lblActivity, gbc_lblActivity);
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		contentPanel.add(textField, gbc_textField);
		JLabel lblEndWeek = new JLabel("Start week");
		GridBagConstraints gbc_lblEndWeek = new GridBagConstraints();
		gbc_lblEndWeek.fill = GridBagConstraints.BOTH;
		gbc_lblEndWeek.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndWeek.gridx = 3;
		gbc_lblEndWeek.gridy = 1;
		contentPanel.add(lblEndWeek, gbc_lblEndWeek);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.gridwidth = 3;
		gbc_textField_2.gridx = 4;
		gbc_textField_2.gridy = 1;
		contentPanel.add(textField_2, gbc_textField_2);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dateChooser = new DateChooser(contentPanel, textField_2);
				dateChooser.setFrame(new CustomWidgetFrame());
				
				dateChooser.setVisible(true);
			}
		});
		//lblNewLabel_1.setIcon(calenderIcon);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 6;
		gbc_lblNewLabel_1.gridy = 1;
		contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblProject = new JLabel("Project");
		GridBagConstraints gbc_lblProject = new GridBagConstraints();
		gbc_lblProject.insets = new Insets(0, 0, 5, 5);
		gbc_lblProject.gridx = 0;
		gbc_lblProject.gridy = 2;
		contentPanel.add(lblProject, gbc_lblProject);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		contentPanel.add(comboBox, gbc_comboBox);
		JLabel lblStart = new JLabel("End week");
		GridBagConstraints gbc_lblStart = new GridBagConstraints();
		gbc_lblStart.fill = GridBagConstraints.BOTH;
		gbc_lblStart.insets = new Insets(0, 0, 5, 5);
		gbc_lblStart.gridx = 3;
		gbc_lblStart.gridy = 2;
		contentPanel.add(lblStart, gbc_lblStart);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.BOTH;
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.gridwidth = 3;
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 2;
		contentPanel.add(textField_3, gbc_textField_3);
		
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dateChooser = new DateChooser(contentPanel, textField_3);
			}
		});
		//label.setIcon(calenderIcon);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.fill = GridBagConstraints.VERTICAL;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 6;
		gbc_label.gridy = 2;
		contentPanel.add(label, gbc_label);
		
		JLabel lblNewLabel = new JLabel("Assign users to activity");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridwidth = 7;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 7;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		contentPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{212, 95, 13, 38, 10, 94, 216, 0};
		gbl_panel.rowHeights = new int[]{16, 111, 199, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblAvailableUsers = new JLabel("Available Users");
		lblAvailableUsers.setForeground(new Color(0, 128, 0));
		lblAvailableUsers.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblAvailableUsers = new GridBagConstraints();
		gbc_lblAvailableUsers.anchor = GridBagConstraints.EAST;
		gbc_lblAvailableUsers.fill = GridBagConstraints.VERTICAL;
		gbc_lblAvailableUsers.insets = new Insets(0, 0, 5, 5);
		gbc_lblAvailableUsers.gridx = 0;
		gbc_lblAvailableUsers.gridy = 0;
		panel.add(lblAvailableUsers, gbc_lblAvailableUsers);
		
		JLabel lblAssignedUsers = new JLabel("Assigned Users");
		lblAssignedUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssignedUsers.setForeground(Color.RED);
		GridBagConstraints gbc_lblAssignedUsers = new GridBagConstraints();
		gbc_lblAssignedUsers.anchor = GridBagConstraints.WEST;
		gbc_lblAssignedUsers.fill = GridBagConstraints.VERTICAL;
		gbc_lblAssignedUsers.insets = new Insets(0, 0, 5, 0);
		gbc_lblAssignedUsers.gridx = 6;
		gbc_lblAssignedUsers.gridy = 0;
		panel.add(lblAssignedUsers, gbc_lblAssignedUsers);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);
		
		table = new CustomTable();
		scrollPane.setViewportView(table);
		
		JLabel label_1 = new JLabel(">>");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.SOUTH;
		gbc_label_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridwidth = 3;
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 1;
		panel.add(label_1, gbc_label_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridheight = 2;
		gbc_scrollPane_1.gridwidth = 3;
		gbc_scrollPane_1.gridx = 4;
		gbc_scrollPane_1.gridy = 1;
		panel.add(scrollPane_1, gbc_scrollPane_1);
		
		table_1 = new CustomTable();
		scrollPane_1.setViewportView(table_1);
		
		JLabel label_2 = new JLabel("<<");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.NORTH;
		gbc_label_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_2.insets = new Insets(0, 0, 0, 5);
		gbc_label_2.gridwidth = 3;
		gbc_label_2.gridx = 2;
		gbc_label_2.gridy = 2;
		panel.add(label_2, gbc_label_2);
		
		Icon calenderIcon = new ImageIcon("./Ressource/calendericon.png");
	}
}
