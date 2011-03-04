/**
 * 
 */
package memoryallocator;

import java.util.LinkedList;

import memoryallocator.ui.MainUI;
import memoryallocator.util.Fields;
import memoryallocator.util.Job;
import memoryallocator.util.Partition;

/**
 * @author Rob Hussey
 *
 */
public class MemoryAllocator {
	private static final int DEFAULT_MEMORY_SIZE = 4096; // the main memory size to start
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// fields : memory size, stack of partitions, queue of jobs
		Fields fields = new Fields(DEFAULT_MEMORY_SIZE, new LinkedList<Partition>(), new LinkedList<Job>());
		
		// create and show new main ui 
		MainUI m = new MainUI(fields);
		m.setVisible(true);

	}

}
