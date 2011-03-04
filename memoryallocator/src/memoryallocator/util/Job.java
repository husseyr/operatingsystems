/**
 * 
 */

package memoryallocator.util;
/**
 * @author Rob Hussey
 *
 */
public class Job {
	protected int id; // unique id
	protected int size; // size of job
	protected int completionTime; // time until job finishes and can be removed
	protected Partition partAssigned; // the partition the job is assigned to
									  // null if none
	
	Job(int id, int size, int completionTime) {
		this.id = id;
		this.size = size;
		this.completionTime = completionTime;
		partAssigned = null;
	}

	// getters and setters
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getCompletionTime() {
		return completionTime;
	}
	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Partition getPartAssigned() {
		return partAssigned;
	}

}
