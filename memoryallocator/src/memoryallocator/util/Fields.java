/**
 * 
 */
package memoryallocator.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * @author Rob Hussey
 *
 */
public class Fields {
	private int memSize;
	private List<Partition> partList;
	private List<Partition> freeList;
	private List<Job> jobList;
	private Queue<Job> waitingQueue;
	private static int totalPartSize;
	private boolean dynamic;
	private static int lastJobID;
	private static int lastPartID;
	private int algorithm; // 0=first-fit, 1=best-fit, 2=next-fit, 3=worst-fit
	private int lastPartAssigned;
	private int fragmentation;
	
	// constructor - need memsize, a list of partitions (all), and a list of jobs
	public Fields(int memSize, List<Partition> partList, List<Job> jobList) {
		this.memSize = memSize;
		this.partList = partList;
		this.jobList = jobList;
		waitingQueue = new LinkedList<Job>();
		freeList = new LinkedList<Partition>();
		dynamic = false;
		totalPartSize = 0;
		lastJobID = -1; // -1 since there is no last job at this point
		lastPartID = -1;
		algorithm = 0;
		lastPartAssigned = 0;
		fragmentation = 0;
	}
	
	/*
	 * Getters and setters follow
	 */
	public int getMemSize() {
		return memSize;
	}
	public void setMemSize(int memSize) {
		this.memSize = memSize;
		
		if (dynamic == true || (dynamic == false && memSize < totalPartSize)) {
			lastJobID = 0;
			jobList = new LinkedList<Job>();
			totalPartSize = 0;
			partList = new LinkedList<Partition>();
			freeList = new LinkedList<Partition>();
			waitingQueue = new LinkedList<Job>();
			lastPartAssigned = 0;
			fragmentation = 0;
		}
	}
	public int getTotalPartSize() {
		return totalPartSize;
	}
	public List<Partition> getPartList() {
		return partList;
	}
	public List<Partition> getFreeList() {
		return freeList;
	}
	public Partition[] getOrderedPartArray() {
		Partition[] oParts = Arrays.copyOf(freeList.toArray(), freeList.size(), Partition[].class);
		Arrays.sort(oParts);
		
		return oParts;
	}
	public Partition[] getReverseOrderedPartArray() {
		Partition[] oParts = Arrays.copyOf(freeList.toArray(), freeList.size(), Partition[].class);
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
		// reset everything when going from fixed to dynamic
		this.dynamic = dynamic;
		totalPartSize = 0;
		partList = new LinkedList<Partition>();
		freeList = new LinkedList<Partition>();
		waitingQueue = new LinkedList<Job>();
		lastJobID = 0;
		jobList = new LinkedList<Job>();
		lastPartAssigned = 0;
		fragmentation = 0;
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
	public void addPartition(int size, int memAddress) {
		lastPartID++; // partition counter
		Partition p = new Partition(lastPartID, size, memAddress);
		partList.add(p);
		freeList.add(p); // every partition starts out free
		totalPartSize += size;
	}
	
	public void removePartition(int id) {
		if (partList.size() <= id)
			return;
		
		// get the partition
		Partition p = partList.get(id);
		
		// set previous address to the current address
		int prevAddr = p.startAddress;
		
		// move back the address of all proceeding partitions
		for (int i = id + 1; i < partList.size(); i++) {
			int curAddr = partList.get(i).startAddress;
			partList.get(i).startAddress = prevAddr;
			prevAddr = curAddr;
		}
		lastPartID--; // ids have been decremented, so last part is decremented
		totalPartSize -= partList.remove(id).size;
		freeList.remove(p);
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
		lastJobID++; // counter for job ids for configuring jobs - NOT actual id
		
		Random rand = new Random();
		// random completion time between 2 and 12
		jobList.add(new Job(lastJobID, size, rand.nextInt(10) + 2));
	}
	
	public void removeJob(int id) {
		if (jobList.size() <= id)
			return;
		
		// decrement ids of jobs proceeding
		for (int i = id + 1; i < jobList.size(); i++)
			jobList.get(i).setId(i - 1);
		
		// remove from job list
		jobList.remove(id);
		lastJobID--;
	}
	
	public void setAlgorithm(int algorithm) {
		this.algorithm = algorithm;
	}
	
	public int getAlgorithm() {
		return algorithm;
	}
	
	// look for adjacent partitions in free list - consolidate
	private void deallocate() {
		for (int i = 0; i < freeList.size(); i++) {
			Partition p1 = freeList.get(i);
			
			// check every partition to see if adjacent - exhaustive 
			for (int j = 0; j < freeList.size(); j++) {
				Partition p2 = freeList.get(j);
				if (p1.startAddress + p1.size == p2.startAddress) {
					p1.setSize(p1.size + p2.size);
					freeList.remove(p2);
				}
			}
		}
	}
	
	private void assignDynamicPartition(Partition p, Job j) {
		if (p.size <= j.getSize())
			return;
			
		// create a new partition with a new id, a size of the difference of the partition size and job size being
		//  assigned, and a starting address of the partition being assigned address + the job size
		Partition freePartition = new Partition(++lastPartID, p.size - j.getSize(), p.startAddress + j.getSize());
		freePartition.oldAddress = p.startAddress; // old address - for the snapshot
		freePartition.oldSize = p.size; // old size - for the snapshot
		p.setSize(j.getSize()); // since we have a new partition with size p.size - j.getSize()
		                        // we need to remove j.getSize() from the old
		freeList.add(freePartition);
		fixPartitionOrder();
		partList.add(freePartition);
	}
	
	private boolean firstFit(Job j) {
		// go through partitions in their current order - find first fit for job
		for (Partition p : freeList) {
			if (p.getSize() >= j.getSize()) {
				p.assignJob(j);
				lastPartAssigned = p.id;
				freeList.remove(p);
				if (dynamic && j.getSize() < p.getSize())
					assignDynamicPartition(p, j);
				
				return true;
			}
		}
		
		waitingQueue.add(j);
		return false;
	}
	
	public void firstFit() {
		// try to find something in the waiting queue that will fit first
		if (!waitingQueue.isEmpty()) {
			for (int i = 0; i < waitingQueue.size(); i++) {
				if (firstFit(waitingQueue.remove()))
					return;
			}
		}

		// if nothing works in waiting queue - try a new job
		if (!jobList.isEmpty())
			firstFit(jobList.remove(0));
	}
	
	private boolean bestFit(Job j) {
		// get partitions sorted smallest to largest - find first (smallest) fit
		for (Partition p : getOrderedPartArray()) {
			if (p.getSize() >= j.getSize()) {
				p.assignJob(j);
				freeList.remove(p);
				lastPartAssigned = p.id;
				if (dynamic && j.getSize() < p.getSize())
					assignDynamicPartition(p, j);
				
				return true;
			}
		}
		
		waitingQueue.add(j);
		return false;
	}
	
	public void bestFit() {
		// try to find something in the waiting queue that will fit first
		if (!waitingQueue.isEmpty()) {
			for (int i = 0; i < waitingQueue.size(); i++) {
				if (bestFit(waitingQueue.remove()))
					return;
			}
		}

		// if nothing works in waiting queue - try a new job
		if (!jobList.isEmpty())
			bestFit(jobList.remove(0));
	}
	
	private boolean nextFit(Job j) {
		// start search at whatever partition last assigned a job
		for (int i = lastPartAssigned; i < partList.size(); i++) {
			Partition p = partList.get(i);
			if (freeList.contains(p) && p.getSize() >= j.getSize()) {
				p.assignJob(j);
				freeList.remove(p);
				lastPartAssigned = p.id;
				if (dynamic && j.getSize() < p.getSize())
					assignDynamicPartition(p, j);
				
				return true;
			}
		}
		
		// nothing from last partition to end, start from beginning
		for (int i = 0; i < lastPartAssigned; i++) {
			Partition p = partList.get(i);
			if (freeList.contains(p) && p.getSize() >= j.getSize()) {
				p.assignJob(j);
				lastPartAssigned = p.id;
				if (dynamic && j.getSize() < p.getSize())
					assignDynamicPartition(p, j);
				return true;
			}
		}
		
		waitingQueue.add(j);
		return false;
	}
	
	public void nextFit() {
		// try to find something in the waiting queue that will fit first
		if (!waitingQueue.isEmpty()) {
			for (int i = 0; i < waitingQueue.size(); i++) {
				if (nextFit(waitingQueue.remove()))
					return;
			}
		}

		// if nothing works in waiting queue - try a new job
		if (!jobList.isEmpty())
			nextFit(jobList.remove(0));
	}

	private boolean worstFit(Job j) {
		// get partitions sorted biggest to smallest and find first (biggest) that it will fit
		for (Partition p : getReverseOrderedPartArray()) {
			if (p.getSize() >= j.getSize()) {
				p.assignJob(j);
				freeList.remove(p);
				lastPartAssigned = p.id;
				if (dynamic && j.getSize() < p.getSize())
					assignDynamicPartition(p, j);
				
				return true;
			}
		}
		
		waitingQueue.add(j);
		return false;
	}
	
	public void worstFit() {
		// try to find something in the waiting queue that will fit first
		if (!waitingQueue.isEmpty()) {
			for (int i = 0; i < waitingQueue.size(); i++) {
				if (worstFit(waitingQueue.remove()))
					return;
			}
		}

		// if nothing works in waiting queue - try a new job
		if (!jobList.isEmpty())
			worstFit(jobList.remove(0));
	}

	// sorts the free partitions by starting address and adds them back into the free list in order
	public void fixPartitionOrder() {
		Partition[] partsArray = Arrays.copyOf(freeList.toArray(), freeList.size(), Partition[].class);
		Arrays.sort(partsArray, new PartitionAddressComparator());
		freeList = new LinkedList<Partition>();
		for (Partition p : partsArray) {
			freeList.add(p);
		}
	}
	
	public void updateTimes() {
		// decrement each busy job's completion time - if 0 then free the partition
		for (Partition p : partList) {
			if (p.accessJob != null) {
				p.accessJob.completionTime--;
				
				if (p.accessJob.completionTime == 0) {
					p.removeJob();
					freeList.add(p);
					fixPartitionOrder();
				}
			}
		}

		// consolidate space if dynamic
		if (dynamic)
			deallocate();
	}
	
	// if dynamic - return external fragmentation, if fixed - internal fragmentation
	public int getFragmentation() {
		int frag = 0;
		if (dynamic) {
			for (Partition p : freeList)
				frag += p.size;
			return frag;
		}
		else {
			for (Partition p : partList) {
				if (p.isBusy())
					frag += p.size - p.accessJob.size;
			}
		}
		return frag;
	}
}
