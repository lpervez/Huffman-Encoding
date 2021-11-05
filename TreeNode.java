//-------------------------------------------------------------
// DO NOT EDIT ANYTHING FOR THIS CLASS EXCEPT TO ADD JAVADOCS
//-------------------------------------------------------------
import java.io.Serializable;

//Tree node used in a binary huffman tree

/**
 * Tree Node Class.
 */
public class TreeNode implements Serializable, Comparable<TreeNode> {

	//bad practice to have public inst. variables, 
	//but we want to test this more easily...
	
	//count for the character (leaf node) or 
	/**
	 * total of counts from both children (internal node).
	 */
	public int count;
	
	//character represented by this node
	//internal node: keep character to be null
	/**
	 * internal node: keep character to be null.
	 */
	public Character character = null;
	
	/** children links.
	 *
	 */
	public TreeNode left, right;

	/**
	 * TreeNode.
	 * @param count count
	 */
	public TreeNode(int count){
		this.count = count;
	}

	/**
	 * Constructor.
	 * @param count count
	 * @param character character
	 */
	public TreeNode(int count, Character character){
		this.count = count;
		this.character = character;
	}

	/**
	 * Set Left.
	 * @param left left
	 */
	public void setLeft(TreeNode left){ this.left = left;}

	/**
	 * Set right.
	 * @param right right
	 */
	public void setRight(TreeNode right){ this.right = right;}

	/**
	 * CompareTo.
	 * @param otherNode otherNode
	 * @return Integer
	 */
	public int compareTo(TreeNode otherNode){
		if (this.count - otherNode.count!=0){
			return (this.count - otherNode.count); //compare count
		}
		else{
			if (this.character!=null && otherNode.character!=null) {//use char to break the tie
				return (this.character - otherNode.character); 
				//same character + same count would be a tie
			}
			else{
				return (this.count - otherNode.count); 
				//null + same count would be a tie				
			}
		}		
	}

	/**
	 * Equals.
	 * @param o o
	 * @return boolean
	 */
	@Override
	public boolean equals(Object o){
		if (!(o instanceof TreeNode)){
			return false;
		}
		TreeNode otherNode = (TreeNode) o;
		return (this.compareTo(otherNode) == 0);
	}

	/**
	 * To String.
	 * @return String
	 */
	public String toString(){ 
		return "<"+this.character+","+this.count+">";			
	}
}