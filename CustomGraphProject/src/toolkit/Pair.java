package toolkit;

public class Pair <t1,t2> {
	private t1 T1;
	private t2 T2;
	
	public Pair(t1 first, t2 second) {
		T1 = first;
		T2 = second;
	}
	
	public t1 first()
	{
		return T1;
	}
	
	public t2 second()
	{
		return T2;
	}
}
