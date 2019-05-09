
package formComponents;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import models.ItemModel;

public class CustomTable extends JTable {
	
	private static final long serialVersionUID = 1L;
	
	public CustomTable(TableModel model)
	{
		super(model);
		
		setShowGrid(false);
	}
	
	public int columnCount()
	{
		return getModel().getColumnCount();
	}
	
	public void setColumnCount(int count)
	{
		DefaultTableModel model = (DefaultTableModel) getModel();
		model.setColumnCount(count);
		setModel(model);
	}
	
	
	public void addItem(ItemModel item)
	{
		DefaultTableModel model = (DefaultTableModel) getModel();
		
		model.setColumnCount(columnCount());
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
	
	public List<ItemModel> items()
	{
		List<ItemModel> result = new ArrayList<ItemModel>();
		
		for (int i = 0; i < getRowCount(); i++)
			result.add(itemAt(i));
		
		return result;
	}
	
	public ItemModel currentItem()
	{
		int currentRow = this.getSelectedRow();
		return itemAt(currentRow);
	}
	
	public List<ItemModel> currentItems()
	{
		List<ItemModel> selectedItems = new ArrayList<ItemModel>();
		
		int[] selectedIndexes = getSelectedRows();
		for(int index : selectedIndexes)
			selectedItems.add(itemAt(index));
		
		return selectedItems;
	}
	
	public void clear()
	{
		DefaultTableModel model = (DefaultTableModel) getModel();
		for (int i = 0; i < model.getRowCount(); i++) {
			model.removeRow(i--);
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
	
	private ItemModel createItemModel(int index)
	{
		String[] data = new String[columnCount()];
		
		for (int i = 0; i < columnCount(); i++)
			data[i] = (String) getValueAt(index,i);
		
		
		return new ItemModel(data);
	}
	
}
