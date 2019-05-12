package formComponents;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import models.ItemModel;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class CustomTableComponent extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomTable table;
	public CustomTableComponent() {
		setLayout(new BorderLayout(0, 0));
		DefaultTableModel model = new DefaultTableModel();
		table = new CustomTable(model);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
				setCursor(cursor);
			}
		});
		add(scrollPane, BorderLayout.CENTER);
		add(table.getTableHeader(),BorderLayout.NORTH);
		
		
	}
	
	public void setHeaderLabels(String[] labels)
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(labels);
		table.setModel(model);
	}
	
	public void addItem(ItemModel item)
	{
		table.addItem(item);
	}
	
	public void addItems(List<ItemModel> items)
	{
		for(ItemModel item : items)
			table.addItem(item);
	}
	
	public void removeItemAt(int index)
	{
		table.removeItem(index);
	}
	
	public void removeItem(ItemModel item)
	{
		table.removeFirstOccurence(item);
	}
	
	public ItemModel itemAt(int index)
	{
		return table.itemAt(index);
	}
	
	public List<ItemModel> allItems()
	{
		return table.items();
	}
	
	public ItemModel currentItem() throws Exception
	{
		return table.currentItem();
	}
	
	public List<ItemModel> currentItems() throws Exception
	{
		return table.currentItems();
	}
	
	public int currentIndex()
	{
		return table.getSelectedRow();
	}
	
	public void setCurrentIndex(int index)
	{
		if(index >= columnCount() || index < 0)
			return;
		
		table.setRowSelectionInterval(index, index);
	}
	
	public int columnCount()
	{
		return table.columnCount();
	}
	
	public int rowCount()
	{
		return table.getRowCount();
	}
	
	public void setColumnCount(int count)
	{
		table.setColumnCount(count);
	}
	
	public void clear()
	{
		table.clear();
	}
	
	public void setMultipleSelectionMode(boolean mode)
	{
		if(mode)
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		else
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	public void addMouseListener(MouseListener l)
	{
		table.addMouseListener(l);
	}
}
