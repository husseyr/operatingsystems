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
	private List<Job> jobQueue;
	private static int totalPartSize;
	private boolean dynamic;
	private static int lastJobID;
	
	public Fields(int memSize, List<Partition> partList, List<Job> jobQueue) {
		this.memSize = memSize;
		this.partList = partList;
		this.jobQueue = jobQueue;
		dynamic = false;
		totalPartSize = 0;
		lastJobID = -1; // -1 since there is no last job at this point
	}
	
	public int getMemSize() {
		return memSize;
	}
	public void setMemSize(int memSize) {
		this.memSize = memSize;
		
		if (dynamic == true) {
			lastJobID = 0;
			jobQueue = new LinkedList<Job>();
		}
		
		if (dynamic == true || (dynamic == false && memSize < totalPartSize)) {
			totalPartSize = 0;
			partList = new LinkedList<Partition>();
		}
	}
	public int getTotalPartSize() {
		return totalPartSize;
	}
	public List<Partition> getPartList() {
		return partList;
	}
	public List<Job> getJobQueue() {
		return jobQueue;
	}
	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
		totalPartSize = 0;
		partList = new LinkedList<Partition>();
		lastJobID = 0;
		jobQueue = new LinkedList<Job>();
	}
	public boolean isDynamic() {
		return dynamic;
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
	
	public int getLargestPartition() {
		int largestSize = 0;
		for (Partition p : partList) {
			if (p.size > largestSize)
				largestSize = p.size;
		}
		return largestSize;
	}

	public void addJob(int size) {
		lastJobID++;
		jobQueue.add(new Job(lastJobID, size, 30));
	}
	
	public void removeJob(int id) {
		if (jobQueue.size() <= id)
			return;
		
		for (int i = id + 1; i < jobQueue.size(); i++)
			jobQueue.get(i).setId(i - 1);
			
		jobQueue.remove(id);
		lastJobID--;
	}
}
