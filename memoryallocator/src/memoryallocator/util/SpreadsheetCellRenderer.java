package memoryallocator.util;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class SpreadsheetCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object o, 
			boolean isSelected, boolean isFocus, int row, int col) {
		super.getTableCellRendererComponent(table,o,isSelected,isFocus,row,col);
		SpreadsheetTable sTable = (SpreadsheetTable)table;
		setBackground(sTable.item[row][col]);
		return this;
	}
}
