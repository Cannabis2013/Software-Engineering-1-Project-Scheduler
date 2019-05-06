package abstractions;

import javax.swing.JMenuBar;

public interface CustomFrame {
	void setWidget(FrameImplementable implementable);
	void ShowDialog();
	void close();
	
	void setMenuBar(JMenuBar menuBar);
}
