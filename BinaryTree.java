import java.io.Serializable;
import java.util.Queue;

/**
 * Binary Tree Class.
 * @author laraibpervez
 */
public class BinaryTree implements Serializable {
	/**
	 * Queue Class.
	 */
	private class Queue {
		/**
		 * Node Class.
		 * @author laraibpervez
		 */
		private class Node {
			/**
			 * Value.
			 */
			TreeNode value;
			/**
			 * Next.
			 */
			Node next;
		}
		/**
		 * Attribute.
		 */
		Node head;
		/**
		 * Attribute.
		 */
		Node tail;
		/**
		 * Add Queue.
		 * @param value value
		 */
		@SuppressWarnings("unused")
		void add(TreeNode value) {
			Node node = new Node();
			node.value = value;
			node.next = null;

			if (head == null)
				head = node;
			else
				tail.next = node;
			tail = node;
		}
		/**
		 * Remove.
		 * @return Node
		 */
		@SuppressWarnings("unused")
		TreeNode remove() {
			if (head == null)
				return null;

			TreeNode node = head.value;
			head = head.next;
			if (head == null)
				tail = null;

			return node;
		}

		/**
		 * Is Empty.
		 *
		 * @return Boolean
		 */
		@SuppressWarnings("unused")
		boolean isEmpty() {
			return head == null;
		}
	}
	// -------------------------------------------------------------
	// DO NOT EDIT ANYTHING FOR THIS SECTION EXCEPT TO ADD JAVADOCS
	// -------------------------------------------------------------

	// bad practice to have public inst. variables, but we want to test this...
	/**
	 * Root of tree.
	 */
	public TreeNode root;

	/**
	 * Set Root.
	 *
	 * @param node node
	 */
	public void setRoot(TreeNode node) {
		this.root = node;
	}

	// -------------------------------------------------------------
	// END OF PROVIDED "DO NOT EDIT" SECTION
	// -------------------------------------------------------------

	/**
	 * Helper function for Height.
	 *
	 * @param node node
	 * @return Integer
	 */
	@SuppressWarnings("unused")
	private int recursiveCallHeight(TreeNode node) {
		int left = 0, right = 0;
		if (node.left != null) {
			left = 1 + recursiveCallHeight(node.left);
		}
		if (node.right != null) {
			right = 1 + recursiveCallHeight(node.right);
		}

		return Math.max(left, right);
	}

	/**
	 * Helper Function for Leaf.
	 *
	 * @param node node
	 * @return Integer
	 */
	@SuppressWarnings("unused")
	private int recursiveCallLeaf(TreeNode node) {
		int leafcount = 0;
		if (node.left != null) {
			leafcount += recursiveCallLeaf(node.left);
		}
		if (node.right != null) {
			leafcount += recursiveCallLeaf(node.right);
		}
		if (node.right == null && node.left == null) {
			leafcount += 1;
		}

		return leafcount;
	}

	/**
	 * Helper function for Pre Order.
	 *
	 * @param node node
	 * @param str  str
	 * @return String
	 */
	private String preOrder(TreeNode node, String str) {

		if (node == null) {
			return str;
		}
		str += node.toString();
		if (node.left != null) {
			str = preOrder(node.left, str);
		}
		if (node.right != null) {
			str = preOrder(node.right, str);
		}

		return str;
	}

	/**
	 * Helper function for In Order.
	 *
	 * @param node node
	 * @param str  str
	 * @return String
	 */
	@SuppressWarnings("unused")
	private String inOrder(TreeNode node, String str) {

		if (node == null) {
			return str;
		}
		if (node.left != null) {
			str = inOrder(node.left, str);
		}
		str += node.toString();
		if (node.right != null) {
			str = inOrder(node.right, str);
		}

		return str;
	}

	/**
	 * Helper function for Level Order.
	 *
	 * @param node node
	 * @param str  str
	 * @return String
	 */
	@SuppressWarnings("unused")
	private String levelOrder(TreeNode node, String str) {

		Queue level = new Queue();
		level.add(node);

		while (!level.isEmpty()) {
			TreeNode temp = level.remove();
			if (temp.left != null) {
				level.add(temp.left);
			}
			if (temp.right != null) {
				level.add(temp.right);
			}
			str += temp.toString();

		}
		return str;
	}

