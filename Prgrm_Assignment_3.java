/*
	CSCI 3320 Advanced Programming
	Aarti Munjal
		
	Prgrm Assignment #3
	Charles Bollig

*/
import java.util.*;

public class Prgrm_Assignment_3{
	
	//TreeNode class
	public static class TreeNode{

		int data;
		TreeNode leftChild;
		TreeNode rightChild;
		
		//TreeNode class default constructor
		public TreeNode(){
			
			//Default as -1 for error checking
			data = -1;
			leftChild = null;
			rightChild = null;
		}

		//TreeNode class constructor
		public TreeNode( int input ){

			data = input;
			leftChild = null;
			rightChild = null;
		}	
	}	
	
	//Tree class
	public static class Tree{

		TreeNode root;

		//function that gets called in Tree class constructor
		public TreeNode createTree( int[] inputArray, int begin, int end ){
			
			int middle = (int)((begin + end)/2);
			if(end == middle){

				if(middle >= inputArray.length)
					return null;
				
				return null;
			}

			TreeNode newNode = new TreeNode(inputArray[middle]);
			newNode.leftChild = createTree(inputArray, begin, middle);
			newNode.rightChild = createTree(inputArray, middle+1, end);
			return newNode;

		}

		//Tree class constructor
		public Tree( int[] inputArray ){

			root = createTree(inputArray, 0, inputArray.length);
		}

		//displayTree member function to show the levels of the tree
		public void displayTree(TreeNode currNode, int level){

			if(currNode.leftChild != null){
				//System.out.println("currNode [" + currNode.data + "] currNode.leftChild: " + currNode.leftChild.data);
				displayTree(currNode.leftChild, level -1);
			}
			for(int i = 0; i < level; i++){
				System.out.print('\t');
			}
			System.out.println( currNode.data);
			if(currNode.rightChild != null){
				//System.out.println("currNode [" + currNode.data + "] currNode.rightChild: " + currNode.rightChild.data);				
				displayTree(currNode.rightChild, level -1);
				
			}
			return;
		
		}

		//findNode member function to find a particular node
		public TreeNode findNode( TreeNode currNode, int val ){

			TreeNode testNode = new TreeNode();
			if(currNode.data == val){
				System.out.println("\nFound it: " + currNode.data + "\n");
				return currNode;
			}

			if(currNode.leftChild != null){
				
				testNode = findNode(currNode.leftChild, val);
				if(testNode.data == val)
				return testNode;
			}

			if(currNode.rightChild != null){
							
				testNode = findNode(currNode.rightChild, val);
				if(testNode.data == val)
				return testNode;
				
			}
			//System.out.println("Executing " + testNode.data);
			return testNode;
		
		}

		//findCommonAncestor member function to find a common ancestor betwen two nodes
		public TreeNode findCommonAncestor( TreeNode root, TreeNode node1, TreeNode node2 ){

			//either tree is empty or node is empty
			if( root == null)
				return null;

			//before recursion, if node1 or node2 is the root; it the common ancestor
			if(node1 == root || node2 == root)
				return root;
			
			TreeNode leftSide = findCommonAncestor( root.leftChild, node1, node2);
			
			TreeNode rightSide = findCommonAncestor( root.rightChild, node1, node2);


			if(leftSide != null && rightSide != null)
				return root;
			
			if(leftSide != null)
				return leftSide;
			else
				return rightSide;
		}

	}
	
	//main program
	public static void main(String [] args){
		
		Scanner keyboard = new Scanner(System.in);
		int val1, val2;
		int [] prgrmArray = {2, 3, 5, 6, 7, 9, 11, 12, 14, 15, 16, 18, 20, 23, 24, 26};

		//Create the tree based on the array
		Tree prgrmTree = new Tree(prgrmArray);
			
		//Print the tree
		System.out.println("\nThe tree is below (from right-to-left): \n");
		prgrmTree.displayTree(prgrmTree.root, 5);
		
		try{
			System.out.print("\nEnter the first node: \n");
			val1 = keyboard.nextInt();
			System.out.print("\nEnter the second node: \n");
			val2 = keyboard.nextInt();

			//Test to see if the values actually exist in the tree
			TreeNode node1 = prgrmTree.findNode( prgrmTree.root, val1 );
			TreeNode node2 = prgrmTree.findNode( prgrmTree.root, val2 );

			if(node1.data == -1 || node2.data == -1 ){

				System.out.println("\nOne or more of the inputted nodes does not exist in the tree.\n");
				return;

			}

			TreeNode commonAncestor = prgrmTree.findCommonAncestor(prgrmTree.root, node1, node2);

			if(commonAncestor == null  ){
				System.out.println("\nUnfortunately, the tree is empty.\n");
				return;
			}
			if(commonAncestor == node1)
				System.out.println("The common ancestor of the two nodes is the first node: " + node1.data+ "\n");
			else if(commonAncestor == node2)
				System.out.println("The common ancestor of the two nodes is the second node: " + node2.data+ "\n");
			else
				System.out.println("\nThe common ancestor of the two nodes is: " + commonAncestor.data + "\n");

		}catch(InputMismatchException e){

			System.out.println("\nYou must enter a valid node. Exiting Program.\n");

		}
	
	}
	
}	

