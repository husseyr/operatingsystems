/**
 * 
 */
package memoryallocator.util;

import java.util.Comparator;

/**
 * @author Rob Hussey
 *
 */
public class PartitionAddressComparator implements Comparator<Partition> {

	public int compare(Partition o1, Partition o2) {
		return o1.startAddress - o2.startAddress;
	}

}
