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
 * @author rob
 *
 */
public class MemoryAllocator {
	private static final int DEFAULT_MEMORY_SIZE = 51248;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// fields : memory size, stack of partitions, queue of jobs
		Fields fields = new Fields(DEFAULT_MEMORY_SIZE, new LinkedList<Partition>(), new LinkedList<Job>());
		fields.setMemSize(DEFAULT_MEMORY_SIZE);
		MainUI m = new MainUI(fields);
		m.setVisible(true);

	}

}