	/**
	 * Height.
	 *
	 * @return Integer
	 */
	public int height() {
		// Return the height of the tree.
		// Return -1 for a null tree
		//
		// Hint: this is doable in _very_ few lines of code
		// if you choose to use recursion.
		//
		// O(H): H as the tree height
		if (root == null) {
			return 0;
		}

		return recursiveCallHeight(root); // default return: change or remove as needed

	}

	/**
	 * Number of Leaves.
	 *
	 * @return Integer
	 */
	public int numLeaves() {
		// Return the number of leaf nodes in the tree.
		// Return zero for null trees.
		//
		// Hint: this is doable in _very_ few lines of code
		// if you choose to use recursion.
		//
		// O(N): N is the tree size
		if (root == null) {
			return 0;
		}
		return recursiveCallLeaf(root); // default return: change or remove as needed
	}

	/**
	 * String Pre Order.
	 *
	 * @return String
	 */
	public String toStringPreOrder() {
		// Return a string representation of the tree
		// follow PRE-ORDER traversal to include all nodes.

		// Return empty string "" for null trees.
		// Use the toString() method of TreeNode class.
		// Check main method below for examples.

		// Hint: this is doable in _very_ few lines of code
		// if you choose to use recursion.

		return preOrder(root, ""); // default return: change or remove as needed
	}

	/**
	 * String In Order.
	 *
	 * @return String
	 */
	public String toStringInOrder() {
		// Return a string representation of the tree
		// follow IN-ORDER traversal to include all nodes.

		// Return empty string "" for null trees.
		// Use the toString() method of TreeNode class.
		// Check main method below for examples.

		// Hint: this is doable in _very_ few lines of code
		// if you choose to use recursion.
		//

		return inOrder(root, ""); // default return: change or remove as needed
	}

	/**
	 * String Level Order.
	 *
	 * @return String
	 */
	public String toStringLevelOrder() {
		// Return a string representation of the tree
		// follow LEVEL-ORDER traversal to include all nodes.

		// Return empty string "" for null trees.
		// Use the toString() method of TreeNode class.
		// Check main method below for examples.

		// Hint: Remember that you can create a local class
		// to help you with this!

		// [Hint]Possible approach 1:
		// It is easy to make a priority queue into a FIFO queue
		// if you think a little bit about it. Reuse your priority
		// queue here to do the level-order traversal.

		// [Hint]Possible approach 2:
		// It is also easy to reuse the linked list class from
		// Project 2 to implement a FIFO queue and help with the
		// level-order traversal.

		return levelOrder(root, ""); // default return: change or remove as needed

	}

	// -------------------------------------------------------------
	// Main Method For Your Testing -- Edit all you want
	// -------------------------------------------------------------

	/**
	 * Main Method.
	 *
	 * @param args args
	 */
	public static void main(String[] args) {

		BinaryTree tree = new BinaryTree();

		// a single-node tree
		tree.setRoot(new TreeNode(1, 'r'));
		if (tree.height() == 0 && tree.numLeaves() == 1 && tree.toStringPreOrder().equals("<r,1>")) {
			System.out.println("Yay1");
		}

		// set up a tree
		// r,1
		// / \
		// a,2 e,10
		// / \
		// b,3 c,4
		// \
		// d,5
		// Note: this tree is a general binary tree but not a Huffman tree.
		TreeNode node1 = new TreeNode(2, 'a');
		TreeNode node2 = new TreeNode(3, 'b');
		TreeNode node3 = new TreeNode(4, 'c');
		TreeNode node4 = new TreeNode(5, 'd');
		TreeNode node5 = new TreeNode(10, 'e');
		tree.root.setLeft(node1);
		tree.root.setRight(node5);
		node1.setLeft(node2);
		node1.setRight(node3);
		node3.setRight(node4);

		// tree basic features
		if (tree.root.left.right.count == 4 && tree.height() == 3 && tree.numLeaves() == 3) {
			System.out.println("Yay2");
		}

		// tree traverals
		if (tree.toStringPreOrder().equals("<r,1><a,2><b,3><c,4><d,5><e,10>")) {
			System.out.println("Yay3");
		}

		if (tree.toStringInOrder().equals("<b,3><a,2><c,4><d,5><r,1><e,10>")) {
			System.out.println("Yay4");
		}

		if (tree.toStringLevelOrder().equals("<r,1><a,2><e,10><b,3><c,4><d,5>")) {
			System.out.println("Yay5");
		}
	}
}
