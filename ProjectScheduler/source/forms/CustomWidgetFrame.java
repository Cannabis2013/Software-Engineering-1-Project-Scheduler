package forms;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import javafx.scene.control.Menu;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Insets;
import java.awt.LayoutManager;

public class CustomWidgetFrame extends JFrame implements CustomFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean mouseMoveEvent = false;
	private double tempMouseX, tempMouseY;
	private int X, Y;
	private GridBagLayout gbl_contentPane;
	private JPanel topBar;

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
		gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{21, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		topBar = new JPanel();
		topBar.addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseDragged(MouseEvent e) {
				if(!mouseMoveEvent)
					return;
				int mouseX =  (int) (e.getLocationOnScreen().getX() - tempMouseX);
				int mouseY = (int) (e.getLocationOnScreen().getY() - tempMouseY);
				
				setLocation(X + mouseX, Y + mouseY);
			}
		});
		topBar.addMouseListener(new MouseAdapter() {
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
		
		topBar.setBorder(null);
		FlowLayout flowLayout = (FlowLayout) topBar.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		topBar.setPreferredSize(new Dimension(100, 30));
		topBar.setMinimumSize(new Dimension(0, 0));
		topBar.setBackground(new Color(0, 0, 128));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(topBar, gbc_panel);
		
		
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
		topBar.add(label);
		
		
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

	@Override
	public void setMenuBar(JMenuBar menuBar) {
		
		JPanel widget =  (JPanel) contentPane.getComponent(1);
		contentPane.remove(widget);
		
		LayoutManager mng = contentPane.getLayout();
		
		int height = (int) (topBar.getPreferredSize().getHeight() + widget.getPreferredSize().getHeight() + 
				16);
		setSize(widget.getWidth(), height);
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		layoutConstraints.fill = GridBagConstraints.BOTH;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 1;
		contentPane.add(menuBar,layoutConstraints);
		
		layoutConstraints = new GridBagConstraints();
		layoutConstraints.fill = GridBagConstraints.BOTH;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 2;
		contentPane.add(widget, layoutConstraints);
		layoutConstraints.insets.bottom = 0;
		
		
	}

}
