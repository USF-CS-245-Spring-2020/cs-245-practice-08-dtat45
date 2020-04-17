
public class BST<T> {

	BSTNode root=null;
	
	class BSTNode {
		
		Comparable<T> data;
		BSTNode left;
		BSTNode right;
		
		// Constructor
		public BSTNode(Comparable<T> item) {
			
			data=item;
			left=null;
			right=null;
		}
	}
	
	

	// Checks the tree to see if the item is contained in it
	public boolean find(Comparable<T> item) {
		
		return find(root,item);
	}
	
	protected boolean find(BSTNode node,Comparable<T> item) {
		
		if(node==null) // Base case
			return false;
		if(node.data==item) // Returns true if the item is found in the tree
			return true;
		else if(((Comparable<T>) node.data).compareTo((T) item)>0)
			return find(node.left,item);
		else
			return find(node.right,item);
	}
	
	// Places an item in the tree in sorted order
	public void insert(Comparable<T> item) {
		
		root=insert(root,item);
	}
	
	protected BSTNode insert(BSTNode node,Comparable<T> item) {
		
		if(node==null)
			return new BSTNode(item);
		if(((Comparable<T>) node.data).compareTo((T) item)>0)
			node.left=insert(node.left,item);
		else
			node.right=insert(node.right,item);
		
		return node;
	}
	
	// Traverses through the tree and prints every element
	// in sorted order
	public void print() {
		
		inOrderTraversal(root); // Starts traversal at the root
	}
	
	protected void inOrderTraversal(BSTNode node) {
		
		if(node!=null) {
			
			inOrderTraversal(node.left);
			System.out.println(node.data);
			inOrderTraversal(node.right);
		}
	}
	
	// Deletes the desired item from the tree and
	// reconnects leaves using in-order successor, if needed
	public void delete(Comparable<T> item) {
		
		root=delete(root,item);
	}
	
	protected BSTNode delete(BSTNode node,Comparable<T> item) {
		
		if(node==null)
			return null;
		if(((Comparable<T>) node.data).compareTo((T) item)>0)
			node.left=delete(node.left,item);
		else if(((Comparable<T>) node.data).compareTo((T) item)<0)
			node.right=delete(node.right,item);
		else {
			
			if(node.left==null) {
				
				node.data=node.right.data;
				node.right=node.right.right;
			}
			else
				node.data=removeSmallest(node);
			return node;
		}
		
		return null;
	}
	
	protected Comparable<T> removeSmallest(BSTNode node) {
		
		if(node.left.left==null) {
			
			Comparable<T> smallestNodeVal=node.left.data;
			node.left=node.left.right;
			return smallestNodeVal;
		}
		else
			return removeSmallest(node.left);
	}
}
