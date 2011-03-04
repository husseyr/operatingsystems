/**
 * 
 */

package memoryallocator.util;
/**
 * @author Rob Hussey
 *
 */
public class Partition implements Comparable<Partition> {
	protected int id; 			// unique id
	protected int oldSize;		// for dynamic - when deallocating, address can change (consolidation)
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
		oldAddress = startAddress; // old address is the start address to start
		accessJob = null; // no job yet
		status = false; // not busy
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
	public Job getAccessJob() {
		return accessJob;
	}
	public int getAccessJobID() {
		if (accessJob == null) 
			return -1;
		
		return accessJob.getId();
	}
	public boolean isBusy() {
		return status;
	}
	
	// assign job - store reference to the job and set a reference to this part in the job
	public void assignJob(Job accessJob) {
		accessJob.partAssigned = this;
		this.accessJob = accessJob;
		status = true;
	}
	
	// remove the references set when adding the job
	public void removeJob() {
		accessJob.partAssigned = null;
		this.accessJob = null;
		status = false;
	}
	
	// decreasing size
	public int compareTo(Partition arg0) {
		return size - arg0.size;
	}
}
