/**
 * 
 */
package memoryallocator.util;

import java.util.Deque;
import java.util.Queue;

/**
 * @author rob
 *
 */
public class Fields {
	private static int memSize;
	private static Deque<Partition> partStack;
	private static Queue<Job> jobQueue;
	
	public int getMemSize() {
		return memSize;
	}
	public void setMemSize(int memSize) {
		Fields.memSize = memSize;
	}
	public static Deque<Partition> getPartStack() {
		return partStack;
	}
	public static Queue<Job> getJobQueue() {
		return jobQueue;
	}

}
