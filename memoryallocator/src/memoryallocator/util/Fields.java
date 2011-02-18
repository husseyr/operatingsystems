/**
 * 
 */
package memoryallocator.util;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author rob
 *
 */
public class Fields {
	private int memSize;
	private Deque<Partition> partStack;
	private Queue<Job> jobQueue;
	private static int totalPartSize;
	
	public Fields(int memSize, Deque<Partition> partStack, Queue<Job> jobQueue) {
		this.memSize = memSize;
		this.partStack = partStack;
		this.jobQueue = jobQueue;
		totalPartSize = 0;
	}
	
	public int getMemSize() {
		return memSize;
	}
	public void setMemSize(int memSize) {
		this.memSize = memSize;
		totalPartSize = 0;
		partStack = new LinkedList<Partition>();
	}
	public int getTotalPartSize() {
		return totalPartSize;
	}
	public Deque<Partition> getPartStack() {
		return partStack;
	}
	public Queue<Job> getJobQueue() {
		return jobQueue;
	}
	
	/**
	 * Add partition to stack if it can fit.
	 * @param size
	 * @param memAddress
	 * @return true if the partition was added, false if not
	 */
	public boolean addPartition(int size, int memAddress) {
		if (totalPartSize + size <= memSize) {
			partStack.push(new Partition(size, memAddress));
			totalPartSize += size;
			return true;
		}
		return false;
	}

}
