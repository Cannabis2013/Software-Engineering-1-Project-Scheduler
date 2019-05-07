package forms;
import Application_Facade.ApplicationFrontEnd;
import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import formComponents.CustomTable;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

public class HourRegistration extends JPanel implements FrameImplementable {
	
	private ApplicationFrontEnd parent;
	private static final long serialVersionUID = 1L;
	CustomFrame frame;
	DateChooser dateChooser;
	private CustomTable table;
	
	public HourRegistration(ApplicationFrontEnd parent) {
		setForeground(Color.WHITE);
		setBorder(null);
		setPreferredSize(new Dimension(960, 540));
		setBackground(new Color(176, 224, 230));
		setBackground(Color.WHITE);
		setMinimumSize(new Dimension(960, 540));
		this.parent = parent;
		setFrame(new CustomWidgetFrame());
		initialize();
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param parent 
	 */
	private void initialize() {
		setBounds(100, 100, 960, 540);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.close();
				parent.launchLoginDialog();
			}
		});
		mnFile.add(mntmLogOut);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmAddProject = new JMenuItem("Add Project");
		mntmAddProject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		mnEdit.add(mntmAddProject);
		
		JMenuItem mntmAddActivity = new JMenuItem("Add Activity");
		mnEdit.add(mntmAddActivity);
		setLayout(null);
		Icon calenderIcon = new ImageIcon("./Ressource/calendericon.png");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(200, 0, 760, 540);
		panel_1.setBackground(new Color(176, 224, 230));
		add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(31, 65, 590, 443);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		table = new CustomTable();
		table.setBounds(6, 6, 578, 431);
		panel_2.add(table);
		
		JButton btnAddActivity = new JButton("Add Activity");
		btnAddActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAddActivity.setBounds(637, 77, 117, 29);
		panel_1.add(btnAddActivity);
		
		JButton btnEditActivity = new JButton("Edit Activity");
		btnEditActivity.setBounds(637, 111, 117, 29);
		panel_1.add(btnEditActivity);
		
		JButton btnRemoveActivty = new JButton("Remove Activty");
		btnRemoveActivty.setBounds(633, 143, 128, 29);
		panel_1.add(btnRemoveActivty);
		
		JLabel lblActivityOverview = new JLabel("Activity Overview");
		lblActivityOverview.setHorizontalAlignment(SwingConstants.CENTER);
		lblActivityOverview.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblActivityOverview.setBounds(31, 8, 253, 45);
		panel_1.add(lblActivityOverview);
		
		JButton btnCheckUsers = new JButton("Check Users");
		btnCheckUsers.setBounds(637, 174, 117, 29);
		panel_1.add(btnCheckUsers);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 200, 540);
		add(panel);
		panel.setBorder(null);
		panel.setBackground(new Color(176, 224, 230));
		panel.setLayout(null);
		
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(0, 0, 0, 0);
		panel.add(verticalStrut);
		
		JLabel lblManagement = new JLabel("Projects");
		lblManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagement.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblManagement.setBounds(13, 6, 169, 45);

		panel.add(lblManagement);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(24, 65, 158, 303);
		panel.add(scrollPane_2);
		
		JTree tree = new JTree();
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			}
		});
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Projects") {
				{
					add(new DefaultMutableTreeNode("Show All Projects"));
					add(new DefaultMutableTreeNode("Project 1"));
					add(new DefaultMutableTreeNode("Project 2"));
					add(new DefaultMutableTreeNode("Project 3"));
				}
			}
		));
		scrollPane_2.setColumnHeaderView(tree);
		
		JButton btnRemoveActivity = new JButton("Remove Project");
		btnRemoveActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				
				TreePath[] paths = tree.getSelectionPaths();
				if(paths != null) {
					for(TreePath path: paths) { DefaultMutableTreeNode node = (DefaultMutableTreeNode) 
                            path.getLastPathComponent();
                        if (node.getParent() != null) {
                            model.removeNodeFromParent(node);
                        }
                    }

				}
			}
		});
		btnRemoveActivity.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnRemoveActivity.setBounds(41, 477, 126, 29);
		panel.add(btnRemoveActivity);
		
		JButton btnProjectView = new JButton("Edit Project");
		btnProjectView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnProjectView.setBounds(41, 447, 127, 29);
		panel.add(btnProjectView);
		
		JButton btnAddActivty = new JButton("Add Project");
		btnAddActivty.setBounds(40, 414, 127, 29);
		panel.add(btnAddActivty);
		btnAddActivty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnAddActivty.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		JButton btnProjectOverview = new JButton("Project Overview");
		btnProjectOverview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnProjectOverview.setBounds(34, 383, 136, 29);
		panel.add(btnProjectOverview);
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
}