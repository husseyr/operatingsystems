
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/** Basic test of BinaryTree class functionality
 * @author Rob Hussey
 * date: 1/21/2010
 *
 */
public class BinaryTreeTest {

	private BinaryTree<String> root;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// create a binary tree with a root node, 2 children of the root, and 4 leaf nodes
		BinaryTree<String> leftLeaf1 = new BinaryTree<String>("4");
		BinaryTree<String> leftLeaf2 = new BinaryTree<String>("5");
		
		BinaryTree<String> rightLeaf1 = new BinaryTree<String>("6");
		BinaryTree<String> rightLeaf2 = new BinaryTree<String>("7");
		
		BinaryTree<String> leftRoot = new BinaryTree<String>("2", leftLeaf1, leftLeaf2);
		BinaryTree<String> rightRoot = new BinaryTree<String>("3", rightLeaf1, rightLeaf2);
		
		root = new BinaryTree<String>("1", leftRoot, rightRoot);
	}

	@Test
	public void testToString() {
		// print out the tree structure
		System.out.println(root);
		
	}
}
