package forms;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import abstractions.IApplicationProgrammingInterface;
import models.ActivityModel;
import static_Domain.DateFormatizer;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class AbsentActivityDialog extends JPanel implements FrameImplementable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField titleSelector;
	private CustomFrame frame;
	private String title;
	private IApplicationProgrammingInterface service;
	private JTextField startDateField;
	private JTextField endDateField;
	private JComboBox<String> reasonSelector;

	/**
	 * Create the panel.
	 */
	public AbsentActivityDialog(IApplicationProgrammingInterface service) {
		this.service = service;
		initializeComponent();
		setTitle("Add absent activity");
	}
	
	public void initializeComponent()
	{
		setMinimumSize(new Dimension(200, 300));
		setPreferredSize(new Dimension(275, 400));
		setBackground(new Color(176, 224, 230));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblFillActivityOf = new JLabel("Fill activity of absence");
		lblFillActivityOf.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblFillActivityOf = new GridBagConstraints();
		gbc_lblFillActivityOf.insets = new Insets(5, 0, 5, 0);
		gbc_lblFillActivityOf.gridx = 0;
		gbc_lblFillActivityOf.gridy = 0;
		add(lblFillActivityOf, gbc_lblFillActivityOf);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 224, 230));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 5, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblTitle = new JLabel("Title");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		panel_1.add(lblTitle, gbc_lblTitle);
		
		titleSelector = new JTextField();
		titleSelector.setText("Your titel");
		GridBagConstraints gbc_titleSelector = new GridBagConstraints();
		gbc_titleSelector.insets = new Insets(0, 0, 5, 0);
		gbc_titleSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleSelector.gridx = 1;
		gbc_titleSelector.gridy = 0;
		panel_1.add(titleSelector, gbc_titleSelector);
		titleSelector.setColumns(10);
		
		JLabel lblStartDate = new JLabel("Start date");
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.anchor = GridBagConstraints.WEST;
		gbc_lblStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDate.gridx = 0;
		gbc_lblStartDate.gridy = 1;
		panel_1.add(lblStartDate, gbc_lblStartDate);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		panel_1.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		startDateField = new JTextField();
		GridBagConstraints gbc_startDateField = new GridBagConstraints();
		gbc_startDateField.insets = new Insets(0, 0, 0, 5);
		gbc_startDateField.fill = GridBagConstraints.HORIZONTAL;
		gbc_startDateField.gridx = 0;
		gbc_startDateField.gridy = 0;
		panel.add(startDateField, gbc_startDateField);
		startDateField.setColumns(10);
		
		JLabel startDateIcon = new JLabel("");
		startDateIcon.setIcon(new ImageIcon(".\\Ressource\\calendericon.png"));
		startDateIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DateChooser dC = new DateChooser(startDateField);
				dC.setFrame(new CustomDialog());
			}
		});
		GridBagConstraints gbc_startDateIcon = new GridBagConstraints();
		gbc_startDateIcon.gridx = 1;
		gbc_startDateIcon.gridy = 0;
		panel.add(startDateIcon, gbc_startDateIcon);
		
		JLabel lblEndDate = new JLabel("End date");
		GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
		gbc_lblEndDate.anchor = GridBagConstraints.WEST;
		gbc_lblEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndDate.gridx = 0;
		gbc_lblEndDate.gridy = 2;
		panel_1.add(lblEndDate, gbc_lblEndDate);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 2;
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		endDateField = new JTextField();
		GridBagConstraints gbc_endDateField = new GridBagConstraints();
		gbc_endDateField.insets = new Insets(0, 0, 0, 5);
		gbc_endDateField.fill = GridBagConstraints.HORIZONTAL;
		gbc_endDateField.gridx = 0;
		gbc_endDateField.gridy = 0;
		panel_2.add(endDateField, gbc_endDateField);
		endDateField.setColumns(10);
		
		JLabel endDateIcon = new JLabel("");
		endDateIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DateChooser dC = new DateChooser(endDateField);
				dC.setFrame(new CustomDialog());
			}
		});
		endDateIcon.setIcon(new ImageIcon("./Ressource/calendericon.png"));
		GridBagConstraints gbc_endDateIcon = new GridBagConstraints();
		gbc_endDateIcon.gridx = 1;
		gbc_endDateIcon.gridy = 0;
		panel_2.add(endDateIcon, gbc_endDateIcon);
		
		JLabel lblReason = new JLabel("Reason");
		lblReason.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblReason = new GridBagConstraints();
		gbc_lblReason.anchor = GridBagConstraints.WEST;
		gbc_lblReason.insets = new Insets(0, 0, 5, 5);
		gbc_lblReason.gridx = 0;
		gbc_lblReason.gridy = 3;
		panel_1.add(lblReason, gbc_lblReason);
		
		reasonSelector = new JComboBox<String>();
		
		String[] possibleReasons = {"Sick", "Vacation", "No reason", "Suspended", "Other"};
		
		for(String reason : possibleReasons)
			reasonSelector.addItem(reason);
		
		GridBagConstraints gbc_reasonSelector = new GridBagConstraints();
		gbc_reasonSelector.insets = new Insets(0, 0, 5, 0);
		gbc_reasonSelector.fill = GridBagConstraints.HORIZONTAL;
		gbc_reasonSelector.gridx = 1;
		gbc_reasonSelector.gridy = 3;
		panel_1.add(reasonSelector, gbc_reasonSelector);
		
		JTextArea txtrDescriptionbox = new JTextArea();
		txtrDescriptionbox.setLineWrap(true);
		txtrDescriptionbox.setText("Enter details here.");
		GridBagConstraints gbc_txtrDescriptionbox = new GridBagConstraints();
		gbc_txtrDescriptionbox.gridwidth = 2;
		gbc_txtrDescriptionbox.fill = GridBagConstraints.BOTH;
		gbc_txtrDescriptionbox.gridx = 0;
		gbc_txtrDescriptionbox.gridy = 4;
		panel_1.add(txtrDescriptionbox, gbc_txtrDescriptionbox);
		
		JPanel buttonGroup = new JPanel();
		GridBagConstraints gbc_buttonGroup = new GridBagConstraints();
		gbc_buttonGroup.insets = new Insets(0, 0, 5, 0);
		gbc_buttonGroup.fill = GridBagConstraints.VERTICAL;
		gbc_buttonGroup.gridx = 0;
		gbc_buttonGroup.gridy = 2;
		add(buttonGroup, gbc_buttonGroup);
		buttonGroup.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		buttonGroup.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				assembleActivityOfAbsence();
				close();
			}
		});
		buttonGroup.add(btnSave);
		
	}
	
	public void assembleActivityOfAbsence()
	{
		LocalDate sDate = DateFormatizer.dateFromString(startDateField.getText()),
				eDate = DateFormatizer.dateFromString(endDateField.getText());
		
		ActivityModel absence = new ActivityModel(titleSelector.getText(), 
				(String) reasonSelector.getSelectedItem(), 
				sDate, 
				eDate, service.currentUserLoggedIn().UserName());
		
		service.addAbsenceActivity(absence);
	}

	@Override
	public void setFrame(CustomFrame frame) {
		this.frame = frame;
		this.frame.setWidget(this);
		this.frame.setResizeable(false);
		this.frame.setWindowModality(true);
		this.frame.ShowDialog();
	}
	
	

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String title() {
		return title;
	}

	@Override
	public void close() {
		frame.close();
	}

}
