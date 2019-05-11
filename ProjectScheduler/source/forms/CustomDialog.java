package forms;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import abstractions.CustomFrame;
import abstractions.FrameImplementable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Insets;

public class CustomDialog extends JDialog implements CustomFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean mouseMoveEvent = false;
	private double tempMouseX, tempMouseY;
	private int X, Y;
	private int tempWidth, tempHeight;
	private GridBagLayout gbl_contentPane;
	private JPanel topBar;
	private JLabel windowTitle;
	private boolean widgetIsResizing = false;
	private boolean resizeable = true;
	private int borderWidth = 5;

	/**
	 * Create the frame.
	 */
	public CustomDialog() {
		setUndecorated(true);
		initializeComponents();
		
	}
	
	public void initializeComponents()
	{
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				X = (int) arg0.getLocationOnScreen().getX();
				Y = (int) arg0.getLocationOnScreen().getY();
				
				tempWidth = getWidth();
				tempHeight = getHeight();	
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				widgetIsResizing = false;
			}
		});
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				
				if(((arg0.getX() < borderWidth || arg0.getX() > getWidth() - borderWidth || 
						arg0.getY() > getHeight() - borderWidth) || 
						widgetIsResizing) && resizeable)
				{
					widgetIsResizing = true;
					int diffX = (int) (arg0.getLocationOnScreen().getX()- X);
					int diffY = (int) (arg0.getLocationOnScreen().getY() -Y);
					int newWidth = tempWidth + diffX, newHeight = tempHeight + diffY;
					
					setSize(new Dimension(newWidth, newHeight));
				}
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				if((e.getX() < borderWidth || e.getX() > getWidth() - borderWidth || 
						e.getY() > getHeight() - borderWidth) && resizeable)
				{
					Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
					setCursor(cursor);
				}
				else
				{
					Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
					setCursor(cursor);
				}
			}
		});
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[] {32, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
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
			@Override
			public void mouseMoved(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.MOVE_CURSOR);
				setCursor(cursor);
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
		topBar.setPreferredSize(new Dimension(100, 30));
		topBar.setMinimumSize(new Dimension(100, 30));
		topBar.setBackground(new Color(0, 0, 128));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(topBar, gbc_panel);
		GridBagLayout gbl_topBar = new GridBagLayout();
		gbl_topBar.columnWidths = new int[]{436, 9, 0};
		gbl_topBar.rowHeights = new int[]{16, 0};
		gbl_topBar.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_topBar.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		topBar.setLayout(gbl_topBar);
		
		
		JLabel label = new JLabel("X");
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
				setCursor(cursor);
			}
		});
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
		
		windowTitle = new JLabel("New label");
		windowTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		windowTitle.setForeground(Color.WHITE);
		windowTitle.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_windowTitle = new GridBagConstraints();
		gbc_windowTitle.anchor = GridBagConstraints.WEST;
		gbc_windowTitle.insets = new Insets(0, 5, 0, 0);
		gbc_windowTitle.gridx = 0;
		gbc_windowTitle.gridy = 0;
		topBar.add(windowTitle, gbc_windowTitle);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		topBar.add(label, gbc_label);
	}

	@Override
	public void setWidget(FrameImplementable implementable) {
		
		setWindowTitle(implementable.title());
		Component widget = (JPanel) implementable;
		this.setSize(widget.getPreferredSize());
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		layoutConstraints.fill = GridBagConstraints.BOTH;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 2;
		layoutConstraints.insets = new Insets(0, borderWidth, borderWidth, borderWidth);
		
		contentPane.add(widget,layoutConstraints);
		
		int minHeight = widget.getMinimumSize().height + topBar.getMinimumSize().height;
		int minWidth = widget.getMinimumSize().width + topBar.getMinimumSize().width;
		setMinimumSize(new Dimension(minWidth, minHeight));
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
		
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		layoutConstraints.fill = GridBagConstraints.BOTH;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 1;
		layoutConstraints.insets = new Insets(0,borderWidth,0,borderWidth);
		contentPane.add(menuBar,layoutConstraints);
		
		menuBar.setMinimumSize(new Dimension(0,23));
		menuBar.setMaximumSize(new Dimension(32768,23));
		layoutConstraints = new GridBagConstraints();
		layoutConstraints.fill = GridBagConstraints.BOTH;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 2;
		layoutConstraints.insets = new Insets(0, borderWidth, borderWidth, borderWidth);
		
		contentPane.add(widget, layoutConstraints);
		layoutConstraints.insets.bottom = 0;
		
		int minHeight = getMinimumSize().height + menuBar.getMinimumSize().height;
		int minWidth = getMinimumSize().width + menuBar.getMinimumSize().width;
		setMinimumSize(new Dimension(minWidth, minHeight));
	}

	@Override
	public void setWindowModality(boolean modal) {
		setModal(modal);
	}
	
	public void setWindowTitle(String title)
	{
		windowTitle.setText(title);
	}

	@Override
	public void setResizeable(boolean rz) {
		// TODO Auto-generated method stub
		resizeable = rz;
	}

}
