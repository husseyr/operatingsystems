/**
 * 
 */
package memoryallocator.util;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 * @author Rob Hussey
 *
 */
public class SpreadsheetTable extends JTable {
	private static final long serialVersionUID = 1L;
	Color item[][];
	
	public SpreadsheetTable(int row, int col, String[] colNames) {
		super();
		DefaultTableModel model = new DefaultTableModel();
		for (String c : colNames)
			model.addColumn(c);
		for(int i = 0; i < row; i++)
			model.addRow((Object[])null);
		super.setModel(model);
		item = new Color[row][col];
	}
	
	// set the given cell to the given color value
	public void setValue(Object o, int row, int col, Color bgColor) {
		super.setValueAt(o, row, col);
		item[row][col] = bgColor;
	}
	
	// update the cell renderer
	public void updateUI() {
		super.updateUI();
		int columnCount = getColumnCount();
		TableColumnModel columnModel= getColumnModel();
		for(int i=0; i < columnCount; i++) {
			columnModel.getColumn(i).setCellRenderer(new SpreadsheetCellRenderer());
		}
	}

}
