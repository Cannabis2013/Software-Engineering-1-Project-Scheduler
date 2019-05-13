/*
 * Martin Hansen
 */


package abstractions;

public interface FrameImplementable {
	void setFrame(CustomFrame frame);
	
	void setTitle(String title);
	String title();
	
	void close();
	
}
