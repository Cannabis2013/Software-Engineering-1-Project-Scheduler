package forms;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import Application_Facade.ApplicationFrontEnd;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.event.KeyEvent;

import Abstractions.IApplicationProgrammingInterface;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = -6266910851826102840L;
	IApplicationProgrammingInterface service;
	
	ApplicationFrontEnd parent;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel lblUsername;
	private JButton btnLogin;
	
	public LoginDialog(ApplicationFrontEnd parent, IApplicationProgrammingInterface service) {
		setBackground(new Color(160, 82, 45));
		
		this.service = service;
		
		getContentPane().setBackground(new Color(176, 196, 222));
		
		
		this.parent = parent;
		setBounds(100, 100, 300, 270);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 196, 222));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblUsername = new JLabel("Username");
			lblUsername.setForeground(new Color(255, 255, 255));
			lblUsername.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		}
		{

			textField = new JTextField();
			textField.setColumns(10);

			textField.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(java.awt.event.KeyEvent arg0) {
					
				}
				
				@Override
				public void keyReleased(java.awt.event.KeyEvent arg0) {
					// TODO Auto-generated method stub
				
				}
				
				@Override
				public void keyPressed(java.awt.event.KeyEvent arg0) {
					if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
					{
						try {
							service.login(textField.getText());
						} catch (Exception e) {
							return;
						}
						parent.launchMainView();
						dispose();
					}
						
				}
			});
		}
		{
			btnLogin = new JButton("Login");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						service.login(textField.getText());
						parent.launchMainView();
						dispose();
					} catch (Exception e1) {
						
					}
				}
			});
		}
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("./Ressource/logo.png"));
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(108)
							.addComponent(lblUsername))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(8)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUsername)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogin)
					.addGap(47))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{0};
			gbl_buttonPane.rowHeights = new int[]{0};
			gbl_buttonPane.columnWeights = new double[]{Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
		}
	}
	
	private void initializeComponents()
	{
		setTitle("Login dialog");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
}
