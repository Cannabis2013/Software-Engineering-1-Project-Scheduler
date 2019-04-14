package forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Application_Facade.ApplicationFacade;

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

public class LoginDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6266910851826102840L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	
	ApplicationFacade parent;
	
	public LoginDialog(ApplicationFacade parent) {
		
		
		this.parent = parent;
		initializeComponents();
		
	}
	
	private void initializeComponents()
	{
		setTitle("Login dialog");
		setResizable(false);
		setBounds(100, 100, 279, 297);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblWelcomePleaseEnter = new JLabel("Welcome, please enter your credential");
			lblWelcomePleaseEnter.setForeground(Color.WHITE);
			lblWelcomePleaseEnter.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lblWelcomePleaseEnter = new GridBagConstraints();
			gbc_lblWelcomePleaseEnter.gridwidth = 2;
			gbc_lblWelcomePleaseEnter.insets = new Insets(0, 0, 5, 0);
			gbc_lblWelcomePleaseEnter.gridx = 0;
			gbc_lblWelcomePleaseEnter.gridy = 0;
			contentPanel.add(lblWelcomePleaseEnter, gbc_lblWelcomePleaseEnter);
		}
		{
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblUsername = new GridBagConstraints();
			gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
			gbc_lblUsername.gridx = 0;
			gbc_lblUsername.gridy = 1;
			contentPanel.add(lblUsername, gbc_lblUsername);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 1;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Close");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton okButton = new JButton("Log in");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						parent.validateLogin(textField.getText());
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

}
