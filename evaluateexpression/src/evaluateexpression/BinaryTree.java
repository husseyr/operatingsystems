/**
 * 
 */
package evaluateexpression;

/** BinaryTree has a root node, which has data and links to right and left nodes.
 * @author Rob Hussey
 * date: 1/21/2010
 *
 */
public class BinaryTree<E> {
	// root node will have references to the left and right nodes associated with it
	private Node<E> root;
	
	protected static class Node<E> {
		public E rootData;
		
		// links to left and right nodes associated with the root node
		public Node<E> left;
		public Node<E> right;
		
		// by default - make leaf node (left and right null)
		Node(E rootData) {
			this.rootData = rootData;
			left = null;
			right = null;
		}
	}
	
	// given just some data - create binary tree with just a leaf node with that data
	public BinaryTree(E data) {
		root = new Node<E>(data);
	}
	
	// given a root node - create binary tree with that node as the only node in the tree
	private BinaryTree(Node<E> root) {
		this.root = root;
	}
	
	// given some data and left and right binary trees - create binary tree with the data at root
	// and the binary trees and children of the new tree
	public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		root = new Node<E>(data);
		
		// if either are null - since the nodes are null by default, we don't do anything	
		if (leftTree != null)
			root.left = leftTree.root;
		
		if (rightTree != null)
			root.right = rightTree.root;
	}
	
	// get the left binary tree linked to by the root node
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
        	// return a new tree with the left node as root (which has the needed associations)
            return new BinaryTree<E>(root.left);
        } else {
            return null;
        }
    }
    
    // get the right binary tree linked to by the root node
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        } else {
            return null;
        }
    }
    
    // if root is null or if its children are null, then it is a leaf node
    public boolean isLeaf() {
    	if (root == null)
    		return true;
    	else if (root.left == null && root.right == null)
    		return true;
		return false;
    }
    
    // just return the data at the root node
    public E getData() {
    	return root.rootData;
    }

    // pre-order traversal which creates a formatted string showing each parent/child relationship
    private void preOrderTraverse(Node<E> currentNode, StringBuilder sb, int depth) {
    	// don't do anything if node is empty - the previous was a leaf node
    	if (currentNode == null)
    		return;
    	
    	// add some formatting (more as it gets deeper) to make it easier to follow
    	for (int i = 0; i < depth; i++)
    		sb.append("|_");
    	
    	// put the data and a newline at the end of the formatting above
        sb.append(currentNode.rootData.toString());
        sb.append("\n");
        
        // recursively do the above for each left and right node (adding more to the depth each time)
        preOrderTraverse(currentNode.left, sb, depth + 1);
        preOrderTraverse(currentNode.right, sb, depth + 1);
    }
    
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, sb, 0);
		return sb.toString();
	}
}
