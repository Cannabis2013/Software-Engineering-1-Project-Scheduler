package abstractions;

import javax.swing.JMenuBar;

public interface CustomFrame {
	void setWidget(FrameImplementable implementable);
	void ShowDialog();
	public void setModality(boolean modal);
	void close();
	
	void setMenuBar(JMenuBar menuBar);
}
