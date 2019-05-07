package forms;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import Application_Facade.ApplicationFrontEnd;
import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import abstractions.IApplicationProgrammingInterface;
import formComponents.CustomTable;
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
import javax.swing.JTable;

public class ProjectDialog extends JPanel implements FrameImplementable {

	private static final long serialVersionUID = -6266910851826102840L;
	CustomFrame frame = null;
	
	ApplicationFrontEnd parent;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblUsername;
	private JButton btnLogin;
	private JTable table;
	private IApplicationProgrammingInterface service;
	
	public ProjectDialog(IApplicationProgrammingInterface service) {
		this.parent = parent;

		setPreferredSize(new Dimension(700, 370));
		setBorder(null);
		
		setBackground(new Color(160, 82, 45));
		
		
		setBackground(new Color(176, 196, 222));
		
		setBounds(100, 100, 334, 270);
		setLayout(null);
		contentPanel.setBounds(22, 37, 655, 292);
		contentPanel.setBackground(new Color(176, 196, 222));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel);
		contentPanel.setLayout(null);
		{
			table = new CustomTable();
			table.setBounds(6, 6, 643, 282);
			contentPanel.add(table);
		}
		{
			lblUsername = new JLabel("Project Overview");
			lblUsername.setBounds(252, 6, 212, 32);
			add(lblUsername);
			lblUsername.setForeground(Color.DARK_GRAY);
			lblUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		}
		
		this.service = service;
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
