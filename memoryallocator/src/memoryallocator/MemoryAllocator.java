/**
 * 
 */
package memoryallocator;

import memoryallocator.ui.MainUI;
import memoryallocator.util.Fields;

/**
 * @author rob
 *
 */
public class MemoryAllocator {
	private static final int DEFAULT_MEMORY_SIZE = 51248;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Fields fields = new Fields();
		fields.setMemSize(DEFAULT_MEMORY_SIZE);
		MainUI m = new MainUI(fields);
		m.setVisible(true);

	}

}
