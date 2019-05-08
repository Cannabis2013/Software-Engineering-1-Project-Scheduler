package forms;

import java.awt.BorderLayout;
import Application_Facade.ApplicationFrontEnd;
import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import abstractions.IApplicationProgrammingInterface;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class RegisterHour extends JPanel implements FrameImplementable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField projectTitleTextField;
	private JTextField endDateTextField;
	private JTextArea descriptionTextBox;
	private DateChooser dateChooser;
	private CustomFrame frame = null;
	/**
	 * Create the dialog.
	 */
	public RegisterHour(IApplicationProgrammingInterface service) {
		initialize();
		
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
		
		projectTitleTextField = new JTextField();
		projectTitleTextField.setBounds(161, 90, 161, 26);
		projectTitleTextField.setColumns(10);
		
		endDateTextField = new JTextField();
		endDateTextField.setBounds(161, 190, 161, 26);
		endDateTextField.setColumns(10);
		
		JLabel lblEnterShortDescription = new JLabel("Enter short description");
		lblEnterShortDescription.setBounds(345, 61, 255, 16);
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
				
			}
		});
		
		descriptionTextBox = new JTextArea();
		descriptionTextBox.setBounds(342, 89, 258, 145);
		descriptionTextBox.setColumns(10);
		contentPanel.setLayout(null);
		contentPanel.add(lblFillInInformation);
		contentPanel.add(lblEnterShortDescription);
		contentPanel.add(lblStartDate);
		contentPanel.add(lblEndDate);
		contentPanel.add(lblProjectId);
		contentPanel.add(projectTitleTextField);
		contentPanel.add(endDateTextField);
		contentPanel.add(cancelButton);
		contentPanel.add(saveButton);
		contentPanel.add(descriptionTextBox);

		
		JComboBox projectLeaderSelector = new JComboBox();
		projectLeaderSelector.setModel(new DefaultComboBoxModel(new String[] {"Clair", "David", "Martin", "Peter"}));
		projectLeaderSelector.setBounds(161, 140, 161, 26);
		projectLeaderSelector.addItem("clair");
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

}
