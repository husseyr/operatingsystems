/**
 * 
 */

package memoryallocator.util;
/**
 * @author rob
 *
 */
public class Job {
	protected int id;
	protected int size;
	protected int completionTime;
	
	Job(int id, int size, int completionTime) {
		this.id = id;
		this.size = size;
		this.completionTime = completionTime;
	}

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

}
