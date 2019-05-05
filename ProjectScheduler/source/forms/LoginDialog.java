package forms;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import Application_Facade.ApplicationFrontEnd;
import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import abstractions.IApplicationProgrammingInterface;
import sun.rmi.transport.proxy.CGIHandler;

import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.GridBagConstraints;

public class LoginDialog extends JPanel implements FrameImplementable {

	private static final long serialVersionUID = -6266910851826102840L;
	IApplicationProgrammingInterface service;
	CustomFrame frame = null;
	
	ApplicationFrontEnd parent;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel lblUsername;
	private JButton btnLogin;
	
	public LoginDialog(ApplicationFrontEnd parent, IApplicationProgrammingInterface service) {
		setPreferredSize(new Dimension(275, 320));
		setMinimumSize(new Dimension(300, 400));
		setMaximumSize(new Dimension(300, 400));
		setBorder(null);
		
		setBackground(new Color(160, 82, 45));
		
		this.service = service;
		
		setBackground(new Color(176, 196, 222));
		
		
		this.parent = parent;
		setBounds(100, 100, 334, 270);
		setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 196, 222));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{126, 126, 0};
		gbl_contentPanel.rowHeights = new int[]{134, 20, 20, 23, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
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
							close();
						} catch (Exception e) {
							return;
						}
						parent.launchMainView();
						
					}
						
				}
			});
		}
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("./Ressource/logo.png"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		{
			lblUsername = new JLabel("Username");
			lblUsername.setForeground(new Color(255, 255, 255));
			lblUsername.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		}
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.gridwidth = 2;
		gbc_lblUsername.anchor = GridBagConstraints.NORTH;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 1;
		contentPanel.add(lblUsername, gbc_lblUsername);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.anchor = GridBagConstraints.NORTH;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 2;
		contentPanel.add(textField, gbc_textField);
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.NORTH;
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 3;
		contentPanel.add(btnCancel, gbc_btnCancel);
		{
			btnLogin = new JButton("Login");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						service.login(textField.getText());
						parent.launchMainView();
						frame.close();
					} catch (Exception e1) {
						
					}
				}
			});
		}
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.NORTH;
		gbc_btnLogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 3;
		contentPanel.add(btnLogin, gbc_btnLogin);
		{
			JPanel buttonPane = new JPanel();
			add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{0};
			gbl_buttonPane.rowHeights = new int[]{0};
			gbl_buttonPane.columnWeights = new double[]{Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
		}
	}


	@Override
	public void setFrame(CustomFrame frame) {
		this.frame = frame;
		this.frame.setWidget(this);
		this.frame.ShowDialog();
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		frame.close();
	}
}
