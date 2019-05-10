package forms;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

public class AbsentActivityDialog extends JPanel {
	private JTextField txtYourTitel;

	/**
	 * Create the panel.
	 */
	public AbsentActivityDialog() {
		setBackground(new Color(176, 224, 230));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblFillActivityOf = new JLabel("Fill activity of absence");
		lblFillActivityOf.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblFillActivityOf = new GridBagConstraints();
		gbc_lblFillActivityOf.insets = new Insets(5, 0, 5, 0);
		gbc_lblFillActivityOf.gridx = 0;
		gbc_lblFillActivityOf.gridy = 0;
		add(lblFillActivityOf, gbc_lblFillActivityOf);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 224, 230));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 5, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblTitle = new JLabel("Title");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		panel_1.add(lblTitle, gbc_lblTitle);
		
		txtYourTitel = new JTextField();
		txtYourTitel.setText("Your titel");
		GridBagConstraints gbc_txtYourTitel = new GridBagConstraints();
		gbc_txtYourTitel.insets = new Insets(0, 0, 5, 0);
		gbc_txtYourTitel.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYourTitel.gridx = 1;
		gbc_txtYourTitel.gridy = 0;
		panel_1.add(txtYourTitel, gbc_txtYourTitel);
		txtYourTitel.setColumns(10);
		
		JLabel lblStartDate = new JLabel("Start date");
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.anchor = GridBagConstraints.WEST;
		gbc_lblStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDate.gridx = 0;
		gbc_lblStartDate.gridy = 1;
		panel_1.add(lblStartDate, gbc_lblStartDate);
		
		JLabel lblDate = new JLabel("");
		lblDate.setIcon(new ImageIcon("C:\\Users\\Martin\\OneDrive\\Programmering\\Git projekter\\ProjectScheduler\\ProjectScheduler\\Ressource\\calendericon.png"));
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.WEST;
		gbc_lblDate.insets = new Insets(0, 0, 5, 0);
		gbc_lblDate.gridx = 1;
		gbc_lblDate.gridy = 1;
		panel_1.add(lblDate, gbc_lblDate);
		
		JLabel lblEndDate = new JLabel("End date");
		GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
		gbc_lblEndDate.anchor = GridBagConstraints.WEST;
		gbc_lblEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndDate.gridx = 0;
		gbc_lblEndDate.gridy = 2;
		panel_1.add(lblEndDate, gbc_lblEndDate);
		
		JLabel lblDate_1 = new JLabel("");
		lblDate_1.setIcon(new ImageIcon("C:\\Users\\Martin\\OneDrive\\Programmering\\Git projekter\\ProjectScheduler\\ProjectScheduler\\Ressource\\calendericon.png"));
		GridBagConstraints gbc_lblDate_1 = new GridBagConstraints();
		gbc_lblDate_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblDate_1.anchor = GridBagConstraints.WEST;
		gbc_lblDate_1.gridx = 1;
		gbc_lblDate_1.gridy = 2;
		panel_1.add(lblDate_1, gbc_lblDate_1);
		
		JTextArea txtrDescriptionbox = new JTextArea();
		txtrDescriptionbox.setText("Enter details here.");
		GridBagConstraints gbc_txtrDescriptionbox = new GridBagConstraints();
		gbc_txtrDescriptionbox.gridwidth = 2;
		gbc_txtrDescriptionbox.fill = GridBagConstraints.BOTH;
		gbc_txtrDescriptionbox.gridx = 0;
		gbc_txtrDescriptionbox.gridy = 4;
		panel_1.add(txtrDescriptionbox, gbc_txtrDescriptionbox);
		
		JPanel buttonGroup = new JPanel();
		GridBagConstraints gbc_buttonGroup = new GridBagConstraints();
		gbc_buttonGroup.insets = new Insets(0, 0, 5, 0);
		gbc_buttonGroup.fill = GridBagConstraints.VERTICAL;
		gbc_buttonGroup.gridx = 0;
		gbc_buttonGroup.gridy = 2;
		add(buttonGroup, gbc_buttonGroup);
		buttonGroup.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnCancel = new JButton("Cancel");
		buttonGroup.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		buttonGroup.add(btnSave);

	}

}
