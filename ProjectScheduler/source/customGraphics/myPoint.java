package customGraphics;

public class myPoint<t1,t2> {
	
	private t1 X;
	private t2 Y;
	
	public myPoint(t1 x, t2 y)
	{
		X = x;
		Y = y;
	}
	
	public t1 getX()
	{
		return X;
	}
	
	public t2 getY()
	{
		return Y;
	}

}
