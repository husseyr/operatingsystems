/**
 * 
 */
package memoryallocator.util;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author rob
 *
 */
public class Fields {
	private int memSize;
	private List<Partition> partList;
	private Queue<Job> jobQueue;
	private static int totalPartSize;
	
	public Fields(int memSize, List<Partition> partList, Queue<Job> jobQueue) {
		this.memSize = memSize;
		this.partList = partList;
		this.jobQueue = jobQueue;
		totalPartSize = 0;
	}
	
	public int getMemSize() {
		return memSize;
	}
	public void setMemSize(int memSize) {
		this.memSize = memSize;
		if (memSize < totalPartSize)
			totalPartSize = 0;
		partList = new LinkedList<Partition>();
	}
	public int getTotalPartSize() {
		return totalPartSize;
	}
	public List<Partition> getPartList() {
		return partList;
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
			partList.add(new Partition(size, memAddress));
			totalPartSize += size;
			return true;
		}
		return false;
	}
	
	public void removePartition(int id) {
		if (partList.size() <= id)
			return;
		
		int prevAddr = partList.get(id).startAddress;
		for (int i = id + 1; i < partList.size(); i++) {
			int curAddr = partList.get(i).startAddress;
			partList.get(i).startAddress = prevAddr;
			prevAddr = curAddr;
		}
		totalPartSize -= partList.remove(id).size;
	}

}
