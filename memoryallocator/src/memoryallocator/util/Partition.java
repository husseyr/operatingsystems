/**
 * 
 */

package memoryallocator.util;
/**
 * @author rob
 *
 */
public class Partition {
	protected int id;
	protected int size;			// every partition needs a size
	protected int startAddress;	// where the partition starts in memory
	protected Job accessJob;		// job assigned to partition
	protected boolean status;		// free=false, busy=true
	
	Partition(int id, int size, int startAddress) {
		this.id = id;
		this.size = size;
		this.startAddress = startAddress;
		accessJob = null;
		status = false;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public int getStartAddress() {
		return startAddress;
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
		this.accessJob = accessJob;
		status = true;
	}
}
