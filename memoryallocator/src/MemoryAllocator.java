import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * 
 */

/**
 * @author rob
 *
 */
public class MemoryAllocator {
	private static final int DEFAULTMEMSIZE = 32748;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int memSize = DEFAULTMEMSIZE;
		Deque<FixedPartition> partList = new LinkedList<FixedPartition>();
		Queue<Job> jobQueue = new LinkedList<Job>();
		
		setupPartitions(memSize, partList);
	}
	
	public static void setupPartitions(int memSize, Deque<FixedPartition> partList) {
		Random rand = new Random(System.currentTimeMillis());
		int totalSize = 0;
		
		while(totalSize < memSize) {
			int nextPortion = rand.nextInt(16 - 2 + 1) + 2;
			int nextSize = memSize / nextPortion;
			totalSize += nextSize;
			
			if (partList.size() > 0) {
				FixedPartition prevPartition = partList.peek();
				partList.push(new FixedPartition(nextSize, prevPartition.getStartAddress() + prevPartition.getSize()));
			}
			else
				partList.push(new FixedPartition(nextSize, 0));
		}
		
		int neededSize = memSize - (totalSize - partList.pop().getSize());
		if (neededSize < memSize / 16)
			neededSize += partList.pop().getSize();
		
		partList.push(new FixedPartition(neededSize, partList.peek().getStartAddress() + partList.peek().getSize()));
		
		totalSize = 0;
		for (FixedPartition fp : partList) {
			totalSize += fp.getSize();
			System.out.println(fp.getStartAddress() + " : " + fp.getSize());
		}
		
		System.out.println(totalSize + " : " + memSize);
	}

}
