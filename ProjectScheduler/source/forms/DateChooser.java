package forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import abstractions.CustomFrame;
import abstractions.FrameImplementable;

import java.util.Calendar;
import java.util.Locale;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class DateChooser extends JPanel implements FrameImplementable {
	
	private static final long serialVersionUID = 1L;
	int month = Calendar.getInstance().get(Calendar.MONTH);
	int year = Calendar.getInstance().get(Calendar.YEAR);
	private JLabel dateLabel = new JLabel("", JLabel.CENTER);
	private String day = "";
	private Calendar calender = Calendar.getInstance();
	private JPanel p1, p2;
	private JButton next, previous;
	private String[] header = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
	private JButton[] button = new JButton[49];
	private CustomFrame frame = null;
	private String componentTitle;

	public DateChooser(JTextField text) {
		componentTitle = "Choose date";
		
		p1 = new JPanel(new GridLayout(7, 7));
		p1.setPreferredSize(new Dimension(420, 120));
	    
    	for (int i = 0; i < button.length; i++) {
    		int selected = i;
    		button[i] = new JButton();
        
    		if (i > 6) {
    			button[i].setForeground(Color.red);
    			button[i].addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					if (button[selected].getActionCommand() == "") {
    						return;
        					} 
    					else 
        					{
        						day = button[selected].getActionCommand();
        						frame.close();
        						text.setText(setPickedDate());
                        	}
        				}
        			});
        		}
    		if (i < 7) {
    			button[i].setText(header[i]);
    			button[i].setForeground(Color.blue);
        		}
    		p1.add(button[i]);
        	}
    	GridBagLayout gridBagLayout = new GridBagLayout();
    	gridBagLayout.columnWidths = new int[]{420, 0};
    	gridBagLayout.rowHeights = new int[]{120, 23, 0};
    	gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
    	gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
    	setLayout(gridBagLayout);
    	
    	p2 = new JPanel(new GridLayout(1, 3));
    	previous = new JButton("<<");
    	previous.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			month--;
    			displayCalender();
    			}
    		});
    	
    	next = new JButton(">>");
    	next.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			month++;
    			displayCalender();
    			}
    		});
    	
    	GridBagConstraints gbc_p1 = new GridBagConstraints();
    	gbc_p1.fill = GridBagConstraints.BOTH;
    	gbc_p1.insets = new Insets(0, 0, 5, 0);
    	gbc_p1.gridx = 0;
    	gbc_p1.gridy = 0;
    	add(p1, gbc_p1);
    	
    	p2.add(previous);
    	p2.add(dateLabel);
    	p2.add(next);
    	GridBagConstraints gbc_p2 = new GridBagConstraints();
    	gbc_p2.fill = GridBagConstraints.VERTICAL;
    	gbc_p2.gridx = 0;
    	gbc_p2.gridy = 1;
    	add(p2, gbc_p2);
    	displayCalender();
    	}
	
	public void displayCalender() {
		for (int i = 7; i < button.length; i++) {
			button[i].setText("");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", new Locale("en"));
		calender.set(year, month, 1);
		int dayOfWeek = calender.get(java.util.Calendar.DAY_OF_WEEK);
		int dayOfMonth = calender.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		for (int i = 5 + dayOfWeek, day = 1; day <= dayOfMonth; i++, day++) {
			button[i].setText("" + day);
		}
		dateLabel.setText(sdf.format(calender.getTime()));
	}

	public String setPickedDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		calender.set(year, month, Integer.parseInt(day));
		return sdf.format(calender.getTime());
	}
	
	public LocalDate getPickedLocalDate() {
		new SimpleDateFormat("dd-MM-yyyy");
		return LocalDate.of(year, month,Integer.parseInt(day));
	}

	@Override
	public void setFrame(CustomFrame frame) {
		this.frame = frame;
		this.frame.setWidget(this);
		this.frame.setWindowModality(true);
		this.frame.setResizeable(false);
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