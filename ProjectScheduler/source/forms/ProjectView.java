package forms;
import Application_Facade.ApplicationFrontEnd;
import abstractions.CustomFrame;
import formComponents.DateChooser;
import abstractions.FrameImplementable;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import formComponents.CustomTable;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ProjectView extends JPanel implements FrameImplementable {
	
	private ApplicationFrontEnd parent;
	private static final long serialVersionUID = 1L;
	CustomFrame frame;
	DateChooser dateChooser;
	private CustomTable table;
	private CustomTable table_1;
	private JTextField textField;
	
	public ProjectView(ApplicationFrontEnd parent) {
		setBorder(null);
		setPreferredSize(new Dimension(640, 400));
		setBackground(new Color(176, 224, 230));
		setBackground(Color.WHITE);
		setMinimumSize(new Dimension(640, 400));
		this.parent = parent;
		setFrame(new CustomWidgetFrame());
		initialize();
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 524, 495);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
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
				parent.launchAddProjectView();
			}
		});
		
		mnEdit.add(mntmAddProject);
		
		JMenuItem mntmAddActivity = new JMenuItem("Add Activity");
		mnEdit.add(mntmAddActivity);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{212, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(176, 224, 230));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 4;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{95, 0};
		gbl_panel.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 29, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblWelcomeText = new JLabel("Welcome text");
		lblWelcomeText.setForeground(Color.WHITE);
		lblWelcomeText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblWelcomeText = new GridBagConstraints();
		gbc_lblWelcomeText.insets = new Insets(0, 0, 5, 0);
		gbc_lblWelcomeText.fill = GridBagConstraints.BOTH;
		gbc_lblWelcomeText.gridx = 0;
		gbc_lblWelcomeText.gridy = 0;
		panel.add(lblWelcomeText, gbc_lblWelcomeText);
		
		textField = new JTextField();
		
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 2;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnTest = new JButton();
		btnTest.setFocusPainted(false);
		Icon calenderIcon = new ImageIcon("./Ressource/calendericon.png");
		btnTest.setIcon(calenderIcon);
		btnTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dateChooser = new DateChooser(panel, textField);
			}
		});
		GridBagConstraints gbc_btnTest = new GridBagConstraints();
		gbc_btnTest.insets = new Insets(0, 0, 5, 0);
		gbc_btnTest.gridx = 0;
		gbc_btnTest.gridy = 1;
		panel.add(btnTest, gbc_btnTest);
		
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 12;
		panel.add(verticalStrut, gbc_verticalStrut);
		
		JLabel lblManagement = new JLabel("Management");
		lblManagement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_lblManagement = new GridBagConstraints();
		gbc_lblManagement.gridx = 0;
		gbc_lblManagement.gridy = 13;
		panel.add(lblManagement, gbc_lblManagement);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 224, 230));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 4;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel = new JLabel("Assigned activities");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		lblNewLabel.setBorder(null);
		lblNewLabel.setBackground(Color.RED);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		table = new CustomTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 5, 0);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;
		panel_1.add(table, gbc_table);
		
		JLabel lblRegisteredHours = new JLabel("Registered hours");
		GridBagConstraints gbc_lblRegisteredHours = new GridBagConstraints();
		gbc_lblRegisteredHours.insets = new Insets(0, 0, 5, 0);
		gbc_lblRegisteredHours.gridx = 0;
		gbc_lblRegisteredHours.gridy = 2;
		panel_1.add(lblRegisteredHours, gbc_lblRegisteredHours);
		lblRegisteredHours.setForeground(Color.BLACK);
		lblRegisteredHours.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		table_1 = new CustomTable();
		GridBagConstraints gbc_table_1 = new GridBagConstraints();
		gbc_table_1.fill = GridBagConstraints.BOTH;
		gbc_table_1.gridx = 0;
		gbc_table_1.gridy = 3;
		panel_1.add(table_1, gbc_table_1);
	}

	@Override
	public void setFrame(CustomFrame frame) {
		this.frame = frame;
		this.frame.setWidget(this);
		this.frame.ShowDialog();
	}

	@Override
	public void close() {
		frame.close();
	}

}
