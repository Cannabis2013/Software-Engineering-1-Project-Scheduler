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
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = -6266910851826102840L;
	IApplicationProgrammingInterface service;
	
	ApplicationFrontEnd parent;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel lblUsername;
	private JButton btnLogin;
	private JLabel lblNewLabel_1;
	private JLabel label;
	
	public LoginDialog(ApplicationFrontEnd parent, IApplicationProgrammingInterface service) {
		setUndecorated(true);
		setResizable(false);
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
			textField.setBackground(new Color(240, 248, 255));
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
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("./Ressource/logo.png"));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(29)
							.addComponent(lblUsername))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUsername)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnLogin))
					.addGap(7))
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
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setVgap(7);
			flowLayout.setHgap(10);
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panel.setBackground(new Color(0, 0, 128));
			panel.setPreferredSize(new Dimension(100, 30));
			panel.setMinimumSize(new Dimension(0, 0));
			getContentPane().add(panel, BorderLayout.NORTH);
			
			lblNewLabel_1 = new JLabel("X");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNewLabel_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.exit(0);
				}
			});
			lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setForeground(new Color(255, 255, 255));
			panel.add(lblNewLabel_1);
		}
		initializeComponents();
	}
	
	private void initializeComponents()
	{
		setTitle("Login dialog");
		this.setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
}
