package models;

public class ItemModel {
	
	private String[] itemData = new String[1];
	
	public ItemModel(String[] columnValues)
	{
		itemData = columnValues;
		
	}
	public ItemModel(String text)
	{
		itemData[0] = text;
	}
	
	public String text(int col)
	{
		return itemData[col];
	}
	
	public void setText(String text, int column)
	{
		String[] tempData = itemData;
		if(itemData.length <= column)
			itemData = new String[column + 1];
		
		for (int i = 0; i < itemData.length; i++) {
			if(i == column)
				itemData[i] = text;
			else if(i < tempData.length)
				itemData[i] = tempData[i];
			else
				itemData[i] = "";
		}
	}
	
	public int columnCount()
	{
		return itemData.length + 1;
	}
	
	public boolean equals(ItemModel compare)
	{
		for (int i = 0; i < itemData.length; i++) {
			if(!itemData[i].equals(compare.itemData[i]))
				return false;
		}
		return true;
	}
	
	public String[] data()
	{
		return itemData;
	}
	
}