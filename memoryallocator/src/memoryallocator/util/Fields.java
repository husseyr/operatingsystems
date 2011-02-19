/**
 * 
 */
package memoryallocator.util;

import java.util.Arrays;
import java.util.Collections;
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
	private List<Job> jobList;
	private Queue<Job> waitingQueue;
	private static int totalPartSize;
	private boolean dynamic;
	private static int lastJobID;
	private static int lastPartID;
	private int algorithm; // 0=first-fit, 1=best-fit, 2=next-fit, 3=worst-fit
	private int lastPartAssigned;
	
	public Fields(int memSize, List<Partition> partList, List<Job> jobList) {
		this.memSize = memSize;
		this.partList = partList;
		this.jobList = jobList;
		waitingQueue = new LinkedList<Job>();
		dynamic = false;
		totalPartSize = 0;
		lastJobID = -1; // -1 since there is no last job at this point
		lastPartID = -1;
		algorithm = 0;
		lastPartAssigned = 0;
	}
	
	public int getMemSize() {
		return memSize;
	}
	public void setMemSize(int memSize) {
		this.memSize = memSize;
		
		if (dynamic == true) {
			lastJobID = 0;
			jobList = new LinkedList<Job>();
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
	public Partition[] getOrderedPartArray() {
		Partition[] oParts = (Partition[]) partList.toArray();
		Arrays.sort(oParts);
		
		return oParts;
	}
	public Partition[] getReverseOrderedPartArray() {
		Partition[] oParts = (Partition[]) partList.toArray();
		Arrays.sort(oParts, Collections.reverseOrder());
		
		return oParts;
	}
	public List<Job> getJobList() {
		return jobList;
	}
	public Queue<Job> getWaitingQueue() {
		return waitingQueue;
	}
	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
		totalPartSize = 0;
		partList = new LinkedList<Partition>();
		lastJobID = 0;
		jobList = new LinkedList<Job>();
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
		lastPartID++;
		if (totalPartSize + size <= memSize) {
			partList.add(new Partition(lastPartID, size, memAddress));
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
		jobList.add(new Job(lastJobID, size, 30));
	}
	
	public void removeJob(int id) {
		if (jobList.size() <= id)
			return;
		
		for (int i = id + 1; i < jobList.size(); i++)
			jobList.get(i).setId(i - 1);
			
		jobList.remove(id);
		lastJobID--;
	}
	
	public void setAlgorithm(int algorithm) {
		this.algorithm = algorithm;
	}
	
	public int getAlgorithm() {
		return algorithm;
	}
	
	public void firstFit() {
		if (jobList.isEmpty())
			return;
		
		Job nextJob = jobList.remove(0);
		for (Partition p : partList) {
			if (!p.isBusy() && p.getSize() >= nextJob.getSize()) {
				p.assignJob(nextJob);
				lastPartAssigned = p.id;
				return;
			}
		}
		
		waitingQueue.add(nextJob);
	}
	
	public void bestFit() {
		if (jobList.isEmpty())
			return;
		
		Job nextJob = jobList.remove(0);
		for (Partition p : getOrderedPartArray()) {
			if (!p.isBusy() && p.getSize() >= nextJob.getSize()) {
				p.assignJob(nextJob);
				lastPartAssigned = p.id;
				return;
			}
		}
		
		waitingQueue.add(nextJob);
	}

	public void nextFit() {
		if (jobList.isEmpty())
			return;
		
		Job nextJob = jobList.remove(0);
		for (int i = lastPartAssigned; i < partList.size(); i++) {
			Partition p = partList.get(i);
			if (!p.isBusy() && p.getSize() >= nextJob.getSize()) {
				p.assignJob(nextJob);
				lastPartAssigned = p.id;
				return;
			}
		}
		
		for (int i = 0; i < lastPartAssigned; i++) {
			Partition p = partList.get(i);
			if (!p.isBusy() && p.getSize() >= nextJob.getSize()) {
				p.assignJob(nextJob);
				lastPartAssigned = p.id;
				return;
			}
		}

		waitingQueue.add(nextJob);
	}

	public void worstFit() {
		if (jobList.isEmpty())
			return;
		
		Job nextJob = jobList.remove(0);
		for (Partition p : getReverseOrderedPartArray()) {
			if (!p.isBusy() && p.getSize() >= nextJob.getSize()) {
				p.assignJob(nextJob);
				lastPartAssigned = p.id;
				return;
			}
		}
		
		waitingQueue.add(nextJob);
	}
}
