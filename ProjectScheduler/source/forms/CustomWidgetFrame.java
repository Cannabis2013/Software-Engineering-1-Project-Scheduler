package forms;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class CustomWidgetFrame extends JFrame implements CustomFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean mouseMoveEvent = false;
	private double tempMouseX, tempMouseY;
	private int X, Y;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomWidgetFrame frame = new CustomWidgetFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomWidgetFrame() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{21, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(!mouseMoveEvent)
					return;
				int mouseX =  (int) (e.getLocationOnScreen().getX() - tempMouseX);
				int mouseY = (int) (e.getLocationOnScreen().getY() - tempMouseY);
				
				setLocation(X + mouseX, Y + mouseY);
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(mouseMoveEvent)
					e.consume();
				mouseMoveEvent = true;
				X = (int) getLocationOnScreen().getX();
				Y = (int) getLocationOnScreen().getY();
				
				tempMouseX =  e.getLocationOnScreen().getX();
				tempMouseY =  e.getLocationOnScreen().getY();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseMoveEvent = false;
			}
		});
		
		panel.setBorder(null);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setPreferredSize(new Dimension(100, 30));
		panel.setMinimumSize(new Dimension(0, 0));
		panel.setBackground(new Color(0, 0, 128));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		
		
		JLabel label = new JLabel("X");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				boolean isWithin = true;
				double mouseX = e.getLocationOnScreen().getX();
				double mouseY = e.getLocationOnScreen().getY();
				double labelX = label.getLocationOnScreen().getX();
				double labelY = label.getLocationOnScreen().getY();
				
				if(mouseX < labelX || mouseX > labelX + label.getWidth())
					isWithin = false;
				if(mouseY < labelY || mouseY > labelY + label.getHeight())
					isWithin = false;
				
				
				if(isWithin)	
					dispose();
				else
				{
					Border border = BorderFactory.createEmptyBorder();
					label.setBorder(border);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Border border = BorderFactory.createLineBorder(Color.black, 2);
				label.setBorder(border);
			}
		});
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(label);
		
		
	}

	@Override
	public void setWidget(FrameImplementable implementable) {
		Component widget = (JPanel) implementable;
		
		this.setSize(widget.getPreferredSize());
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		layoutConstraints.fill = GridBagConstraints.BOTH;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 1;
		layoutConstraints.insets.top = 0;
		
		contentPane.add(widget,layoutConstraints);
		
	}

	@Override
	public void ShowDialog() {
		setVisible(true);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);
	}

	@Override
	public void close() {
		dispose();
	}

}
