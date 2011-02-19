/**
 * 
 */

package memoryallocator.util;
/**
 * @author rob
 *
 */
public class Partition implements Comparable<Partition> {
	protected int id;
	protected int oldSize;
	protected int size;			// every partition needs a size
	protected int oldAddress;   // dynamic needs reference to previous starting address
	protected int startAddress;	// where the partition starts in memory
	protected Job accessJob;		// job assigned to partition
	protected boolean status;		// free=false, busy=true
	
	Partition(int id, int size, int startAddress) {
		this.id = id;
		this.size = size;
		oldSize = size;
		this.startAddress = startAddress;
		oldAddress = startAddress;
		accessJob = null;
		status = false;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getOldSize() {
		return oldSize;
	}

	public int getStartAddress() {
		return startAddress;
	}
	
	public int getOldAddress() {
		return oldAddress;
	}
	
	public int getAccessJobID() {
		if (accessJob == null) 
			return -1;
		
		return accessJob.getId();
	}
	
	public boolean isBusy() {
		return status;
	}
	
	public void assignJob(Job accessJob) {
		accessJob.partAssigned = this;
		this.accessJob = accessJob;
		status = true;
	}
	
	public void removeJob() {
		accessJob.partAssigned = null;
		this.accessJob = null;
		status = false;
	}

	public int compareTo(Partition arg0) {
		if (size > arg0.size)
			return 1;
		if (size < arg0.size)
			return -1;
		return 0;
	}
}
