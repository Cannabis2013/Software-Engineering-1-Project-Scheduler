package forms;
import javax.swing.JFrame;

import Application_Facade.ApplicationFacade;

public class ProjectView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private ApplicationFacade parent;
	
	public ProjectView(ApplicationFacade parent) {
		
		this.parent = parent;
		initialize();
		setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	}

}
