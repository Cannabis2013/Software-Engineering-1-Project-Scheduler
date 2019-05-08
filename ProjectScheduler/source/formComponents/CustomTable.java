
package formComponents;

import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class CustomTable extends JTable {
	
	private static final long serialVersionUID = 1L;
	private int columnCount = 3;
	private String[] columns = {"col1","col2","col3"};
	
	public CustomTable(TableModel model)
	{
		super(model);
		
		setShowGrid(false);
	}
	
	public int columnCount()
	{
		return columnCount;
	}
	
	public void setColumnCount(int count)
	{
		columnCount = count;
	}
	
	
	public void addItem(ItemModel item)
	{
		DefaultTableModel model = (DefaultTableModel) getModel();
		
		model.setColumnCount(columnCount);
		model.addRow(item.data());
		setModel(model);
	}
	
	public void removeItem(int index)
	{
		if(index > getRowCount() || index < 0)
			return;
		DefaultTableModel model = (DefaultTableModel) getModel();
		model.removeRow(index);
		setModel(model);
	}
	
	public void removeFirstOccurence(ItemModel item)
	{
		for (int i = 0; i < getRowCount(); i++) {
			ItemModel model = itemAt(i);
			if(model.equals(item))
			{
				removeItem(i);
				return;
			}
		}
	}
	
	public void removeAllOccurences(ItemModel item)
	{
		for (int i = 0; i < getRowCount(); i++) {
			ItemModel model = itemAt(i);
			if(model.equals(item))
			{
				removeItem(i);
				i--;
			}
		}
	}
	
	public ItemModel itemAt(int index)
	{
		return createItemModel(index);
	}
	
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
	
	private ItemModel createItemModel(int index)
	{
		String[] data = new String[columnCount];
		
		for (int i = 0; i < columnCount; i++)
			data[columnCount] = (String) getValueAt(index, i);
		
		return new ItemModel(data);
	}
	
}
