package forms;


import javax.swing.JPanel;
import Application_Facade.ApplicationFrontEnd;
import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import abstractions.IApplicationProgrammingInterface;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import formComponents.CustomTableComponent;

public class ProjectDialog extends JPanel implements FrameImplementable {

	private static final long serialVersionUID = -6266910851826102840L;
	CustomFrame frame = null;
	
	ApplicationFrontEnd parent;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblUsername;
	private CustomTableComponent table;
	private IApplicationProgrammingInterface service;
	private String componentTitle;
	public ProjectDialog(IApplicationProgrammingInterface service) {
		this.service = service;
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
			table = new CustomTableComponent();
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


	@Override
	public void setTitle(String title) {
		componentTitle = title;
	}


	@Override
	public String title() {
		return componentTitle;
	}
}
