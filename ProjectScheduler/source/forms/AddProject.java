package forms;

import java.awt.BorderLayout;
import Application_Facade.ApplicationFrontEnd;
import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import abstractions.IApplicationProgrammingInterface;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
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

public class AddProject extends JDialog implements FrameImplementable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private DateChooser dateChooser;
	private CustomFrame frame = null;
	/**
	 * Create the dialog.
	 */
	public AddProject(IApplicationProgrammingInterface service) {
		initialize();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
		private void initialize() {
		setBounds(100, 100, 610, 316);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
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
		
		textField = new JTextField();
		textField.setBounds(146, 90, 176, 26);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(146, 130, 155, 26);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(146, 170, 155, 26);
		textField_2.setColumns(10);
		
		JLabel lblEnterShortDescription = new JLabel("Enter short description");
		lblEnterShortDescription.setBounds(345, 61, 141, 16);
		lblEnterShortDescription.setFont(new Font("Times", Font.PLAIN, 15));
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBounds(345, 252, 86, 29);
		btnNewButton.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(449, 252, 86, 29);
		btnSave.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		textField_4 = new JTextField();
		textField_4.setBounds(342, 89, 183, 145);
		textField_4.setColumns(10);
		contentPanel.setLayout(null);
		contentPanel.add(lblFillInInformation);
		contentPanel.add(lblEnterShortDescription);
		contentPanel.add(lblStartDate);
		contentPanel.add(lblEndDate);
		contentPanel.add(lblLeader);
		contentPanel.add(lblProjectId);
		contentPanel.add(textField);
		contentPanel.add(textField_2);
		contentPanel.add(textField_1);
		contentPanel.add(btnNewButton);
		contentPanel.add(btnSave);
		contentPanel.add(textField_4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dateChooser = new DateChooser(contentPanel, textField_1);
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
				dateChooser = new DateChooser(contentPanel, textField_2);
				dateChooser.setFrame(new CustomWidgetFrame());
			}
		});
		label.setIcon(calenderIcon);
		label.setBounds(298, 175, 16, 16);
		contentPanel.add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Clair", "David", "Martin", "Peter"}));
		comboBox.setBounds(146, 210, 176, 26);
		comboBox.addItem("clair");
		contentPanel.add(comboBox);
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
