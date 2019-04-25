package forms;
import javax.swing.JFrame;

import Application_Facade.ApplicationFrontEnd;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import formComponents.CustomTable;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class ProjectView extends JFrame {
	
	private ApplicationFrontEnd parent;
	private static final long serialVersionUID = 1L;
	private CustomTable table;
	private CustomTable table_1;
	public ProjectView(ApplicationFrontEnd parent) {
		getContentPane().setBackground(Color.BLACK);
		setBackground(Color.WHITE);
		setMinimumSize(new Dimension(640, 400));
		this.parent = parent;
		initialize();
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 524, 495);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mnFile.add(mntmLogOut);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{212, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Assigned activities");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.ORANGE, 1, true));
		panel.setBackground(Color.BLACK);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(5, 5, 5, 5);
		gbc_panel.gridheight = 4;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblWelcomeText = new JLabel("Welcome text");
		lblWelcomeText.setForeground(Color.WHITE);
		lblWelcomeText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblWelcomeText = new GridBagConstraints();
		gbc_lblWelcomeText.fill = GridBagConstraints.BOTH;
		gbc_lblWelcomeText.gridx = 0;
		gbc_lblWelcomeText.gridy = 0;
		panel.add(lblWelcomeText, gbc_lblWelcomeText);
		
		JLabel label = new JLabel("");
		label.setMaximumSize(new Dimension(64, 64));
		label.setInheritsPopupMenu(false);
		label.setIconTextGap(0);
		label.setIcon(new ImageIcon("C:\\Users\\Martin\\OneDrive\\Programmering\\Git projekter\\ProjectScheduler\\ProjectScheduler\\Ressource\\paagen kanelgifler.jpg"));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(2, 2, 2, 2);
		gbc_label.anchor = GridBagConstraints.NORTH;
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);
		
		table = new CustomTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 1;
		getContentPane().add(table, gbc_table);
		
		JLabel lblRegisteredHours = new JLabel("Registered hours");
		lblRegisteredHours.setForeground(Color.WHITE);
		lblRegisteredHours.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblRegisteredHours = new GridBagConstraints();
		gbc_lblRegisteredHours.gridx = 1;
		gbc_lblRegisteredHours.gridy = 2;
		getContentPane().add(lblRegisteredHours, gbc_lblRegisteredHours);
		
		table_1 = new CustomTable();
		GridBagConstraints gbc_table_1 = new GridBagConstraints();
		gbc_table_1.fill = GridBagConstraints.BOTH;
		gbc_table_1.gridx = 1;
		gbc_table_1.gridy = 3;
		getContentPane().add(table_1, gbc_table_1);
	}

}
