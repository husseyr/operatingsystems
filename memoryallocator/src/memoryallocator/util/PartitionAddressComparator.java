/**
 * 
 */
package memoryallocator.util;

import java.util.Comparator;

/**
 * @author rob
 *
 */
public class PartitionAddressComparator implements Comparator<Partition> {

	@Override
	public int compare(Partition o1, Partition o2) {
		return o1.startAddress - o2.startAddress;
	}

}
