package formComponents;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.eclipse.jface.viewers.deferred.SetModel;

public class CustomTableComponent extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomTable table;
	public CustomTableComponent() {
		setLayout(new BorderLayout(0, 0));
		
		
		table = new CustomTable(new DefaultTableModel());
		add(table, BorderLayout.CENTER);
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
	
	public void removeItem(int index)
	{
		table.removeItem(index);
	}
	
	public ItemModel itemAt(int index)
	{
		return table.itemAt(index);
	}
	
	public int columnCount()
	{
		return table.columnCount();
	}
	
}
