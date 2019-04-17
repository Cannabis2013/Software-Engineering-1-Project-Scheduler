package forms;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import Application_Facade.ApplicationFrontEnd;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import java.awt.GridLayout;

public class LoginDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6266910851826102840L;
	
	ApplicationFrontEnd parent;
	private JTextField textField;
	
	public LoginDialog(ApplicationFrontEnd parent) {
		getContentPane().setBackground(new Color(0, 0, 0));
		
		
		this.parent = parent;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{80, 193, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JLabel lblEnterYourCredential = new JLabel("Enter your credential:");
			lblEnterYourCredential.setForeground(new Color(255, 255, 255));
			lblEnterYourCredential.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GridBagConstraints gbc_lblEnterYourCredential = new GridBagConstraints();
			gbc_lblEnterYourCredential.gridwidth = 2;
			gbc_lblEnterYourCredential.insets = new Insets(0, 0, 5, 0);
			gbc_lblEnterYourCredential.gridx = 0;
			gbc_lblEnterYourCredential.gridy = 0;
			getContentPane().add(lblEnterYourCredential, gbc_lblEnterYourCredential);
		}
		{
			JLabel lblUsername = new JLabel("Username:");
			lblUsername.setForeground(new Color(255, 255, 255));
			GridBagConstraints gbc_lblUsername = new GridBagConstraints();
			gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
			gbc_lblUsername.gridx = 0;
			gbc_lblUsername.gridy = 1;
			getContentPane().add(lblUsername, gbc_lblUsername);
		}
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon("C:\\Users\\Martin\\git\\ProjectScheduler\\ProjectScheduler\\Ressource\\paagen kanelgifler.jpg"));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.NORTH;
			gbc_label.gridwidth = 2;
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 2;
			getContentPane().add(label, gbc_label);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.anchor = GridBagConstraints.SOUTH;
			gbc_panel.gridwidth = 2;
			gbc_panel.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 3;
			getContentPane().add(panel, gbc_panel);
			panel.setLayout(new GridLayout(1, 0, 0, 0));
			{
				JButton btnNewButton = new JButton("Cancel");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				panel.add(btnNewButton);
			}
			{
				JButton btnNewButton_1 = new JButton("Ok");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						parent.validateLogin(textField.getText());
						dispose();
					}
				});
				panel.add(btnNewButton_1);
			}
		}
		initializeComponents();
		
	}
	
	private void initializeComponents()
	{
		setTitle("Login dialog");
		setResizable(false);
		setBounds(100, 100, 266, 383);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

}
