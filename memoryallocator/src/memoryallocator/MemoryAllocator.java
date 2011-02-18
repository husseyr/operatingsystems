/**
 * 
 */
package memoryallocator;

import memoryallocator.ui.MainUI;

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
		MainUI m = new MainUI(DEFAULT_MEMORY_SIZE);
		m.setVisible(true);

	}

}
