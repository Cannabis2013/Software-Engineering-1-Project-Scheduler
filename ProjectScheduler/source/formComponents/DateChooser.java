package formComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.text.SimpleDateFormat;
import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import forms.CustomWidgetFrame;
import java.util.Calendar;
import java.util.Locale;

public class DateChooser extends JPanel implements FrameImplementable {
	int month = Calendar.getInstance().get(Calendar.MONTH);
	int year = Calendar.getInstance().get(Calendar.YEAR);
	JLabel dateLabel = new JLabel("", JLabel.CENTER);
	String day = "";
	JDialog dateDialog;
	Calendar calender = Calendar.getInstance();
	JPanel p1, p2;
	JButton next, previous;
	String[] header = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
	JButton[] button = new JButton[49];
	CustomFrame frame = null;

	public DateChooser(JPanel panel, JTextField text) {
		dateDialog = new JDialog();
		dateDialog.setModal(true);
    	dateDialog.setResizable(false);
    
    	p1 = new JPanel(new GridLayout(7, 7));
    	p1.setPreferredSize(new Dimension(420, 120));

    	for (int i = 0; i < button.length; i++) {
    		int selected = i;
    		button[i] = new JButton();
    		button[i].setFocusPainted(false);
    		button[i].setBackground(Color.white);
        
    		if (i > 6) {
    			button[i].addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent ae) {
    					if (button[selected].getActionCommand() == "") {
    						return;
        					} else {
        						day = button[selected].getActionCommand();
        						dateDialog.dispose();
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
    	
    	p2 = new JPanel(new GridLayout(1, 3));
    	previous = new JButton("<<");
    	previous.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ae) {
    			month--;
    			displayCalender();
    			}
    		});
    	
    	next = new JButton(">>");
    	next.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent ae) {
    			month++;
    			displayCalender();
    			}
    		});
    	
    	p2.add(previous);
    	p2.add(dateLabel);
    	p2.add(next);
    	
    	dateDialog.add(p1, BorderLayout.CENTER);
    	dateDialog.add(p2, BorderLayout.SOUTH);
    	dateDialog.pack();
    	dateDialog.setLocationRelativeTo(panel);
    	displayCalender();
    	dateDialog.setVisible(true);
    	}
	
	public void displayCalender() {
		for (int x = 7; x < button.length; x++) {
			button[x].setText("");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", new Locale("en"));
		calender.set(year, month, 1);
	
		int dayOfWeek = calender.get(java.util.Calendar.DAY_OF_WEEK);
		int dayOfMonth = calender.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		for (int i = 5 + dayOfWeek, day = 1; day <= dayOfMonth; i++, day++) {
			button[i].setText("" + day);
			dateLabel.setText(sdf.format(calender.getTime()));
		}
	}

	public String setPickedDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM/yyyy");
		calender.set(year, month, Integer.parseInt(day));
		return sdf.format(calender.getTime());
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