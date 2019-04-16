
public class ItemModel {
	
	private String val;
	private String[] subVals = new String[0];
	
	public ItemModel(String[] columnValues)
	{
		val = columnValues[0];
		subVals = new String[columnValues.length - 1];
		for (int i = 1; i < columnValues.length; i++)
			subVals[i - 1] = columnValues[i];
		
	}
	public ItemModel(String text)
	{
		val = text;
	}
	
	public String text(int col)
	{
		if(col < 0)
			return null;
		if(col == 0)
			return val;
		if(col > subVals.length)
			return null;
		else
			return subVals[col - 1];
	}
	
	public void setText(String text, int column)
	{
		if(column < 0)
			return;
		if(column == 0)
			val = text;
		if(column > subVals.length -1)
			return;
		else
			subVals[column] = text;
	}
	
	public int columnCount()
	{
		return subVals.length + 1;
	}
	
	public boolean equals(ItemModel compare)
	{
		if(compare.text(0) != val)
			return false;
		
		for (int i = 0; i < subVals.length; i++) {
			if(!subVals[i].equals(compare.subVals[i]))
				return false;
		}
		
		return true;
	}
	
	public String[] data()
	{
		String[] d = new String[columnCount()];
		d[0] = val;
		for (int i = 1; i < columnCount(); i++) {
			d[i] = subVals[i - 1];
		}
		
		return d;
	}
	
}