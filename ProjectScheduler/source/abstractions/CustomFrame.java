package abstractions;

import javax.swing.JMenuBar;

public interface CustomFrame {
	void setWidget(FrameImplementable implementable);
	void ShowDialog();
	public void setWindowModality(boolean modal);
	void close();
	
	void setMenuBar(JMenuBar menuBar);
	public void setWindowTitle(String title);
}
